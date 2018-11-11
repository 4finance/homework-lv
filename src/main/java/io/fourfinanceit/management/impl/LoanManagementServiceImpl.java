package io.fourfinanceit.management.impl;

import io.fourfinanceit.constant.RequestStatus;
import io.fourfinanceit.constant.RiskRatingResult;
import io.fourfinanceit.controller.dto.LoanDTO;
import io.fourfinanceit.controller.dto.LoanExtensionRequestDTO;
import io.fourfinanceit.controller.dto.LoanRequestDTO;
import io.fourfinanceit.controller.dto.LoanResponseDTO;
import io.fourfinanceit.domain.LoanDO;
import io.fourfinanceit.domain.LoanExtensionDO;
import io.fourfinanceit.domain.LoanExtensionRequestDO;
import io.fourfinanceit.domain.LoanRequestDO;
import io.fourfinanceit.management.LoanManagementService;
import io.fourfinanceit.management.RiskAnalysisService;
import io.fourfinanceit.mapper.LoanExtensionMapper;
import io.fourfinanceit.mapper.LoanExtensionRequestMapper;
import io.fourfinanceit.mapper.LoanMapper;
import io.fourfinanceit.mapper.LoanRequestMapper;
import io.fourfinanceit.service.LoanExtensionRequestService;
import io.fourfinanceit.service.LoanExtensionService;
import io.fourfinanceit.service.LoanRequestService;
import io.fourfinanceit.service.LoanService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import static io.fourfinanceit.constant.Settings.DEFAULT_INTEREST_RATE;
import static io.fourfinanceit.constant.Settings.EXTENSION_INTEREST_RATE;

@Service
public class LoanManagementServiceImpl implements LoanManagementService {

    private final LoanService loanService;
    private final LoanRequestService loanRequestService;
    private final LoanExtensionService loanExtensionService;
    private final LoanExtensionRequestService loanExtensionRequestService;
    private final RiskAnalysisService riskAnalysisService;

    private final HashMap<RiskRatingResult, Function<LoanRequestDO, LoanResponseDTO>> loanFunctions = new HashMap<>();

    public LoanManagementServiceImpl(final LoanService loanService,
                                     final LoanRequestService loanRequestService,
                                     final LoanExtensionService loanExtensionService,
                                     final LoanExtensionRequestService loanExtensionRequestService,
                                     final RiskAnalysisService riskAnalysisService) {
        this.loanService = loanService;
        this.loanRequestService = loanRequestService;
        this.loanExtensionService = loanExtensionService;
        this.loanExtensionRequestService = loanExtensionRequestService;
        this.riskAnalysisService = riskAnalysisService;
    }

    @PostConstruct
    protected void init() {
        final Function<LoanRequestDO, LoanResponseDTO> approveFunction = this::approve;
        final Function<LoanRequestDO, LoanResponseDTO> rejectFunction = this::reject;
        final Function<LoanRequestDO, LoanResponseDTO> holdFunction = this::hold;
        loanFunctions.put(RiskRatingResult.LOW, approveFunction);
        loanFunctions.put(RiskRatingResult.MEDIUM, holdFunction);
        loanFunctions.put(RiskRatingResult.HIGH, rejectFunction);
    }

    @Override
    public List<LoanDTO> getLoans(final Long clientId) {
        List<LoanDO> loans = loanService.getLoans(clientId);
        return loans.stream().map(LoanMapper.INSTANCE::map).collect(Collectors.toList());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public LoanResponseDTO applyForLoan(final LoanRequestDTO loanRequest) {

        final LoanRequestDO request = LoanRequestMapper.INSTANCE.map(loanRequest);
        final RiskRatingResult result = riskAnalysisService.runAnalysis(request);

        final Function<LoanRequestDO, LoanResponseDTO> loanCommand = loanFunctions.get(result);
        return loanCommand.apply(request);
    }

    private LoanResponseDTO approve(final LoanRequestDO request) {
        saveLoanRequest(request, RequestStatus.APPROVED);
        saveLoan(request);
        return new LoanResponseDTO(String.format("Loan, sum: %s, period: %s approved", request.getRequestedSum(), request.getDuration()));
    }

    private LoanResponseDTO reject(final LoanRequestDO request) {
        saveLoanRequest(request, RequestStatus.REJECTED);
        return new LoanResponseDTO(String.format("Loan, sum: %s, period: %s rejected", request.getRequestedSum(), request.getDuration()));
    }

    private LoanResponseDTO hold(final LoanRequestDO request) {
        saveLoanRequest(request, RequestStatus.ON_HOLD);
        return new LoanResponseDTO(String.format("Loan, sum: %s, period: %s is on hold, additional details required", request.getRequestedSum(), request.getDuration()));
    }

    private void saveLoanRequest(final LoanRequestDO request, final RequestStatus status) {
        request.setStatus(status.getStatus());
        loanRequestService.create(request);
    }

    private void saveLoan(final LoanRequestDO request) {
        final LoanDO loan = LoanMapper.INSTANCE.map(request);
        loan.setInterestRate(DEFAULT_INTEREST_RATE);
        loanService.create(loan);
    }

    @Override
    public LoanResponseDTO extendLoan(final LoanExtensionRequestDTO loanExtensionRequest) {
        final LoanExtensionRequestDO extensionRequest = LoanExtensionRequestMapper.INSTANCE.map(loanExtensionRequest);
        final LoanExtensionDO extension = LoanExtensionMapper.INSTANCE.map(loanExtensionRequest);
        extension.setInterestRate(EXTENSION_INTEREST_RATE);

        final Optional<LoanDO> optionalLoan = loanService.getLoan(extensionRequest.getLoan().getId());
        final LoanDO loan = optionalLoan.orElseThrow(() -> new IllegalArgumentException("Not existing loan can not be extended"));

        extensionRequest.setStatus(RequestStatus.APPROVED.getStatus());
        loanExtensionRequestService.create(extensionRequest);
        loanExtensionService.create(extension);

        loan.setLoanExtension(extension);
        loanService.update(loan);

        return new LoanResponseDTO(String.format("Loan extended for a period of a: %s months", extension.getDuration()));
    }
}

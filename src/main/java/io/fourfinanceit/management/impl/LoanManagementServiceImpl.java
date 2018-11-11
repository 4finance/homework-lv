package io.fourfinanceit.management.impl;

import io.fourfinanceit.controller.dto.LoanDTO;
import io.fourfinanceit.controller.dto.LoanRequestDTO;
import io.fourfinanceit.controller.dto.LoanResponseDTO;
import io.fourfinanceit.domain.LoanDO;
import io.fourfinanceit.domain.LoanRequestDO;
import io.fourfinanceit.management.LoanManagementService;
import io.fourfinanceit.mapper.LoanMapper;
import io.fourfinanceit.mapper.LoanRequestMapper;
import io.fourfinanceit.service.LoanRequestService;
import io.fourfinanceit.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanManagementServiceImpl implements LoanManagementService {

    private final LoanRequestService loanRequestService;
    private final LoanService loanService;

    @Autowired
    public LoanManagementServiceImpl(final LoanRequestService loanRequestService,
                                     final LoanService loanService) {
        this.loanRequestService = loanRequestService;
        this.loanService = loanService;
    }

    @Override
    public List<LoanDTO> getLoans(final Long clientId) {
        List<LoanDO> loans = loanService.getLoans(clientId);
        return loans.stream().map(LoanMapper.INSTANCE::map).collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<LoanResponseDTO> applyForLoan(final LoanRequestDTO loanRequest) {

        final LoanRequestDO map = LoanRequestMapper.INSTANCE.map(loanRequest);





        return null;
    }
}

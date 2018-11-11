package io.fourfinanceit.management.impl;

import io.fourfinanceit.constant.RiskRatingResult;
import io.fourfinanceit.controller.dto.*;
import io.fourfinanceit.domain.ClientDO;
import io.fourfinanceit.domain.LoanDO;
import io.fourfinanceit.domain.LoanExtensionDO;
import io.fourfinanceit.management.RiskAnalysisService;
import io.fourfinanceit.service.LoanExtensionRequestService;
import io.fourfinanceit.service.LoanExtensionService;
import io.fourfinanceit.service.LoanRequestService;
import io.fourfinanceit.service.LoanService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class LoanManagementServiceImplTest {

    @MockBean
    private LoanService loanService;

    @MockBean
    private LoanRequestService loanRequestService;

    @MockBean
    private LoanExtensionService loanExtensionService;

    @MockBean
    private LoanExtensionRequestService loanExtensionRequestService;

    @MockBean
    private RiskAnalysisService riskAnalysisService;

    @Autowired
    private LoanManagementServiceImpl loanManagementService;

    @Test
    public void testGetLoans() {
        final Long clientId = 1L;

        final LoanExtensionDO loanExtension = new LoanExtensionDO();
        loanExtension.setCreated(new Date());
        loanExtension.setDuration(6);
        loanExtension.setId(2L);
        loanExtension.setInterestRate(1.5f);

        final LoanDO loan = new LoanDO();
        loan.setCreated(new Date());
        loan.setDuration(12);
        loan.setId(1L);
        loan.setInterestRate(10f);
        loan.setLoanSum(1000f);
        loan.setLoanExtension(loanExtension);

        when(loanService.getLoans(clientId)).thenReturn(Collections.singletonList(loan));

        final List<LoanDTO> resultLoans = loanManagementService.getLoans(clientId);
        assertThat("Loans should not be null", resultLoans, is(notNullValue()));
        assertThat("Loans count should be 1", resultLoans.size(), is(1));

        final LoanDTO resultLoan = resultLoans.get(0);
        assertThat("Loan should not be null", resultLoan, is(notNullValue()));
        assertThat("Loan creation date should not be null", resultLoan.getCreated(), is(notNullValue()));
        assertThat("Loan duration should be 12", resultLoan.getDuration(), is(12));
        assertThat("Loan id should be 1", resultLoan.getId(), is(1L));
        assertThat("Loan interest rate should be 10", resultLoan.getInterestRate(), is(10f));
        assertThat("Loan sum rate should be 10", resultLoan.getLoanSum(), is(1000f));

        final LoanExtensionDTO resultLoanExtension = resultLoan.getLoanExtension();
        assertThat("Loan extension should not be null", resultLoanExtension, is(notNullValue()));
        assertThat("Loan extension creation date should not be null", resultLoanExtension.getCreated(), is(notNullValue()));
        assertThat("Loan extension duration should be 6", resultLoanExtension.getDuration(), is(6));
        assertThat("Loan extension is should be 2", resultLoanExtension.getId(), is(2L));
        assertThat("Loan extension interest rate is 1.5", resultLoanExtension.getInterestRate(), is(1.5f));

        final InOrder inOrder = inOrder(loanService);
        inOrder.verify(loanService).getLoans(clientId);
    }

    @Test
    public void testApplyForLoanApproved() {
        final LoanRequestDTO loanRequest = prepareTestRequest();
        when(riskAnalysisService.runAnalysis(any())).thenReturn(RiskRatingResult.LOW);

        final LoanResponseDTO resultResponse = loanManagementService.applyForLoan(loanRequest);
        assertThat("Response should not be null", resultResponse, is(notNullValue()));
        assertThat("Response message is not correct", resultResponse.getMessage(), is(String.format("Loan, sum: %s, period: %d approved", loanRequest.getRequestedSum(), loanRequest.getDuration())));

        final InOrder inOrder = inOrder(riskAnalysisService, loanRequestService, loanService);
        inOrder.verify(riskAnalysisService).runAnalysis(any());
        inOrder.verify(loanRequestService).create(any());
        inOrder.verify(loanService).create(any());
    }

    @Test
    public void testApplyForLoanHold() {
        final LoanRequestDTO loanRequest = prepareTestRequest();
        when(riskAnalysisService.runAnalysis(any())).thenReturn(RiskRatingResult.MEDIUM);

        final LoanResponseDTO resultResponse = loanManagementService.applyForLoan(loanRequest);
        assertThat("Response should not be null", resultResponse, is(notNullValue()));
        assertThat("Response message is not correct", resultResponse.getMessage(),
                is(String.format("Loan, sum: %s, period: %d is on hold, additional details required", loanRequest.getRequestedSum(), loanRequest.getDuration())));

        final InOrder inOrder = inOrder(riskAnalysisService, loanRequestService);
        inOrder.verify(riskAnalysisService).runAnalysis(any());
        inOrder.verify(loanRequestService).create(any());
    }

    @Test
    public void testApplyForLoanReject() {
        final LoanRequestDTO loanRequest = prepareTestRequest();
        when(riskAnalysisService.runAnalysis(any())).thenReturn(RiskRatingResult.HIGH);

        final LoanResponseDTO resultResponse = loanManagementService.applyForLoan(loanRequest);
        assertThat("Response should not be null", resultResponse, is(notNullValue()));
        assertThat("Response message is not correct", resultResponse.getMessage(),
                is(String.format("Loan, sum: %s, period: %s rejected", loanRequest.getRequestedSum(), loanRequest.getDuration())));

        final InOrder inOrder = inOrder(riskAnalysisService, loanRequestService);
        inOrder.verify(riskAnalysisService).runAnalysis(any());
        inOrder.verify(loanRequestService).create(any());
    }

    private LoanRequestDTO prepareTestRequest() {
        final LoanRequestDTO loanRequest = new LoanRequestDTO();
        loanRequest.setClientId(1L);
        loanRequest.setDuration(12);
        loanRequest.setIpAddress("127.0.0.1");
        loanRequest.setRequestedSum(1000f);
        return loanRequest;
    }

    @Test
    public void testExtendLoan() {
        final LoanExtensionRequestDTO loanExtensionRequest = new LoanExtensionRequestDTO();
        loanExtensionRequest.setClientId(1L);
        loanExtensionRequest.setDuration(6);
        loanExtensionRequest.setIpAddress("127.0.0.1");
        loanExtensionRequest.setLoanId(2L);

        final ClientDO client = new ClientDO();
        client.setId(1L);

        final LoanDO loan = new LoanDO();
        loan.setInterestRate(10f);
        loan.setCreated(new Date());
        loan.setClient(client);
        loan.setDuration(12);
        loan.setId(2L);
        loan.setLoanSum(1000f);

        final Optional<LoanDO> optionalLoan = Optional.of(loan);

        when(loanService.getLoan(2L)).thenReturn(optionalLoan);

        final LoanResponseDTO resultResponse = loanManagementService.extendLoan(loanExtensionRequest);
        assertThat("Response should not be null", resultResponse, is(notNullValue()));
        assertThat("Response message is not correct", resultResponse.getMessage(),
                is(String.format("Loan extended for a period of a: %s months", loanExtensionRequest.getDuration())));

        final InOrder inOrder = inOrder(loanService, loanExtensionRequestService, loanExtensionService);
        inOrder.verify(loanService).getLoan(2L);
        inOrder.verify(loanExtensionRequestService).create(any());
        inOrder.verify(loanExtensionService).create(any());
        inOrder.verify(loanService).update(any());
    }
}
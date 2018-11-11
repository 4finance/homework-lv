package io.fourfinanceit.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.fourfinanceit.controller.LoanController;
import io.fourfinanceit.controller.dto.*;
import io.fourfinanceit.management.LoanManagementService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(LoanController.class)
public class LoanControllerImplTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoanManagementService loanManagementService;

    @Test
    public void testGetClientLoans() throws Exception {
        final LoanExtensionDTO loanExtension = new LoanExtensionDTO();
        loanExtension.setCreated(new Date());
        loanExtension.setDuration(6);
        loanExtension.setId(1L);
        loanExtension.setInterestRate(1.5f);

        final LoanDTO loan = new LoanDTO();
        loan.setCreated(new Date());
        loan.setDuration(12);
        loan.setId(1L);
        loan.setInterestRate(10f);
        loan.setLoanSum(1000f);
        loan.setLoanExtension(loanExtension);

        when(loanManagementService.getLoans(1L)).thenReturn(Collections.singletonList(loan));

        mockMvc.perform(get("/loan/client/{clientid}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.[0].id").value(1L))
                .andExpect(jsonPath("$.[0].loanSum").value(1000f))
                .andExpect(jsonPath("$.[0].interestRate").value(10f))
                .andExpect(jsonPath("$.[0].created").isNotEmpty())
                .andExpect(jsonPath("$.[0].duration").value(12))
                .andExpect(jsonPath("$.[0].loanExtension.id").value(1L))
                .andExpect(jsonPath("$.[0].loanExtension.duration").value(6))
                .andExpect(jsonPath("$.[0].loanExtension.interestRate").value(1.5f))
                .andExpect(jsonPath("$.[0].loanExtension.created").isNotEmpty());
    }

    @Test
    public void testApplyForLoan() throws Exception {
        final LoanRequestDTO loanRequest = new LoanRequestDTO();
        loanRequest.setClientId(1L);
        loanRequest.setDuration(12);
        loanRequest.setIpAddress("127.0.0.1");
        loanRequest.setRequestedSum(1000f);

        final ObjectMapper mapper = new ObjectMapper();
        final String requestBody = mapper.writeValueAsString(loanRequest);

        final String expectedMessage = String.format("Loan, sum: %f, period: %d approved", loanRequest.getRequestedSum(), loanRequest.getDuration());
        final LoanResponseDTO response = new LoanResponseDTO(expectedMessage);

        when(loanManagementService.applyForLoan(any())).thenReturn(response);

        mockMvc.perform(post("/loan/apply")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(expectedMessage));
    }

    @Test
    public void testExtendLoan() throws Exception {
        final LoanExtensionRequestDTO loanExtensionRequest = new LoanExtensionRequestDTO();
        loanExtensionRequest.setClientId(1L);
        loanExtensionRequest.setDuration(6);
        loanExtensionRequest.setIpAddress("127.0.0.1");
        loanExtensionRequest.setLoanId(2L);

        final ObjectMapper mapper = new ObjectMapper();
        final String requestBody = mapper.writeValueAsString(loanExtensionRequest);

        final String expectedMessage = String.format("Loan extended for a period of a: %d months", loanExtensionRequest.getDuration());
        final LoanResponseDTO response = new LoanResponseDTO(expectedMessage);

        when(loanManagementService.extendLoan(any())).thenReturn(response);

        mockMvc.perform(post("/loan/extend")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(expectedMessage));
    }
}
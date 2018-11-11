package io.fourfinanceit.management;

import io.fourfinanceit.controller.dto.LoanDTO;
import io.fourfinanceit.controller.dto.LoanRequestDTO;
import io.fourfinanceit.controller.dto.LoanResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LoanManagementService {

    List<LoanDTO> getLoans(Long clientId);

    ResponseEntity<LoanResponseDTO> applyForLoan(LoanRequestDTO loanRequest);
}

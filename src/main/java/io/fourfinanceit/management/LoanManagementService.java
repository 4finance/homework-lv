package io.fourfinanceit.management;

import io.fourfinanceit.controller.dto.LoanDTO;
import io.fourfinanceit.controller.dto.LoanExtensionRequestDTO;
import io.fourfinanceit.controller.dto.LoanRequestDTO;
import io.fourfinanceit.controller.dto.LoanResponseDTO;

import java.util.List;

public interface LoanManagementService {

    List<LoanDTO> getLoans(Long clientId);

    LoanResponseDTO applyForLoan(LoanRequestDTO loanRequest);

    LoanResponseDTO extendLoan(LoanExtensionRequestDTO loanExtensionRequest);
}

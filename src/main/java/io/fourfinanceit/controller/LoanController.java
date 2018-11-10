package io.fourfinanceit.controller;

import io.fourfinanceit.controller.dto.LoanDTO;

import java.util.List;

public interface LoanController {

    List<LoanDTO> getClientLoans(Long clientId);
}

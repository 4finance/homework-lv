package io.fourfinanceit.controller.impl;

import io.fourfinanceit.controller.LoanController;
import io.fourfinanceit.controller.dto.LoanDTO;
import io.fourfinanceit.controller.dto.LoanRequestDTO;
import io.fourfinanceit.controller.dto.LoanResponseDTO;
import io.fourfinanceit.domain.LoanDO;
import io.fourfinanceit.management.LoanManagementService;
import io.fourfinanceit.mapper.LoanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/loan")
public class LoanControllerImpl implements LoanController {

    private final LoanManagementService loanManagementService;

    @Autowired
    public LoanControllerImpl(final LoanManagementService loanManagementService) {
        this.loanManagementService = loanManagementService;
    }

    @Override
    @GetMapping(value = "/client/{clientid}")
    public List<LoanDTO> getClientLoans(@PathVariable("clientid") final Long clientId) {
        return loanManagementService.getLoans(clientId);
    }

    @Override
    @PostMapping(value = "/apply")
    public ResponseEntity<LoanResponseDTO> applyForLoan(@RequestBody LoanRequestDTO loanRequest) {
        return loanManagementService.applyForLoan(loanRequest);
    }
}

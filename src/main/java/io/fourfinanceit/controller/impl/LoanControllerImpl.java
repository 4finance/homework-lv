package io.fourfinanceit.controller.impl;

import io.fourfinanceit.controller.LoanController;
import io.fourfinanceit.controller.dto.LoanDTO;
import io.fourfinanceit.domain.LoanDO;
import io.fourfinanceit.mapper.LoanMapper;
import io.fourfinanceit.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/loan")
public class LoanControllerImpl implements LoanController {

    private final LoanService loanService;

    @Autowired
    public LoanControllerImpl(final LoanService loanService) {
        this.loanService = loanService;
    }

    @Override
    @GetMapping(value = "/client/{clientid}")
    public List<LoanDTO> getClientLoans(@PathVariable("clientid") final Long clientId) {
        final List<LoanDO> loans = loanService.getLoans(clientId);
        return loans.stream().map(LoanMapper.INSTANCE::map).collect(Collectors.toList());
    }
}

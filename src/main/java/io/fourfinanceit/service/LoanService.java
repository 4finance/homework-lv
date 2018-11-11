package io.fourfinanceit.service;

import io.fourfinanceit.domain.LoanDO;

import java.util.List;
import java.util.Optional;

public interface LoanService {

    List<LoanDO> getLoans(Long clientId);

    LoanDO create(LoanDO loan);

    Optional<LoanDO> getLoan(Long loanId);

    void update(LoanDO loan);
}

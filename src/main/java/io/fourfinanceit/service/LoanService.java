package io.fourfinanceit.service;

import io.fourfinanceit.domain.LoanDO;

import java.util.List;

public interface LoanService {

    List<LoanDO> getLoans(Long clientId);
}

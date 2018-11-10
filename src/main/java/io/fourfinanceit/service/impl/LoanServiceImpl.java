package io.fourfinanceit.service.impl;

import io.fourfinanceit.domain.ClientDO;
import io.fourfinanceit.domain.LoanDO;
import io.fourfinanceit.repository.LoanRepository;
import io.fourfinanceit.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;

    @Autowired
    public LoanServiceImpl(final LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @Override
    public List<LoanDO> getLoans(final Long clientId) {
        return loanRepository.findByClient(new ClientDO(clientId));
    }
}

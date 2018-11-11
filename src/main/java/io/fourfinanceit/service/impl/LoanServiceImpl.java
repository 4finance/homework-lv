package io.fourfinanceit.service.impl;

import io.fourfinanceit.domain.ClientDO;
import io.fourfinanceit.domain.LoanDO;
import io.fourfinanceit.repository.LoanRepository;
import io.fourfinanceit.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;

    @Autowired
    public LoanServiceImpl(final LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @Override
    public List<LoanDO> getLoans(final Long clientId) {
        return Collections.unmodifiableList(loanRepository.findByClient(new ClientDO(clientId)));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public LoanDO create(final LoanDO loan) {
        loan.setCreated(new Date());
        return loanRepository.save(loan);
    }

    @Override
    public Optional<LoanDO> getLoan(final Long loanId) {
        return loanRepository.findById(loanId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(final LoanDO loan) {
        loanRepository.save(loan);
    }
}

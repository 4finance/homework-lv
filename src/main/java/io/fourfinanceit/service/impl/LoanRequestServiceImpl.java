package io.fourfinanceit.service.impl;

import io.fourfinanceit.repository.LoanRequestRepository;
import io.fourfinanceit.service.LoanRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanRequestServiceImpl implements LoanRequestService {

    private final LoanRequestRepository loanRequestRepository;

    @Autowired
    public LoanRequestServiceImpl(final LoanRequestRepository loanRequestRepository) {
        this.loanRequestRepository = loanRequestRepository;
    }


}

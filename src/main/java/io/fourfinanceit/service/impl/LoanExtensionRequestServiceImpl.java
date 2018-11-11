package io.fourfinanceit.service.impl;

import io.fourfinanceit.domain.LoanExtensionRequestDO;
import io.fourfinanceit.repository.LoanExtensionRequestRepository;
import io.fourfinanceit.service.LoanExtensionRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoanExtensionRequestServiceImpl implements LoanExtensionRequestService {

    private final LoanExtensionRequestRepository loanExtensionRequestRepository;

    @Autowired
    public LoanExtensionRequestServiceImpl(final LoanExtensionRequestRepository loanExtensionRequestRepository) {
        this.loanExtensionRequestRepository = loanExtensionRequestRepository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public LoanExtensionRequestDO create(final LoanExtensionRequestDO loanExtensionRequest) {
        return loanExtensionRequestRepository.save(loanExtensionRequest);
    }
}

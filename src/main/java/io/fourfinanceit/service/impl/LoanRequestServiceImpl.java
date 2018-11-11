package io.fourfinanceit.service.impl;

import io.fourfinanceit.domain.LoanRequestDO;
import io.fourfinanceit.repository.LoanRequestRepository;
import io.fourfinanceit.service.LoanRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class LoanRequestServiceImpl implements LoanRequestService {

    private final LoanRequestRepository loanRequestRepository;

    @Autowired
    public LoanRequestServiceImpl(final LoanRequestRepository loanRequestRepository) {
        this.loanRequestRepository = loanRequestRepository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public LoanRequestDO create(final LoanRequestDO request) {
        request.setCreated(new Date());
        return loanRequestRepository.save(request);
    }

    @Override
    public Integer countByIpAddressForToday(String ipAddress) {
        return loanRequestRepository.countByIpAddressForToday(ipAddress);
    }
}

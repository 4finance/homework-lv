package io.fourfinanceit.service.impl;

import io.fourfinanceit.domain.LoanExtensionDO;
import io.fourfinanceit.repository.LoanExtensionRepository;
import io.fourfinanceit.service.LoanExtensionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static io.fourfinanceit.constant.Settings.EXTENSION_INTEREST_RATE;

@Service
public class LoanExtensionServiceImpl implements LoanExtensionService {

    private final LoanExtensionRepository loanExtensionRepository;

    @Autowired
    public LoanExtensionServiceImpl(final LoanExtensionRepository loanExtensionRepository) {
        this.loanExtensionRepository = loanExtensionRepository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public LoanExtensionDO create(final LoanExtensionDO loanExtension) {
        loanExtension.setCreated(new Date());
        return loanExtensionRepository.save(loanExtension);
    }
}

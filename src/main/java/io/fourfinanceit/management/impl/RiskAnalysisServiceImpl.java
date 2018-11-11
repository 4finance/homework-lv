package io.fourfinanceit.management.impl;

import io.fourfinanceit.domain.LoanRequestDO;
import io.fourfinanceit.management.RiskAnalysisService;
import io.fourfinanceit.management.RiskRatingResult;
import org.springframework.stereotype.Service;

@Service
public class RiskAnalysisServiceImpl implements RiskAnalysisService {

    @Override
    public RiskRatingResult runAnalysis(final LoanRequestDO loanRequest) {

        return RiskRatingResult.OK;
    }
}

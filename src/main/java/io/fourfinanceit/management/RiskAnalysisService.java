package io.fourfinanceit.management;

import io.fourfinanceit.domain.LoanRequestDO;

public interface RiskAnalysisService {
    RiskRatingResult runAnalysis(LoanRequestDO loanRequest);
}

package io.fourfinanceit.management.impl;

import io.fourfinanceit.constant.RiskRatingResult;
import io.fourfinanceit.domain.LoanRequestDO;
import io.fourfinanceit.management.RiskAnalysisService;
import io.fourfinanceit.service.LoanRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Random;

import static io.fourfinanceit.constant.Settings.*;

@Service
public class RiskAnalysisServiceImpl implements RiskAnalysisService {

    private final LoanRequestService loanRequestService;

    @Autowired
    public RiskAnalysisServiceImpl(final LoanRequestService loanRequestService) {
        this.loanRequestService = loanRequestService;
    }

    @Override
    public RiskRatingResult runAnalysis(final LoanRequestDO loanRequest) {

        final RiskRatingResult riskRatingResult;

        final boolean highRiskLoan = riskyTimeSlot() && maxPossibleAmount(loanRequest) || maxApplicationLimitReached(loanRequest);
        if (highRiskLoan) {
            riskRatingResult = doRunAnalysis(loanRequest);

        } else {
            riskRatingResult = RiskRatingResult.LOW;
        }

        return riskRatingResult;
    }

    private boolean riskyTimeSlot() {
        final LocalTime now = LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault()).toLocalTime();
        return now.isAfter(RISK_TIME_PERIOD_START) && now.isBefore(RISK_TIME_PERIOD_END);
    }

    private boolean maxPossibleAmount(final LoanRequestDO loanRequest) {
        return MAX_LOAN_SUM == loanRequest.getRequestedSum();
    }

    private boolean maxApplicationLimitReached(final LoanRequestDO loanRequest) {
        final Integer countOfRequests = loanRequestService.countByIpAddressForToday(loanRequest.getIpAddress());
        return MAX_REQUESTS_PER_DAY <= countOfRequests;
    }

    private RiskRatingResult doRunAnalysis(final LoanRequestDO loanRequest) {
        //analysis logic here...
        return new Random().nextBoolean() ? RiskRatingResult.MEDIUM : RiskRatingResult.HIGH;
    }
}
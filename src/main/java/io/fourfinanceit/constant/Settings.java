package io.fourfinanceit.constant;

import java.time.LocalTime;

public interface Settings {

    float MAX_LOAN_SUM = 5000f;
    int MAX_REQUESTS_PER_DAY = 3;
    float EXTENSION_INTEREST_RATE = 1.5f;
    float DEFAULT_INTEREST_RATE = 10f;
    LocalTime RISK_TIME_PERIOD_START = LocalTime.MIDNIGHT;
    LocalTime RISK_TIME_PERIOD_END = LocalTime.of(8, 0, 0);

}

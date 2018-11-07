package io.fourfinanceit.homework.config;

import java.math.BigDecimal;
import java.time.LocalTime;

public class Constants {
	public static final int MAX_REQUESTS_PER_DAY = 3;
	public static final int MAX_LOAN_AMOUNT = 1000;
	public static final LocalTime WORKING_HOURS_START = LocalTime.of(9, 0, 0);
	public static final LocalTime WORKING_HOURS_END = LocalTime.of(0, 0, 0);
	public static final BigDecimal INTEREST_FACTOR_PER_WEEK = BigDecimal.valueOf(1.5);
}

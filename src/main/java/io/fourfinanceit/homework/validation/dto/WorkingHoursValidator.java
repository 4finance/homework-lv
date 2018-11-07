package io.fourfinanceit.homework.validation.dto;

import io.fourfinanceit.homework.config.Constants;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class WorkingHoursValidator {
	public boolean isWorkingHours() {
		LocalTime now = LocalTime.now();
		return now.isAfter(Constants.WORKING_HOURS_START) && now.isBefore(Constants.WORKING_HOURS_END);
	}
}

package io.fourfinanceit.homework.schedule;

import io.fourfinanceit.homework.validation.filter.IpValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

	private final IpValidator ipValidator;

	@Autowired
	public ScheduledTasks(IpValidator ipValidator) {
		this.ipValidator = ipValidator;
	}

	@Scheduled(cron = "0 0 0 * * *")
	public void clearRestrictedIP() {
		ipValidator.clear();
	}
}

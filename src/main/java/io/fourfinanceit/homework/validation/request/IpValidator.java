package io.fourfinanceit.homework.validation.request;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import static io.fourfinanceit.homework.config.Constants.MAX_REQUESTS_PER_DAY;

public class IpValidator {

	private Map<String, Long> ipAttempts = new ConcurrentHashMap<>();

	synchronized void registerIpAddressAttempt(String ip) {
		Optional<Long> attempt = Optional.ofNullable(ipAttempts.get(ip));
		Long res = attempt.orElse(0L) + 1;
		ipAttempts.put(ip, res);
	}

	synchronized boolean isIpAddressReachedLimit(String ip) {
		Long attempt = ipAttempts.get(ip);
		return attempt != null && attempt >= MAX_REQUESTS_PER_DAY;
	}

	public void clear() {
		ipAttempts.clear();
	}
}

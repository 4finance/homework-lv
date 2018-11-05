package io.fourfinanceit.homework.app;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class IpValidator {
	private static final int MAX_REQUESTS_PER_DAY = 3;

	private Map<String, Long> ipAttempts = new ConcurrentHashMap<>();


	public synchronized void registerIpAddressAttempt(String ip) {
		//TODO implement ip registration logic
	}

	public synchronized boolean isIpAddressReachedLimit(String ip) {
		Long attempt = ipAttempts.get(ip);
		return attempt != null && attempt >= MAX_REQUESTS_PER_DAY;
	}

	public void clear() {
		ipAttempts.clear();
	}
}

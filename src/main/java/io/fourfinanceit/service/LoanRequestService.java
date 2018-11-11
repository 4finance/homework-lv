package io.fourfinanceit.service;

import io.fourfinanceit.domain.LoanRequestDO;

public interface LoanRequestService {

    LoanRequestDO create(LoanRequestDO request);

    Integer countByIpAddressForToday(String ipAddress);
}

package io.fourfinanceit.repository;

import io.fourfinanceit.domain.LoanRequestDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRequestRepository extends JpaRepository<LoanRequestDO, Long> {

    @Query("SELECT COUNT(req) FROM LoanRequestDO req WHERE req.ipAddress = ?1 AND req.created = CURRENT_DATE")
    Integer countByIpAddressForToday(String ipAddress);
}

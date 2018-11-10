package io.fourfinanceit.repository;

import io.fourfinanceit.domain.LoanRequestDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRequestRepository extends JpaRepository<LoanRequestDO, Long> {
}

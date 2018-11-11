package io.fourfinanceit.repository;

import io.fourfinanceit.domain.LoanExtensionRequestDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanExtensionRequestRepository extends JpaRepository<LoanExtensionRequestDO, Long> {
}

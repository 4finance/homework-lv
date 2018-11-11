package io.fourfinanceit.repository;

import io.fourfinanceit.domain.LoanExtensionDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanExtensionRepository extends JpaRepository<LoanExtensionDO, Long> {
}

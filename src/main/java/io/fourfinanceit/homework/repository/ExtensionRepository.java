package io.fourfinanceit.homework.repository;

import io.fourfinanceit.homework.model.entity.LoanExtension;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExtensionRepository extends JpaRepository<LoanExtension, Long> {
}

package io.fourfinanceit.homework.repository;

import io.fourfinanceit.homework.model.entity.Risk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RiskRepository extends JpaRepository<Risk, Long> {
}

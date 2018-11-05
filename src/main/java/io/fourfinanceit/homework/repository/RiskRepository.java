package io.fourfinanceit.homework.repository;

import io.fourfinanceit.homework.model.Rejection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RiskRepository extends JpaRepository<Rejection, Long> {
}

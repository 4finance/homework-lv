package lv.finance.homework.repository;

import lv.finance.homework.model.Rejection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RiskRepository extends JpaRepository<Rejection, Long> {
}

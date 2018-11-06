package io.fourfinanceit.homework.repository;

import io.fourfinanceit.homework.model.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
	List<Loan> findByCustomerId (Long customerId);
}

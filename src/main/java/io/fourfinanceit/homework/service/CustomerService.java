package io.fourfinanceit.homework.service;

import io.fourfinanceit.homework.model.dto.CustomerDto;
import io.fourfinanceit.homework.model.dto.LoanDto;
import io.fourfinanceit.homework.model.dto.TermDto;
import io.fourfinanceit.homework.model.entity.Loan;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
	public void createCustomer(CustomerDto customer) {

	}

	public List<Loan> findLoans(String customerId) {
		return null;
	}

	public void createLoan(String customerId, LoanDto loan) {

	}

	public void extendLoan(String customerId, String loanId, TermDto term) {

	}
}

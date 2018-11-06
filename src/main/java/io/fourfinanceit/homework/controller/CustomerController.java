package io.fourfinanceit.homework.controller;

import io.fourfinanceit.homework.model.dto.CustomerDto;
import io.fourfinanceit.homework.model.dto.LoanDto;
import io.fourfinanceit.homework.model.dto.TermDto;
import io.fourfinanceit.homework.model.entity.Loan;
import io.fourfinanceit.homework.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api")
public class CustomerController {

	private final CustomerService service;

	@Autowired
	public CustomerController(CustomerService service) {
		this.service = service;
	}

	@PostMapping("customer")
	public ResponseEntity<?> createCustomer(@Valid @RequestBody CustomerDto customer) {
		service.createCustomer(customer);
		return ResponseEntity.ok().build();
	}

	@GetMapping("customers/{customerId}/loans")
	public List<Loan> getCustomerLoans(@PathVariable("customerId") Long customerId) {
		return service.findLoans(customerId);
	}

	@PostMapping("customers/{customerId}/loan")
	public ResponseEntity<?> createLoan(@PathVariable(name = "customerId") Long customerId, @Valid @RequestBody LoanDto loan) {
		service.createLoan(customerId, loan);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PostMapping("customers/{customerId}/loans/{loanId}/extend")
	public ResponseEntity<?> extendLoan(@PathVariable("customerId") Long customerId, @PathVariable("loanId") Long loanId, @Valid @RequestBody TermDto term) {
		service.extendLoan(customerId, loanId, term);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}

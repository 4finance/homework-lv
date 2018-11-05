package io.fourfinanceit.homework.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class CustomerController {

	@PostMapping("customer")
	public ResponseEntity<?> createCustomer() {
		return ResponseEntity.badRequest().build();
	}

	@GetMapping("customers/{customerId}/loans")
	public ResponseEntity<?> getCustomerLoans(@PathVariable("customerId") String customerId) {
		return ResponseEntity.badRequest().build();
	}

	@PostMapping("customers/{customerId}/loan")
	public ResponseEntity<?> createLoan(@PathVariable("customerId") String customerId) {
		return ResponseEntity.badRequest().build();
	}

	@PostMapping("customers/{customerId}/loans/{loanId}/extend")
	public ResponseEntity<?> extendLoan(@PathVariable("customerId") String customerId, @PathVariable("loanId") String loanId) {
		return ResponseEntity.badRequest().build();
	}
}

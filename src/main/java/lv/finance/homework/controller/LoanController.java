package lv.finance.homework.controller;

import lv.finance.homework.model.Loan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LoanController {


	@GetMapping("/loans")
	public ResponseEntity<List<Loan>> getLoans(Long customerId) {
		return ResponseEntity.notFound().build();
	}

	@PostMapping("/loan")
	public ResponseEntity<?> createLoan() {
		return ResponseEntity.badRequest().build();
	}

	@PostMapping("/loans/{loanId}/extend")
	public ResponseEntity<?> extendLoan(@PathVariable Long loanId) {
		return ResponseEntity.badRequest().build();
	}
}
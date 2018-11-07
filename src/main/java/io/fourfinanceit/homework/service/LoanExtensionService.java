package io.fourfinanceit.homework.service;

import io.fourfinanceit.homework.model.entity.Loan;
import io.fourfinanceit.homework.model.entity.LoanExtension;
import io.fourfinanceit.homework.repository.ExtensionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LoanExtensionService {

	private final ExtensionRepository extensionRepository;

	@Autowired
	public LoanExtensionService(ExtensionRepository extensionRepository) {
		this.extensionRepository = extensionRepository;
	}

	public void extendLoan(Loan loan, LocalDateTime term) {
		LoanExtension extension = LoanExtension.builder().loan(loan).term(term).build();
		extensionRepository.saveAndFlush(extension);
	}
}

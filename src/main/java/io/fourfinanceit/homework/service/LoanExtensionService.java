package io.fourfinanceit.homework.service;

import io.fourfinanceit.homework.model.entity.Loan;
import io.fourfinanceit.homework.model.entity.LoanExtension;
import io.fourfinanceit.homework.repository.ExtensionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static io.fourfinanceit.homework.config.Constants.INTEREST_FACTOR_PER_WEEK;

@Service
public class LoanExtensionService {

	private final ExtensionRepository extensionRepository;

	@Autowired
	public LoanExtensionService(ExtensionRepository extensionRepository) {
		this.extensionRepository = extensionRepository;
	}

	public void extendLoan(Loan loan, LocalDateTime newTerm) {
		List<LoanExtension> extensions = loan.getExtensions();
		Optional<LoanExtension> lastExtension = extensions.stream().max(Comparator.comparing(LoanExtension::getTerm));
		BigDecimal lastAmount = lastExtension.map(LoanExtension::getAmount).orElse(loan.getAmount());
		LocalDateTime lastTerm = lastExtension.map(LoanExtension::getTerm).orElse(loan.getTerm());

		long weeksInExtension = ChronoUnit.WEEKS.between(newTerm, lastTerm);
		BigDecimal newAmount = lastAmount;

		for (int i = 0; i < weeksInExtension; i++) {
			newAmount = newAmount.multiply(INTEREST_FACTOR_PER_WEEK);
		}

		LoanExtension extension = LoanExtension.builder().loan(loan).term(newTerm).amount(newAmount).build();
		extensionRepository.saveAndFlush(extension);
	}
}

package io.fourfinanceit.homework.validation.request;

import io.fourfinanceit.homework.model.RejectionReason;
import io.fourfinanceit.homework.model.Risk;
import io.fourfinanceit.homework.repository.RiskRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class RiskFilter {

	@Autowired
	private RiskRepository riskRepository;

	void registerRisk() {
		Risk risk = Risk.builder().rejectionReason(getRejectionReason()).build();
		riskRepository.save(risk);
	}

	protected abstract RejectionReason getRejectionReason();
}

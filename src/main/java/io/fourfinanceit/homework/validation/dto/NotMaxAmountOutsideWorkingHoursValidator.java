package io.fourfinanceit.homework.validation.dto;

import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

public class NotMaxAmountOutsideWorkingHoursValidator implements ConstraintValidator<NotMaxAmountOutsideWorkingHours, BigDecimal> {

	private long max;

	@Autowired
	private WorkingHoursValidator workingHoursValidator;

	@Override
	public void initialize(NotMaxAmountOutsideWorkingHours constraintAnnotation) {
		this.max = constraintAnnotation.max();
	}

	@Override
	public boolean isValid(BigDecimal value, ConstraintValidatorContext context) {
		return value.compareTo(BigDecimal.valueOf(max)) < 0 && workingHoursValidator.isWorkingHours();
	}
}

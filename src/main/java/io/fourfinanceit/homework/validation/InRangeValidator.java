package io.fourfinanceit.homework.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

public class InRangeValidator implements ConstraintValidator<InRange, BigDecimal> {

	private long min;
	private long max;

	@Override
	public void initialize(InRange constraintAnnotation) {
		this.min = constraintAnnotation.min();
		this.max = constraintAnnotation.max();
	}

	@Override
	public boolean isValid(BigDecimal value, ConstraintValidatorContext context) {
		return value != null && (value.compareTo(BigDecimal.valueOf(min)) > 0 && value.compareTo(BigDecimal.valueOf(max)) <= 0);
	}
}

package io.fourfinanceit.homework.validation.dto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;

public class NotInPastValidator implements ConstraintValidator<NotInPast, LocalDateTime> {

	@Override
	public void initialize(NotInPast constraintAnnotation) {
	}

	@Override
	public boolean isValid(LocalDateTime value, ConstraintValidatorContext context) {
		return value.isAfter(LocalDateTime.now());
	}
}

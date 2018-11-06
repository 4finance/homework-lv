package io.fourfinanceit.homework.validation.dto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;

public class NotInPastValidator implements ConstraintValidator<NotInPast, Date> {

	@Override
	public void initialize(NotInPast constraintAnnotation) {
	}

	@Override
	public boolean isValid(Date value, ConstraintValidatorContext context) {
		return value.after(new Date());
	}
}

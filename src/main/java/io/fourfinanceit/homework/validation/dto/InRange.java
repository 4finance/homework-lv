package io.fourfinanceit.homework.validation.dto;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {InRangeValidator.class})
public @interface InRange {
	String message() default "Value is out of range";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	long min();

	long max();
}

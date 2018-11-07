package io.fourfinanceit.homework.validation.dto;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {NotMaxAmountOutsideWorkingHoursValidator.class})
public @interface NotMaxAmountOutsideWorkingHours {
	String message() default "Max amount outside working hours";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	long max();
}

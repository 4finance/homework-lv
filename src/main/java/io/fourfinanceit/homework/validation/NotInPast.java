package io.fourfinanceit.homework.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = { DateValidator.class })
public @interface NotInPast {
    String message() default "Date set in past";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

package ai.wealth.boot.initiator.exception.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = MaxUserRoleConstraintValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface MaxUserRoleConstraint {
	 String message() default "The Authorities list cannot contain more than 4 Authorities.";
	    Class<?>[] groups() default {};
	    Class<? extends Payload>[] payload() default {};
}

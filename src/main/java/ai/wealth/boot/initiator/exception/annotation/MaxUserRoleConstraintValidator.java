package ai.wealth.boot.initiator.exception.annotation;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import ai.wealth.boot.initiator.configuration.security.model.Authorities;

public class MaxUserRoleConstraintValidator implements  ConstraintValidator<MaxUserRoleConstraint, List<Authorities>> {

	@Override
	public boolean isValid(List<Authorities> values, ConstraintValidatorContext context) {
		 return values.size() <= 2;
	}

}

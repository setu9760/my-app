package spring.desai.common.pojo.validators;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import spring.desai.common.model.pojo.Subject;

@Component
@Cacheable
public class SubjectValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Subject.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "id.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.required");
	}

}

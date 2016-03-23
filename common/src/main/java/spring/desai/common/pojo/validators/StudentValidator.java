package spring.desai.common.pojo.validators;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import spring.desai.common.model.pojo.Student;

@Component
@Cacheable
public class StudentValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Student.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "id.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "address.required");
		Student student = (Student) target;
		if (student.getAge() < 18) {
			errors.rejectValue("age", "age.too.low");
		} else if (student.getAge() > 65) {
			errors.rejectValue("age", "age.too.high");
		}
	}
}

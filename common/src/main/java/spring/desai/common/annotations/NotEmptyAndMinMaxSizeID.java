package spring.desai.common.annotations;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@NotEmpty
@Documented
@Retention(RUNTIME)
@Size(min = 3, max = 36)
@ReportAsSingleViolation
@Constraint(validatedBy = {})
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
public @interface NotEmptyAndMinMaxSizeID {
	String message() default "id must be between 3 and 36 characters";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}

package spring.desai.webconsole.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;

import spring.desai.common.model.pojo.Student;
import spring.desai.common.model.pojo.Subject;
import spring.desai.common.model.pojo.Tutor;
import spring.desai.common.pojo.validators.StudentValidator;
import spring.desai.common.pojo.validators.SubjectValidator;
import spring.desai.common.pojo.validators.TutorValidator;
import spring.desai.common.utils.GuidGeneratorException;

@Configuration
public class PojoBeansConfig {

//	@Bean(name = "studentBean")
//	public Student getStudentBean() {
//		try {
//			return new Student("student 1", 20);
//		} catch (GuidGeneratorException e) {
//			return null;
//		}
//	}
//
//	@Bean(name = "tutorBean")
//	public Tutor getTutorBean() {
//
//		try {
//			return new Tutor("tutor 1", getSubjectBean());
//		} catch (GuidGeneratorException e) {
//			return null;
//		}
//	}
//
//	@Bean(name = "subjectBean")
//	public Subject getSubjectBean() {
//
//		try {
//			return new Subject("subject 1");
//		} catch (GuidGeneratorException e) {
//			return null;
//		}
//	}
//
//	@Bean(name = "studentValidator")
//	public Validator getStudentValudatior() {
//		return new StudentValidator();
//	}
//
//	@Bean(name = "tutorValidator")
//	public Validator getTutorValidator() {
//		return new TutorValidator();
//	}
//
//	@Bean(name = "subjectValidator")
//	public Validator getSubjectValidator() {
//		return new SubjectValidator();
//	}
}

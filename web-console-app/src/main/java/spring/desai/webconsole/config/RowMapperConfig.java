package spring.desai.webconsole.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

import spring.desai.common.model.Student;
import spring.desai.common.model.Subject;
import spring.desai.common.model.Tutor;
import spring.desai.common.rowmappers.StudentRowMapper;
import spring.desai.common.rowmappers.SubjectRowMapper;
import spring.desai.common.rowmappers.TutorRowMapper;

@Configuration
public class RowMapperConfig {

	@Bean
	public RowMapper<Student> getStudentMapper() {
		return new StudentRowMapper();
	}

	@Bean
	public RowMapper<Subject> getSubjectMapper() {
		return new SubjectRowMapper();
	}

	@Bean
	public RowMapper<Tutor> getTutorMapper() {
		return new TutorRowMapper();
	}

}

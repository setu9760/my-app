package spring.desai.webconsole.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.desai.common.service.ReadOnlyService;
import spring.desai.common.service.StudentAdminService;
import spring.desai.webconsole.service.impl.ReadOnlyServiceImpl;
import spring.desai.webconsole.service.impl.StudentAdminServiceImpl;

@Configuration
public class ServiceConfig {

	@Bean(name = "readOnlyService")
	public ReadOnlyService getReadOnlyService() {
		return new ReadOnlyServiceImpl();
	}

	@Bean(name = "studentAdminService")
	public StudentAdminService getStudentAdminService() {
		return new StudentAdminServiceImpl();
	}

}

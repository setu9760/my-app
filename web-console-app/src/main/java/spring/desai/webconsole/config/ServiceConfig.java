package spring.desai.webconsole.config;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;

import spring.desai.common.service.AdminUserMaintananceService;
import spring.desai.common.service.ReadOnlyService;
import spring.desai.common.service.SchoolService;
import spring.desai.webconsole.service.impl.AdminUserMaintananceServiceImpl;
import spring.desai.webconsole.service.impl.ReadOnlyServiceImpl;
import spring.desai.webconsole.service.impl.SchoolServiceImpl;

@Configuration
// TODO below component scan can be uncommented once all the services are implemented and all of their dependencies are satisfied.
//@ComponentScan({"spring.desai.webconsole.service.impl"})
public class ServiceConfig {

	@Bean(name = "readOnlyService")
	public ReadOnlyService getReadOnlyService() {
		return new ReadOnlyServiceImpl();
	}

	@Bean(name = "studentAdminService")
	public SchoolService getStudentAdminService() {
		return new SchoolServiceImpl();
	}
	
	@Bean(name = "adminUserMaintananceService")
	public AdminUserMaintananceService getAdminUserMaintananceService(){
		return new AdminUserMaintananceServiceImpl();
	}

}

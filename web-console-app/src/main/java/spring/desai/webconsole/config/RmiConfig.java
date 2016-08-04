package spring.desai.webconsole.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.remoting.rmi.RmiServiceExporter;

import spring.desai.common.service.AdminUserMaintananceService;
import spring.desai.common.service.ReadOnlyService;

@Configuration
@Profile("rmiEnabled")
public class RmiConfig {


	@Bean(name = "readonlyServiceExporter")
	public RmiServiceExporter exportReadonlyService() throws Exception {
		RmiServiceExporter exporter = new RmiServiceExporter();
		exporter.setServiceName("readonlyService");
		exporter.setService(ReadOnlyService.class);
		exporter.setRegistryPort(9595);
		return exporter;
	}

	@Bean(name = "adminServiceExporter")
	public RmiServiceExporter exportAdminService() throws Exception {
		RmiServiceExporter exporter = new RmiServiceExporter();
		exporter.setServiceName("adminService");
		exporter.setService(AdminUserMaintananceService.class);
		exporter.setRegistryPort(9595);
		return exporter;
	}
	
}

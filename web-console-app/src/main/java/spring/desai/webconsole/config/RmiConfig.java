package spring.desai.webconsole.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.remoting.rmi.RmiServiceExporter;

import spring.desai.common.service.AdminUserMaintananceService;
import spring.desai.common.service.ReadOnlyService;

@Configuration
@Profile("rmiEnabled")
public class RmiConfig {

	@Autowired
	private ReadOnlyService readOnlyService;
	
	@Autowired
	private AdminUserMaintananceService adminUserMaintananceService;
	
	@Value("${rmi.port:9595}")
	private int rmiPort;
	
	@Bean(name = "readonlyServiceExporter")
	public RmiServiceExporter exportReadonlyService() throws Exception {
		RmiServiceExporter exporter = new RmiServiceExporter();
		exporter.setServiceName("readOnlyService");
		exporter.setServiceInterface(ReadOnlyService.class);
		exporter.setService(readOnlyService);
		exporter.setRegistryPort(rmiPort);
		return exporter;
	}

	@Bean(name = "adminServiceExporter")
	public RmiServiceExporter exportAdminService() throws Exception {
		RmiServiceExporter exporter = new RmiServiceExporter();
		exporter.setServiceName("adminUserMaintananceService");
		exporter.setService(adminUserMaintananceService);
		exporter.setServiceInterface(AdminUserMaintananceService.class);
		exporter.setRegistryPort(rmiPort);
		return exporter;
	}
	
}

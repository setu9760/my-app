package spring.desai.webconsole.config;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.remoting.rmi.RmiServiceExporter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import spring.desai.common.repository.StudentRepository;
import spring.desai.common.repository.impl.StudentRepositoryImpl;
import spring.desai.common.service.AdminService;
import spring.desai.common.service.ReadOnlyService;
import spring.desai.common.utils.GuidGenerator;

@EnableWebMvc
@Configuration
@ComponentScan({ "spring.desai.*"/*, "spring.desai.webconsole.*", "spring.desai.webconsole.config.*", "spring.desai.common.*",
		"spring.desai.webconsole.core.*", "spring.desai.webconsole.config.aspects.*",
		"spring.desai.webconsole.controllers.*", "spring.desai.webconsole.JdbcDaoImpl.*" */ })
@Import({ PojoBeansConfig.class, RowMapperConfig.class /*, SecurityConfig.class*/ })
@EnableAspectJAutoProxy
@EnableTransactionManagement
public class Config {

	private final String DATASOURCE_JNDI = Config_properties.getString("Config.datasource.jndi");

	// private final String DATASOURCE2_JNDI =
	// Config_properties.getString("Config.datasource2.jndi");

	@Bean(name = "dataSource", destroyMethod = "")
	public DataSource getDatasource() throws NamingException {
		return new JndiDataSourceLookup().getDataSource(DATASOURCE_JNDI);
	}

	@Bean(name = "transactionManager")
	public DataSourceTransactionManager getDataSourceTransactionManager() throws NamingException {
		return new DataSourceTransactionManager(getDatasource());
	}

	@Bean(name = "transactionTemplate")
	public TransactionTemplate getTransactionTemplate() throws NamingException {
		return new TransactionTemplate(getDataSourceTransactionManager());
	}

	@Bean(name = "guidGenerator")
	public GuidGenerator getGuidGenerator() {
		return GuidGenerator.getInstance();
	}

	@Bean(name = "studentRepository")
	public StudentRepository getStudentRepo() throws Exception {
		return new StudentRepositoryImpl();
	}
	
	@Bean(name = "tutorRepository")
	public StudentRepository getTutorRepo() throws Exception {
		return new StudentRepositoryImpl();
	}

	@Bean(name = "subjectRepository")
	public StudentRepository getSubjectRepo() throws Exception {
		return new StudentRepositoryImpl();
	}

	@Bean(name = "messageSource")
	public ResourceBundleMessageSource getMessageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
	}

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
		exporter.setService(AdminService.class);
		exporter.setRegistryPort(9595);
		return exporter;
	}
	
	/************************
	 * 
	 * 
	 *************************/

	// @Bean(name = "dataSource2")
	// public DataSource getDatasource2() throws NamingException {
	// JndiTemplate jndiTemplate = new JndiTemplate();
	// DataSource dataSource = (DataSource) jndiTemplate
	// .lookup(DATASOURCE2_JNDI);
	// return dataSource;
	// }

}

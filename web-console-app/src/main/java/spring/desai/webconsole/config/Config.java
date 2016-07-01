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
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import spring.desai.common.service.AdminUserMaintananceService;
import spring.desai.common.service.ReadOnlyService;

@EnableWebMvc
@Configuration
@ComponentScan({ "spring.desai.webconsole.*", "spring.desai.common.repository.impl.*", "spring.desai.common.rowmappers", 
		"spring.desai.secure.service", "spring.desai.secure.repository"
		/*, "spring.desai.webconsole.*", "spring.desai.webconsole.config.*", "spring.desai.common.*",
		"spring.desai.webconsole.core.*", "spring.desai.webconsole.config.aspects.*",
		"spring.desai.webconsole.controllers.*", "spring.desai.webconsole.JdbcDaoImpl.*" */ })
@Import({ /*PojoBeansConfig.class,*/ RowMapperConfig.class , SecurityConfig.class })
@EnableAspectJAutoProxy
@EnableTransactionManagement
public class Config {

	private final String DATASOURCE_JNDI = Config_properties.getString("Config.datasource.jndi");

	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/resources/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
	@Bean(name = "dataSource")
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
		exporter.setService(AdminUserMaintananceService.class);
		exporter.setRegistryPort(9595);
		return exporter;
	}
}

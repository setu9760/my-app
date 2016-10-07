package spring.desai.webconsole.controllers;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import spring.desai.webconsole.config.Config;

@Configuration
@Import(Config.class)
public class MvcTestConfig {

	@Bean(name = "dataSource")
	public DataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:file:C:/junit/testing;DB_CLOSE_ON_EXIT=true");
		dataSource.setUsername("sa");
		dataSource.setPassword("password");
		DatabasePopulatorUtils.execute(getDatabasePopulator(), dataSource);
		return dataSource;
	}
	
	@Value("classpath:/sql/drop-ddl.sql")
	private Resource recreateDDL;
	@Value("classpath:/sql/ddl.sql")
	private Resource ddl;
	@Value("classpath:/sql/dml.sql")
	private Resource dml;

	private DatabasePopulator getDatabasePopulator() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.setContinueOnError(true);
		populator.addScripts(recreateDDL, ddl, dml);
		return populator;
	}
}

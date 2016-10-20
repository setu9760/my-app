package spring.desai.common.repository.tests.jdbc;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import spring.desai.common.repository.StudentRepository;
import spring.desai.common.repository.impl.jdbc.StudentRepositoryImpl;

//@Configuration
public class Configs {
	
	@Bean(name = "dataSource")
	public DataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/test_test");
		dataSource.setUsername("desai");
		dataSource.setPassword("password");
		DatabasePopulatorUtils.execute(getDatabasePopulator(), dataSource);
		return dataSource;
	}

	@Bean(name = "transactionManager")
	public PlatformTransactionManager getTransactionManager() {
		return new DataSourceTransactionManager(getDataSource());
	}

	@Bean(name = "studentRepository")
	public StudentRepository getStudentRepository() {
		return new StudentRepositoryImpl();
	}

	@Value("sql/drop-schema.sql")
	private Resource recreateDDL;
	@Value("sql/ddl.sql")
	private Resource ddl;
	@Value("sql/dml.sql")
	private Resource dml;

	private DatabasePopulator getDatabasePopulator() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.setContinueOnError(true);
		populator.addScripts(recreateDDL, ddl, dml);
		return populator;
	}
}

package spring.desai.common.repository.tests.jdbc;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@ComponentScan(basePackages = { "spring.desai.common.rowmappers, spring.desai.common.repository.impl.jdbc" })
public class JdbcTestConfigs {

	@Bean(name = "dataSource")
	public DataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;MVCC=TRUE;DATABASE_TO_UPPER=FALSE");
		dataSource.setUsername("sa");
		dataSource.setPassword("password");
		DatabasePopulatorUtils.execute(getDatabasePopulator(), dataSource);
		return dataSource;
	}

	@Bean(name = "transactionManager")
	public PlatformTransactionManager getTransactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean(name = "jdbcTemplate")
	public JdbcTemplate getJdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource, false);
	}

	@Value("classpath:sql/drop-ddl.sql")
	private Resource recreateDDL;
	@Value("classpath:sql/ddl.sql")
	private Resource ddl;
	@Value("classpath:sql/dml.sql")
	private Resource dml;

	private DatabasePopulator getDatabasePopulator() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.setContinueOnError(true);
		populator.addScripts(recreateDDL, ddl, dml);
		return populator;
	}
}

package spring.desai.webconsole.config;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;


@Configuration
@Profile("jdbc")
@ComponentScan("spring.desai.common.repository.impl.jdbc")
public class JdbcConfig {

	private final String DATASOURCE_JNDI = ConfigProperties.getString("config.datasource.jndi");
	
	/**
	 * <b>NOTE: Added destroyMethod attribute to bean definition as the default behaviour calls close() on datasource thus not allowing redeploy of application </b>
	 * @return
	 * @throws NamingException
	 */
	@Bean(name = "dataSource", destroyMethod= "")
	public DataSource getDatasource() throws NamingException {
		return new JndiDataSourceLookup().getDataSource(DATASOURCE_JNDI);
	}
	
	@Bean(name = "jdbcTemplate")
	public JdbcTemplate getJdbcTemplate() throws Exception{
		return new JdbcTemplate(getDatasource());
	}

	@Bean(name = "transactionManager")
	public DataSourceTransactionManager getDataSourceTransactionManager() throws NamingException {
		return new DataSourceTransactionManager(getDatasource());
	}
}

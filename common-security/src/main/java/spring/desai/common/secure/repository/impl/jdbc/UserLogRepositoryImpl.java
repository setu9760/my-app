package spring.desai.common.secure.repository.impl.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import spring.desai.common.repository.UserLogRepository;
import spring.desai.common.repository.exception.RepositoryDataAccessException;

@Repository("userLogRepository")
public class UserLogRepositoryImpl implements UserLogRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final String INSERT_USER_LOG_SQL = "INSERT INTO USER_LOG (user_id, user_function, ipaddress) VALUES (?, ?, ?)";

	@Override
	public void logUserActivity(String userId, int userFunction, String ipAddress) throws RepositoryDataAccessException {
		getJdbcTemplate().update(INSERT_USER_LOG_SQL, new Object[] { userId, userFunction, ipAddress });
	}

	private JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

}

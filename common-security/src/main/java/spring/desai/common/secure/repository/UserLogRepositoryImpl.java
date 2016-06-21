package spring.desai.common.secure.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import spring.desai.common.repository.UserLogRepository;
import spring.desai.common.repository.exception.RepositoryDataAccessException;

@Repository("userLogRepository")
public class UserLogRepositoryImpl implements UserLogRepository{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void logUserActivity(String userId, int activity) {
		// TODO Auto-generated method stub
		try {
			getJdbcTemplate();
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException("Error occured while logging user activity to database", e);
		}
	}
	
	private JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

}

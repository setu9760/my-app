package spring.desai.common.secure.repository.impl.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import spring.desai.common.model.User.SIGN_ON_STATUS;
import spring.desai.common.repository.UsrrRepository;
import spring.desai.common.repository.exception.RepositoryDataAccessException;
import spring.desai.common.secure.user.UserLoginDetails;

@Repository("usrrRepository")
public class UsrrRepositoryImpl implements UsrrRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static final String USER_LOGIN_DETAIL_SQL = "SELECT UD.USER_ID, U.PASSWORD, UD.ACCOUNT_NON_LOCKED,  U.PASSWORD_NON_EXPIRED, UD.FAILED_ATTEMPTS , UD.SIGN_ON_STATUS  FROM USER_DETAILS UD JOIN USRR U ON LOWER(UD.USER_ID) = LOWER(U.USER_ID) WHERE LOWER(U.USER_ID) = LOWER(?)";
	private static final String INCREMENT_FAILED_ATTEMPT_SQL = "UPDATE USER_DETAILS SET FAILED_ATTEMPTS  = FAILED_ATTEMPTS+1 WHERE LOWER(USER_ID) = LOWER(?)";
	private static final String RESET_FAILED_ATTEMPTS_SQL = "UPDATE USER_DETAILS SET FAILED_ATTEMPTS  = 0 WHERE LOWER(USER_ID) = LOWER(?)";
	private static final String UPDATE_LOGIN_STATUS_SQL = "UPDATE USER_DETAILS SET SIGN_ON_STATUS = ? WHERE LOWER(USER_ID) = LOWER(?)";
	private static final String LOCK_USER_ACCOUNT_SQL = "UPDATE USER_DETAILS SET ACCOUNT_NON_LOCKED = 'false' WHERE LOWER(USER_ID) = LOWER (?)";
	private static final String UNLOCK_USER_ACCOUNT_SQL = "UPDATE USER_DETAILS SET ACCOUNT_NON_LOCKED = 'true' WHERE LOWER(USER_ID) = LOWER (?)";
	
	@Override
	public UserDetails getUserLoginDetails(String userId)  throws RepositoryDataAccessException{
		try {
			List<UserDetails> list = getJdbcTemplate().query(USER_LOGIN_DETAIL_SQL, new Object[] { userId }, new RowMapper<UserDetails>(){

				@Override
				public UserDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
					UserDetails ud = new UserLoginDetails(rs.getString(1), rs.getString(2), rs.getBoolean(3), rs.getBoolean(4), rs.getInt(5), SIGN_ON_STATUS.valueOf(rs.getString(6)));
					return ud;
				}
				
			});
			if (list != null && !list.isEmpty()) {
				return list.get(0);
			}
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException("Error occured while getting user login details from database.", e);
		}
		return null;
	}
	
	@Override
	public void incrementFailedAttempt(String userId) throws RepositoryDataAccessException{
		try {
			getJdbcTemplate().update(INCREMENT_FAILED_ATTEMPT_SQL, new Object[] { userId });
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException("Error occured while updating failed login attempt.", e);
		}
	}
	
	@Override
	public void resetFailedAttempt(String userId) throws RepositoryDataAccessException {
		try {
			getJdbcTemplate().update(RESET_FAILED_ATTEMPTS_SQL, new Object[] { userId });
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException("Error occured while resetting failed attempts.", e);
		}
	}
	
	public void updateSignOnStatus(String userId, SIGN_ON_STATUS status) throws RepositoryDataAccessException{
		try {
			getJdbcTemplate().update(UPDATE_LOGIN_STATUS_SQL, new Object[] { String.valueOf(status), userId});
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException("Error occured while setting sign-on status for user " + userId, e);
		}
	}
	
	@Override
	public void lockUserAccount(String userId) throws RepositoryDataAccessException {
		try {
			getJdbcTemplate().update(LOCK_USER_ACCOUNT_SQL, new Object[] { userId });
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException("Error occured while locking the user account.", e);
		}
	}
	
	@Override
	public void unlockUserAccount(String userId) throws RepositoryDataAccessException {
		try {
			getJdbcTemplate().update(UNLOCK_USER_ACCOUNT_SQL, new Object[] { userId });
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException("Error occured while unlocking the user account.", e);
		}
	}
	
	@Override
	public void createUser(String userId, String encryptedPassword)  throws RepositoryDataAccessException{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void deleteUser(String userId) throws RepositoryDataAccessException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void updatePassword(String userId, String encryptedPassword) throws RepositoryDataAccessException {
		// TODO Auto-generated method stub
		
	}
	
	private JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

}

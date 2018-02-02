package spring.desai.common.repository.impl.jdbc;

import static spring.desai.common.utils.DataBaseConstants.ACCOUNT_NON_LOCKED;
import static spring.desai.common.utils.DataBaseConstants.FAILED_ATTEMPTS;
import static spring.desai.common.utils.DataBaseConstants.F_NAME;
import static spring.desai.common.utils.DataBaseConstants.SIGN_ON_STATUS;
import static spring.desai.common.utils.DataBaseConstants.USER_ID;
import static spring.desai.common.utils.DataBaseConstants.USER_TABLE_NAME;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import spring.desai.common.model.User;
import spring.desai.common.repository.UserRepository;
import spring.desai.common.repository.exception.RepositoryDataAccessException;

@Repository("userRepository")
public class UserRepositoryImpl extends BaseJdbcRepository<User> implements UserRepository {

	private static final int MAX_FAILED_ATTEMPTS = 4;
	private final String FIND_MAX_ATTEMPTS_USER_SQL = "SELECT * FROM " + getTableName() + " WHERE " + FAILED_ATTEMPTS + " >= ?";
	private final String RESET_LOCKED_OUT_USER_SQL = "UPDATE " + getTableName() + " SET " + ACCOUNT_NON_LOCKED + " = 'true' WHERE " + USER_ID + " = ? ";
	
	@Override
	protected String getInsertSql() {
		return "INSERT INTO user_details (user_id, f_name, l_name, address, failed_attempts, account_non_locked, sign_on_status) VALUES (?, ?, ?, ?, ?, ?, ?)";
	}

	@Override
	protected String getUpdateSql() {
		return "UPDATE user_details SET f_name = ?, l_name = ?, address = ? " +/**, failed_attempts = ?, account_non_locked = ?, sign_on_status = ?**/ " WHERE user_id = ?";
	}

	@Override
	protected RowMapper<User> getRowMapper() {
		return userRowMapper;
	}

	@Override
	protected String getTableName() {
		return USER_TABLE_NAME;
	}

	@Override
	protected String getNameField() {
		return F_NAME;
	}
	
	@Override
	protected String getIdField() {
		return USER_ID;
	}

	@Override
	protected PreparedStatementSetter getInsertPreparedStatementSetter(final User u) {
		return ps -> {
				ps.setString(1, u.getId());
				ps.setString(2, u.getFirstname());
				ps.setString(3, u.getLastname());
				ps.setString(4, u.getAddress());
				ps.setInt(5, u.getFailedAttempts());
				ps.setString(6, String.valueOf(u.isAccountNonLocked()));
				ps.setString(7, String.valueOf(u.getSignOnStatus()));
		};
	}

	@Override
	protected PreparedStatementSetter getUpdatePreparedStatementSetter(final User u) {
		return ps -> {
				ps.setString(1, u.getFirstname());
				ps.setString(2, u.getLastname());
				ps.setString(3, u.getAddress());
				ps.setString(4, u.getId());
		};
	}

	@Override
	protected BatchPreparedStatementSetter getInsertBatchPreparedStatementSetter(final Collection<User> c) {
		final List<User> l = new ArrayList<>(c);
		return new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				User u = l.get(i);
				ps.setString(1, u.getId());
				ps.setString(2, u.getFirstname());
				ps.setString(3, u.getLastname());
				ps.setString(4, u.getAddress());
				ps.setInt(5, u.getFailedAttempts());
				ps.setString(6, String.valueOf(u.isAccountNonLocked()));
				ps.setString(7, String.valueOf(u.getSignOnStatus()));
			}
			
			@Override
			public int getBatchSize() {
				return l.size();
			}
		};
	}

	@Override
	protected BatchPreparedStatementSetter getUpdateBatchPreparedStatementSetter(final Collection<User> c) {
		final List<User> l = new ArrayList<>(c);
		return new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				User u = l.get(i);
				ps.setString(1, u.getFirstname());
				ps.setString(2, u.getLastname());
				ps.setString(3, u.getAddress());
				ps.setString(4, u.getId());
			}
			
			@Override
			public int getBatchSize() {
				return l.size();
			}
		};
	}

	@Override
	public Collection<User> findLockedOutUsers() throws RepositoryDataAccessException {
		return getJdbcTemplate().query(getFindBySql(getTableName(), ACCOUNT_NON_LOCKED), new Object[] { "false" }, getRowMapper());
	}

	@Override
	public Collection<User> findCurrentlyActiveUsers()  throws RepositoryDataAccessException{
		return getJdbcTemplate().query(getFindBySql(getTableName(), SIGN_ON_STATUS), new Object[] { String.valueOf(spring.desai.common.model.User.SIGN_ON_STATUS.LOGGED_IN) }, getRowMapper());
	}

	@Override
	public Collection<User> findUsersWithMaxFailedAttempts() throws RepositoryDataAccessException {
		return getJdbcTemplate().query(FIND_MAX_ATTEMPTS_USER_SQL, new Object[] { MAX_FAILED_ATTEMPTS }, getRowMapper());
	}

	@Override
	public void resetLockedOutUser(User user) throws RepositoryDataAccessException {
		checkPersitableValidity(user);
		getJdbcTemplate().update(RESET_LOCKED_OUT_USER_SQL, new Object[] { user.getId() });
	}
	 
	@Override
	public void resetLockedOutUsers(Collection<User> users) throws RepositoryDataAccessException {
		checkPersitableValidity(users);
			// TODO do this update in one update statement rather then multiple single updates.
//			Object[] ids = new String[users.size()];
//			int i = 0;
//			for (User user : users) {
//				ids[i++] = user.getId();
//			}
//			getJdbcTemplate().update("UPDATE " + getTableName() + " SET " + ACCOUNT_LOCKED + " = 'false' WHERE " + USER_ID + " IN (  ", ids);
		for (User user : users) {
			resetLockedOutUser(user);
		}
	}
	
	@Override
	public boolean isExistingUser(String userId) {
		int count = getJdbcTemplate().queryForObject("SELECT COUNT(*) FROM " + getTableName() + " WHERE LOWER(" + getIdField() + ") = LOWER(?)", new Object[] { userId }, Integer.class);
		return count != 0;
	}
}

package spring.desai.common.repository.impl.jdbc;

import static spring.desai.common.utils.DataBaseConstants.F_NAME;
import static spring.desai.common.utils.DataBaseConstants.USER_TABLE_NAME;

import java.util.Collection;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import spring.desai.common.model.pojo.User;
import spring.desai.common.repository.UserRepository;

@Repository("userRepository")
public class UserRepositoryImpl extends BaseJdbcRepository<User> implements UserRepository {

	@Override
	protected String getInsertSql() {
		return "INSERT INTO users VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	}

	@Override
	protected String getUpdateSql() {
		return "UPDATE users SET f_name = ?, l_name = ?, age = ?, address = ?, failed_attempts = ?, account_locked = ?, sign_on_status = ? WHERE user_id = ?";
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
	protected PreparedStatementSetter getInsertPreparedStatementSetter(User persistable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected PreparedStatementSetter getUpdatePreparedStatementSetter(User persistable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected BatchPreparedStatementSetter getInsertBatchPreparedStatementSetter(Collection<User> persistable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected BatchPreparedStatementSetter getUpdateBatchPreparedStatementSetter(Collection<User> persistable) {
		// TODO Auto-generated method stub
		return null;
	}

}

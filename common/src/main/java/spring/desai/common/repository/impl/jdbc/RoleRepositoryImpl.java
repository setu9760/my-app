package spring.desai.common.repository.impl.jdbc;

import static spring.desai.common.utils.DataBaseConstants.DESCRIPTION;
import static spring.desai.common.utils.DataBaseConstants.ROLE;
import static spring.desai.common.utils.DataBaseConstants.ROLES_TABLE;
import static spring.desai.common.utils.DataBaseConstants.ROLE_FULL;
import static spring.desai.common.utils.DataBaseConstants.USER_ID;
import static spring.desai.common.utils.DataBaseConstants.USER_ROLE_TABLE;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import spring.desai.common.model.Role;
import spring.desai.common.model.User;
import spring.desai.common.repository.RoleRepository;
import spring.desai.common.repository.exception.RepositoryDataAccessException;
import spring.desai.common.utils.Unsupported;

@Repository("roleRepository")
public class RoleRepositoryImpl extends BaseJdbcRepository<Role> implements RoleRepository {

	private static final String GET_USER_ID_FOR_ROLE_SQL = "SELECT " + USER_ID + " FROM " + USER_ROLE_TABLE + " WHERE "+ ROLE +" = ?";
	private static final String GET_ROLES_FOR_FOR_USER_SQL = "SELECT r.* FROM " + ROLES_TABLE + " r LEFT JOIN " + USER_ROLE_TABLE + " ur ON r." + ROLE + " = ur." + ROLE + " WHERE ur." + USER_ID + " = ?";
	private static final String IS_ROLE_AVAILABLE_TO_USER_SQL = "SELECT COUNT(*) FROM " + USER_ROLE_TABLE + " WHERE " + USER_ID + " = ? AND " + ROLE + " = ?";
	private static final String ASSIGN_ROLE_TO_USER_SQL = "INSERT INTO " + USER_ROLE_TABLE + " VALUES (?, ?)"; 
	
	@Override
	public Collection<String> getUserIdsforRole(Role role) throws RepositoryDataAccessException {
		checkPersitableValidity(role);
		try {
			return getJdbcTemplate().queryForList(GET_USER_ID_FOR_ROLE_SQL, new Object[] { role.getId() }, String.class);
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}

	@Override
	public Collection<Role> getRolesForUserId(String userId) throws RepositoryDataAccessException {
		try {
			return getJdbcTemplate().query(GET_ROLES_FOR_FOR_USER_SQL, new Object[] { userId }, getRowMapper());
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}

	@Override
	public boolean isRoleAvailableToUser(String userId, Role role) throws RepositoryDataAccessException {
		checkPersitableValidity(role);
		try {
			return getJdbcTemplate().queryForObject(IS_ROLE_AVAILABLE_TO_USER_SQL, new Object[] {userId, role.getId()}, Integer.class) == 1;
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}
	
	@Override
	public void assignRoleToUser(String userId, Role role) throws RepositoryDataAccessException {
		if (!isRoleAvailableToUser(userId, role)) {
			try {
				getJdbcTemplate().update(ASSIGN_ROLE_TO_USER_SQL, new Object[] { userId, role.getId()});
			} catch (DataAccessException e) {
				throw new RepositoryDataAccessException(e);
			}
		} else {
			getLogger().warn("User: " + userId + " already is assigned role " + role.getId() + ", cannot assign same role twice");
		}
		
	}

	@Override
	protected String getInsertSql() {
		return "INSERT INTO " + ROLES_TABLE + " VALUES (?, ?, ?)";
	}

	@Override
	protected String getUpdateSql() {
		return "UPDATE " + ROLES_TABLE + " SET " + ROLE_FULL + " = ?, " + DESCRIPTION + " = ? WHERE " + ROLE + " = ?";
	}

	@Override
	protected RowMapper<Role> getRowMapper() {
		return rolesRowMapper;
	}

	@Override
	protected String getTableName() {
		return ROLES_TABLE;
	}

	@Override
	protected String getNameField() {
		return ROLE;
	}
	
	@Override
	protected String getIdField() {
		return ROLE;
	}

	@Override
	@Unsupported
	public void saveAll(Collection<Role> persistables) throws RepositoryDataAccessException {
		throwUnsupportedOperationException("saveAll()", getClass().getName());
	}
	
	@Override
	@Unsupported
	public void updateAll(Collection<Role> persistables) throws RepositoryDataAccessException {
		throwUnsupportedOperationException("updateAll()", getClass().getName());
	}
	
	@Override
	protected PreparedStatementSetter getInsertPreparedStatementSetter(final Role r) {
		return new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, r.getId());
				ps.setString(2, r.getRole_full());
				ps.setString(3, r.getDescription());
			}
		};
	}

	@Override
	protected PreparedStatementSetter getUpdatePreparedStatementSetter(final Role r) {
		return new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, r.getRole_full());
				ps.setString(2, r.getDescription());
				ps.setString(3, r.getId());
			}
		};
	}

	@Override
	protected BatchPreparedStatementSetter getInsertBatchPreparedStatementSetter(final Collection<Role> c) {
		return null;
	}

	@Override
	protected BatchPreparedStatementSetter getUpdateBatchPreparedStatementSetter(final Collection<Role> c) {
		return null;
	}

}

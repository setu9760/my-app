package spring.desai.common.repository.impl;

import static spring.desai.common.utils.DataBaseConstants.COST_CODE;
import static spring.desai.common.utils.DataBaseConstants.ID;
import static spring.desai.common.utils.DataBaseConstants.IS_MANDATORY;
import static spring.desai.common.utils.DataBaseConstants.NAME;
import static spring.desai.common.utils.DataBaseConstants.STUD_ID;
import static spring.desai.common.utils.DataBaseConstants.SUBJECT_STUDENT_LINK_TABLE_NAME;
import static spring.desai.common.utils.DataBaseConstants.SUBJECT_TABLE_NAME;
import static spring.desai.common.utils.DataBaseConstants.SUBJ_ID;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import spring.desai.common.model.enums.CostCode;
import spring.desai.common.model.pojo.Subject;
import spring.desai.common.repository.AbstractBaseRepository;
import spring.desai.common.repository.SubjectRepository;
import spring.desai.common.repository.exception.RepositoryDataAccessException;

@Repository(value="subjectRepository")
public class SubjectRepositoryImpl extends AbstractBaseRepository<Subject> implements SubjectRepository {
	
	public Collection<Subject> findByIds(String... ids) throws RepositoryDataAccessException {
		try {
			return getJdbcTemplate().query(getFindBySqlWhereIn(SUBJECT_TABLE_NAME, ID, ids.length), ids, getRowMapper());
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}
	
	@Override
	public Collection<Subject> findByCostCode(CostCode costCode) throws RepositoryDataAccessException {
		try {
			return getJdbcTemplate().query(getFindBySql(SUBJECT_TABLE_NAME, COST_CODE), new Object[] { String.valueOf(costCode) }, getRowMapper());
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}

	@Override
	public Collection<Subject> findByMandatory(boolean isMandatory) throws RepositoryDataAccessException {
		try {
			return getJdbcTemplate().query(getFindBySql(SUBJECT_TABLE_NAME, IS_MANDATORY), new Object[] { isMandatory }, getRowMapper());
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}

	@Override
	public Collection<Subject> getSubjectsForStudentId(String stud_id) throws DataAccessException {
		try {
			List<String> subjIds = getJdbcTemplate().queryForList(getFieldFindBySql(SUBJECT_STUDENT_LINK_TABLE_NAME, STUD_ID, SUBJ_ID), new Object[] { stud_id}, String.class);
			return getJdbcTemplate().query(getFindBySqlWhereIn(SUBJECT_TABLE_NAME, ID, subjIds.size()), subjIds.toArray(), getRowMapper());
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}
	
	@Override
	protected RowMapper<Subject> getRowMapper() {
		return subjectRowMapper;
	}
	
	@Override
	protected String getInsertSql() {
		return "INSERT INTO subject (id, name, cost_code, isMandatory) VALUES (?, ?, ?, ?)";
	}

	@Override
	protected String getUpdateSql() {
		return "UPDATE subject set name = ?, cost_code = ?, isMandatory = ? WHERE id = ?";
	}

	@Override
	protected String getTableName() {
		return SUBJECT_TABLE_NAME;
	}

	@Override
	protected String getIdField() {
		return ID;
	}

	@Override
	protected String getNameField() {
		return NAME;
	}
	
	@Override
	protected PreparedStatementSetter getInsertPreparedStatementSetter(final Subject s) {
		return new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, s.getId());
				ps.setString(2, s.getName());
				ps.setString(3, s.getCost_code().toString());
				ps.setString(4, String.valueOf(s.isMandatory()));
			}
		};
	}
	
	@Override
	protected PreparedStatementSetter getUpdatePreparedStatementSetter(final Subject s) {
		return new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, s.getName());
				ps.setString(2, s.getCost_code().toString());
				ps.setString(3, String.valueOf(s.isMandatory()));
				ps.setString(4, s.getId());
			}
		};
	}
	
	@Override
	protected BatchPreparedStatementSetter getInsertBatchPreparedStatementSetter(final Collection<Subject> persistable) {
		final List<Subject> l = new ArrayList<>(persistable);
		return new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				Subject s = l.get(i);
				ps.setString(1, s.getName());
				ps.setString(2, s.getCost_code().toString());
				ps.setString(3, String.valueOf(s.isMandatory()));
				ps.setString(4, s.getId());
			}
			
			@Override
			public int getBatchSize() {
				return l.size();
			}
		};
	}
	
	@Override
	protected BatchPreparedStatementSetter getUpdateBatchPreparedStatementSetter(final Collection<Subject> persistable) {
		final List<Subject> l = new ArrayList<>(persistable);
		return new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				Subject s = l.get(i);
				ps.setString(1, s.getCost_code().toString());
				ps.setString(2, String.valueOf(s.isMandatory()));
				ps.setString(3, s.getId());
				ps.setString(4, s.getName());
			}
			
			@Override
			public int getBatchSize() {
				return l.size();
			}
		};
	}

}

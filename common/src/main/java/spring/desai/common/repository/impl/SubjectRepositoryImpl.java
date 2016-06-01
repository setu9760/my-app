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
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import spring.desai.common.model.enums.CostCode;
import spring.desai.common.model.pojo.Subject;
import spring.desai.common.repository.AbstractBaseRepository;
import spring.desai.common.repository.SubjectRepository;
import spring.desai.common.repository.exception.RepositoryDataAccessException;

@Repository(value="subjectRepository")
public class SubjectRepositoryImpl extends AbstractBaseRepository<Subject> implements SubjectRepository {
	
	@Override
	public void saveImpl(Subject subject) throws RepositoryDataAccessException {
		try {
			checkPersitableValidity(subject);
			getJdbcTemplate().update(getInsertSql(), new Object[] {subject.getId(), subject.getName(), subject.getCost_code().toString(), subject.isMandatory()});
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}
	
	@Override
	public void saveAllImpl(final Collection<Subject> persistables) throws RepositoryDataAccessException{
		try {
			final List<Subject> subjs = new ArrayList<>(persistables);
			getJdbcTemplate().batchUpdate(getInsertSql(), new BatchPreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					Subject s = subjs.get(i);
					checkPersitableValidity(s);
					ps.setString(1, s.getId());
					ps.setString(2, s.getName());
					ps.setString(3, s.getCost_code().toString());
					ps.setString(4, String.valueOf(s.isMandatory()));
				}
				
				@Override
				public int getBatchSize() {
					return persistables.size();
				}
			});
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}

	@Override
	public void updateImpl(Subject subject) {
		try {
			checkPersitableValidity(subject);
			getJdbcTemplate().update(getUpdateSql(), new Object[] {subject.getName(), subject.getCost_code().toString(), subject.isMandatory(), subject.getId()});
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}
	
	@Override
	public void updateAllImpl(final Collection<Subject> persistables) throws RepositoryDataAccessException{
		try {
			final List<Subject> subjs = new ArrayList<>(persistables);
			getJdbcTemplate().batchUpdate(getUpdateSql(), new BatchPreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					Subject s = subjs.get(i);
					checkPersitableValidity(s);
					ps.setString(1, s.getName());
					ps.setString(2, s.getCost_code().toString());
					ps.setString(3, String.valueOf(s.isMandatory()));
					ps.setString(4, s.getId());
				}
				
				@Override
				public int getBatchSize() {
					return persistables.size();
				}
			});
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}
	
	public Collection<Subject> findByIds(String... ids) throws RepositoryDataAccessException {
		try {
			return getJdbcTemplate().query(getFindBySqlWhereIn(SUBJECT_TABLE_NAME, ID, ids.length), ids, getSubjectRowMapper());
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}
	
	@Override
	public Collection<Subject> findByCostCode(CostCode costCode) throws RepositoryDataAccessException {
		try {
			return getJdbcTemplate().query(getFindBySql(SUBJECT_TABLE_NAME, COST_CODE), new Object[] { String.valueOf(costCode) }, getSubjectRowMapper());
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}

	@Override
	public Collection<Subject> findByMandatory(boolean isMandatory) throws RepositoryDataAccessException {
		try {
			return getJdbcTemplate().query(getFindBySql(SUBJECT_TABLE_NAME, IS_MANDATORY), new Object[] { isMandatory }, getSubjectRowMapper());
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}

	@Override
	public Collection<Subject> getSubjectsForStudentId(String stud_id) throws DataAccessException {
		try {
			List<String> subjIds = getJdbcTemplate().queryForList(getFieldFindBySql(SUBJECT_STUDENT_LINK_TABLE_NAME, STUD_ID, SUBJ_ID), new Object[] { stud_id}, String.class);
			return getJdbcTemplate().query(getFindBySqlWhereIn(SUBJECT_TABLE_NAME, ID, subjIds.size()), subjIds.toArray(), getSubjectRowMapper());
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}
	
	@Override
	protected RowMapper<Subject> getRowMapper() {
		return getSubjectRowMapper();
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

}

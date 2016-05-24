package spring.desai.common.repository.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.stereotype.Repository;

import spring.desai.common.model.enums.CostCode;
import spring.desai.common.model.pojo.Subject;
import spring.desai.common.repository.AbstractBaseRepository;
import spring.desai.common.repository.SubjectRepository;
import spring.desai.common.repository.exception.RepositoryDataAccessException;
import spring.desai.common.utils.DataBaseConstants;

@Repository(value="subjectRepository")
public class SubjectRepositoryImpl extends AbstractBaseRepository implements SubjectRepository {
	
	@Override
	public void save(Subject subject) throws RepositoryDataAccessException {
		try {
			getJdbcTemplate().update(getInsertSql(), new Object[] {subject.getId(), subject.getName(), subject.getCost_code(), subject.isMandatory()});
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}
	
	@Override
	public void saveAll(final Collection<Subject> persistables) throws RepositoryDataAccessException{
		try {
			final List<Subject> subjs = new ArrayList<>(persistables);
			getJdbcTemplate().batchUpdate(getInsertSql(), new BatchPreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					Subject s = subjs.get(i);
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
	public void update(Subject subject) {
		try {
			getJdbcTemplate().update(getUpdateSql(), new Object[] {subject.getName(), subject.getCost_code(), subject.isMandatory(), subject.getId()});
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}
	
	@Override
	public void updateAll(final Collection<Subject> persistables) throws RepositoryDataAccessException{
		try {
			final List<Subject> subjs = new ArrayList<>(persistables);
			getJdbcTemplate().batchUpdate(getUpdateSql(), new BatchPreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					Subject s = subjs.get(i);
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
	
	@Override
	public Subject findById(String id) throws RepositoryDataAccessException {
		try {
			List<Subject> l = getJdbcTemplate().query(getFindBySql(DataBaseConstants.SUBJECT_TABLE_NAME, DataBaseConstants.ID), new Object[] { id }, getSubjectRowMapper());
			return (l != null && !l.isEmpty()) ? l.get(0) : null;
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}
	
	public Collection<Subject> findByIds(String... ids) throws RepositoryDataAccessException {
		try {
			return getJdbcTemplate().query(getFindBySqlWhereIn(DataBaseConstants.SUBJECT_TABLE_NAME, DataBaseConstants.ID, ids.length), ids, getSubjectRowMapper());
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}
	
	@Override
	public Collection<Subject> findByName(String name) throws RepositoryDataAccessException {
		try {
			return getJdbcTemplate().query(getFindBySql(DataBaseConstants.SUBJECT_TABLE_NAME, DataBaseConstants.NAME), new Object[] { name }, getSubjectRowMapper());
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}

	@Override
	public Collection<Subject> findByCostCode(CostCode costCode) throws RepositoryDataAccessException {
		try {
			return getJdbcTemplate().query(getFindBySql(DataBaseConstants.SUBJECT_TABLE_NAME, DataBaseConstants.COST_CODE), new Object[] { costCode.toString() }, getSubjectRowMapper());
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}

	@Override
	public Collection<Subject> findByMandatory(boolean isMandatory) throws RepositoryDataAccessException {
		try {
			return getJdbcTemplate().query(getFindBySql(DataBaseConstants.SUBJECT_TABLE_NAME, DataBaseConstants.IS_MANDATORY), new Object[] { isMandatory }, getSubjectRowMapper());
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}

	@Override
	public Collection<Subject> getSubjectsForStudentId(String stud_id) throws DataAccessException {
		try {
			List<String> subjIds = getJdbcTemplate().queryForList(getFieldFindBySql(DataBaseConstants.SUBJECT_STUDENT_LINK_TABLE_NAME, DataBaseConstants.STUD_ID, DataBaseConstants.SUBJ_ID), new Object[] { stud_id}, String.class);
			return getJdbcTemplate().query(getFindBySqlWhereIn(DataBaseConstants.SUBJECT_TABLE_NAME, DataBaseConstants.ID, subjIds.size()), subjIds.toArray(), getSubjectRowMapper());
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}
	
	@Override
	public Collection<Subject> getAll() throws RepositoryDataAccessException {
		return (Collection<Subject>) getAllImpl(getSelectAllSql(DataBaseConstants.SUBJECT_TABLE_NAME), Subject.class);
	}

	@Override
	public void deleteById(String id) throws RepositoryDataAccessException {
		deleteImpl(getDeleteBySql(DataBaseConstants.SUBJECT_TABLE_NAME, DataBaseConstants.ID), id);
	}

	@Override
	public void deleteByName(String name) throws RepositoryDataAccessException {
		deleteImpl(getDeleteBySql(DataBaseConstants.SUBJECT_TABLE_NAME, DataBaseConstants.NAME), name);
	}
	
	/**
	 * No-op
	 */
	@Override
	public void deleteAll() throws RepositoryDataAccessException {
		deleteAllImpl(DataBaseConstants.STUDENT_SCHOLORSHIP_LINK_TABLE_NAME);
	}

	@Override
	public int countAll() throws RepositoryDataAccessException {
		return countAllImpl(getCountAllSql(DataBaseConstants.SUBJECT_TABLE_NAME));
	}

	@Override
	protected String getInsertSql() {
		return "INSERT INTO subject (id, name, cost_code, isMandatory) VALUE (?, ?, ?, ?)";
	}

	@Override
	protected String getUpdateSql() {
		return "UPDATE subject set name = ?, cost_code = ?, isMandatory = ? WHERE id = ?";
	}

}

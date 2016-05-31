package spring.desai.common.repository.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.stereotype.Repository;

import spring.desai.common.model.pojo.Tutor;
import spring.desai.common.repository.AbstractBaseRepository;
import spring.desai.common.repository.TutorRepository;
import spring.desai.common.repository.exception.RepositoryDataAccessException;
import static spring.desai.common.utils.DataBaseConstants.*;

@Repository(value="tutorRepository")
public class TutorRepositoryImpl extends AbstractBaseRepository implements TutorRepository {

	@Override
	public void save(Tutor tutor) {
		try {
			checkPersitableValidity(tutor);
			getJdbcTemplate().update(getInsertSql(), new Object[] { tutor.getId(), tutor.getF_name(), tutor.getL_name(), tutor.getAddress(), tutor.isFulltime(), tutor.getSubj_id()} );
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}
	
	@Override
	public void saveAll(final Collection<Tutor> persistables) throws RepositoryDataAccessException {
		try {
			final List<Tutor> tutors = new ArrayList<>(persistables);
			getJdbcTemplate().batchUpdate(getInsertSql(), new BatchPreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					Tutor t = tutors.get(i);
					checkPersitableValidity(t);
					ps.setString(1, t.getId());
					ps.setString(2, t.getF_name());
					ps.setString(3, t.getL_name());
					ps.setString(4, t.getAddress());
					ps.setString(5, String.valueOf(t.isFulltime()));
					ps.setString(6, t.getSubj_id());
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
	public void update(Tutor tutor) {
		try {
			checkPersitableValidity(tutor);
			getJdbcTemplate().update(getUpdateSql(), new Object[] {tutor.getF_name(), tutor.getL_name(), tutor.getAddress(), tutor.isFulltime(), tutor.getSubj_id(), tutor.getId()});
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}
	
	@Override
	public void updateAll(final Collection<Tutor> persistables) throws RepositoryDataAccessException {
		try {
			final List<Tutor> tutors = new ArrayList<>(persistables);
			getJdbcTemplate().batchUpdate(getUpdateSql(), new BatchPreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					Tutor t = tutors.get(i);
					checkPersitableValidity(t);
					ps.setString(1, t.getF_name());
					ps.setString(2, t.getL_name());
					ps.setString(3, t.getAddress());
					ps.setString(4, String.valueOf(t.isFulltime()));
					ps.setString(5, t.getSubj_id());
					ps.setString(6, t.getId());
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
	public Tutor findById(String id) throws RepositoryDataAccessException {
		try {
			List<Tutor> l = getJdbcTemplate().query(getFindBySql(TUTOR_TABLE_NAME, ID), new Object[] { id }, getTutorRowMapper());
			return (l != null && !l.isEmpty()) ? l.get(0) : null;
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}

	@Override
	public Collection<Tutor> findByName(String f_name) throws RepositoryDataAccessException {
		try {
			return getJdbcTemplate().query(getFindBySql(TUTOR_TABLE_NAME, F_NAME), new Object[] { f_name }, getTutorRowMapper());
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}
	
	@Override
	public Collection<Tutor> getAll() throws DataAccessException {
		return (Collection<Tutor>) getAllImpl(getSelectAllSql(TUTOR_TABLE_NAME), Tutor.class);
	}
	
	@Override
	public Collection<Tutor> getTutorsForSubject(String subj_id) throws DataAccessException {
		try {
			return getJdbcTemplate().query(getFindBySql(TUTOR_TABLE_NAME, SUBJ_ID), new Object[] { subj_id }, getTutorRowMapper());
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}

	@Override
	public void deleteById(String id) throws RepositoryDataAccessException {
		deleteImpl(getDeleteBySql(TUTOR_TABLE_NAME, ID), id);
	}

	@Override
	public void deleteByName(String f_name) throws RepositoryDataAccessException {
		deleteImpl(getDeleteBySql(TUTOR_TABLE_NAME, F_NAME), f_name);
	}
	
	/**
	 * No-op
	 */
	@Override
	public void deleteAll() throws RepositoryDataAccessException {
		deleteAllImpl(TUTOR_TABLE_NAME);
	}

	@Override
	public int countAll() throws RepositoryDataAccessException {
		return countAllImpl(getCountAllSql(TUTOR_TABLE_NAME));
	}
	
	@Override
	protected String getInsertSql() {
		return "INSERT INTO tutor VALUES (?, ?, ?, ?, ?, ?)";
	}
	
	@Override
	protected String getUpdateSql() {
		return "UPDATE tutor SET f_name = ?, l_name = ?, address = ?, isFulltime = ?, subj_id = ? WHERE id = ?";
	}
}

package spring.desai.common.repository.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import spring.desai.common.model.pojo.Tutor;
import spring.desai.common.repository.AbstractBaseRepository;
import spring.desai.common.repository.TutorRepository;
import spring.desai.common.repository.exception.RepositoryDataAccessException;
import static spring.desai.common.utils.DataBaseConstants.*;

@Repository(value="tutorRepository")
public class TutorRepositoryImpl extends AbstractBaseRepository<Tutor> implements TutorRepository {

	@Override
	public void saveImpl(Tutor tutor) {
		try {
			checkPersitableValidity(tutor);
			getJdbcTemplate().update(getInsertSql(), new Object[] { tutor.getId(), tutor.getF_name(), tutor.getL_name(), tutor.getAddress(), tutor.isFulltime(), tutor.getSubj_id()} );
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}
	
	@Override
	public void saveAllImpl(final Collection<Tutor> persistables) throws RepositoryDataAccessException {
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
	public void updateImpl(Tutor tutor) {
		try {
			checkPersitableValidity(tutor);
			getJdbcTemplate().update(getUpdateSql(), new Object[] {tutor.getF_name(), tutor.getL_name(), tutor.getAddress(), tutor.isFulltime(), tutor.getSubj_id(), tutor.getId()});
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}
	
	@Override
	public void updateAllImpl(final Collection<Tutor> persistables) throws RepositoryDataAccessException {
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
	public Collection<Tutor> getTutorsForSubject(String subj_id) throws DataAccessException {
		try {
			return getJdbcTemplate().query(getFindBySql(TUTOR_TABLE_NAME, SUBJ_ID), new Object[] { subj_id }, getTutorRowMapper());
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}

	@Override
	protected RowMapper<Tutor> getRowMapper() {
		return getTutorRowMapper();
	}
	
	@Override
	protected String getNameField() {
		return F_NAME;
	}
	
	@Override
	protected String getTableName() {
		return TUTOR_TABLE_NAME;
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

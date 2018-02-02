package spring.desai.common.repository.impl.jdbc;

import static spring.desai.common.utils.DataBaseConstants.F_NAME;
import static spring.desai.common.utils.DataBaseConstants.SUBJ_ID;
import static spring.desai.common.utils.DataBaseConstants.TUTOR_TABLE_NAME;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import spring.desai.common.model.Tutor;
import spring.desai.common.repository.TutorRepository;
import spring.desai.common.repository.exception.RepositoryDataAccessException;

@Repository(value="tutorRepository")
public class TutorRepositoryImpl extends BaseJdbcRepository<Tutor> implements TutorRepository {

	@Override
	public Collection<Tutor> getTutorsForSubject(String subj_id) throws RepositoryDataAccessException {
		return getJdbcTemplate().query(getFindBySql(TUTOR_TABLE_NAME, SUBJ_ID), new Object[] { subj_id }, getRowMapper());
	}
	
	@Override
	protected RowMapper<Tutor> getRowMapper() {
		return tutorRowMapper;
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
		return "INSERT INTO tutor VALUES (?, ?, ?, 1, ?, ?, ?)";
	}
	
	@Override
	protected String getUpdateSql() {
		return "UPDATE tutor SET f_name = ?, l_name = ?, address = ?, isFulltime = ?, subj_id = ? WHERE id = ?";
	}
	
	@Override
	protected PreparedStatementSetter getInsertPreparedStatementSetter(final Tutor t) {
		return ps -> {
				ps.setString(1, t.getId());
				ps.setString(2, t.getFirstname());
				ps.setString(3, t.getLastname());
				ps.setString(4, t.getAddress());
				ps.setString(5, String.valueOf(t.isFulltime()));
				ps.setString(6, t.getSubj_id());
		};
	}
	
	@Override
	protected PreparedStatementSetter getUpdatePreparedStatementSetter(final Tutor t) {
		return ps -> {
				ps.setString(1, t.getFirstname());
				ps.setString(2, t.getLastname());
				ps.setString(3, t.getAddress());
				ps.setString(4, String.valueOf(t.isFulltime()));
				ps.setString(5, t.getSubj_id());
				ps.setString(6, t.getId());
		};
	}
	
	@Override
	protected BatchPreparedStatementSetter getInsertBatchPreparedStatementSetter(Collection<Tutor> persistable) {
		final List<Tutor> l = new ArrayList<>(persistable);
		return new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				Tutor t = l.get(i);
				ps.setString(1, t.getId());
				ps.setString(2, t.getFirstname());
				ps.setString(3, t.getLastname());
				ps.setString(4, t.getAddress());
				ps.setString(5, String.valueOf(t.isFulltime()));
				ps.setString(6, t.getSubj_id());
			}
			
			@Override
			public int getBatchSize() {
				return l.size();
			}
		};
	}
	
	@Override
	protected BatchPreparedStatementSetter getUpdateBatchPreparedStatementSetter(Collection<Tutor> persistable) {
		final List<Tutor> l = new ArrayList<>(persistable);
		return new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				Tutor t = l.get(i);
				ps.setString(1, t.getFirstname());
				ps.setString(2, t.getLastname());
				ps.setString(3, t.getAddress());
				ps.setString(4, String.valueOf(t.isFulltime()));
				ps.setString(5, t.getSubj_id());
				ps.setString(6, t.getId());
			}
			
			@Override
			public int getBatchSize() {
				return l.size();
			}
		};
	}
}

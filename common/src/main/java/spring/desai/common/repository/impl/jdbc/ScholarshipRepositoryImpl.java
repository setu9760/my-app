package spring.desai.common.repository.impl.jdbc;

import static spring.desai.common.utils.DataBaseConstants.SCHOLORSHIP_TABLE_NAME;
import static spring.desai.common.utils.DataBaseConstants.STUD_ID;
import static spring.desai.common.utils.DataBaseConstants.TYPE;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import spring.desai.common.model.Scholarship;
import spring.desai.common.model.enums.ScholorshipType;
import spring.desai.common.repository.ScholarshipRepository;
import spring.desai.common.repository.exception.RepositoryDataAccessException;

@Repository("scholarshipRepository")
public class ScholarshipRepositoryImpl extends BaseJdbcRepository<Scholarship> implements ScholarshipRepository{

	@Override
	public Collection<Scholarship> findByType(ScholorshipType type) throws RepositoryDataAccessException {
		return findByName(String.valueOf(type));
	}
	
	public Collection<Scholarship> findByStudentId(String stud_id) throws RepositoryDataAccessException{
		return getJdbcTemplate().query(getFindBySql(SCHOLORSHIP_TABLE_NAME, STUD_ID), new Object[] { stud_id }, getRowMapper());
	}
	
	@Override
	protected RowMapper<Scholarship> getRowMapper() {
		return scholarshipRowMapper;
	}

	@Override
	protected String getTableName() {
		return SCHOLORSHIP_TABLE_NAME;
	}
	
	@Override
	protected String getNameField() {
		return TYPE;
	}
	
	@Override
	protected String getInsertSql() {
		return "INSERT INTO scholorship (id, external_ref, type, total_amount, paid_amount, isFullyPaid, isPostPay, stud_id, additional_comments) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	}

	@Override
	protected String getUpdateSql() {
		return "UPDATE scholorship SET external_ref = ?, type = ?, total_amount = ?, paid_amount = ?, isFullyPaid = ?, isPostPay = ?, stud_id = ?, additional_comments = ? WHERE id = ?";
	}
	
	@Override
	protected PreparedStatementSetter getInsertPreparedStatementSetter(final Scholarship s) {
		return new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, s.getId());
				ps.setString(2, s.getExternal_ref());
				ps.setString(3, String.valueOf(s.getType()));
				ps.setDouble(4, s.getTotal_amount());
				ps.setDouble(5, s.getPaid_amount());
				ps.setString(6, String.valueOf(s.isFullyPaid()));
				ps.setString(7, String.valueOf(s.isPostPay()));
				ps.setString(8, s.getStud_id());
				ps.setString(9, s.getAdditional_comments());
			}
		};
	}
	
	@Override
	protected PreparedStatementSetter getUpdatePreparedStatementSetter(final Scholarship s) {
		return new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, s.getExternal_ref());
				ps.setString(2, String.valueOf(s.getType()));
				ps.setDouble(3, s.getTotal_amount());
				ps.setDouble(4, s.getPaid_amount());
				ps.setString(5, String.valueOf(s.isFullyPaid()));
				ps.setString(6, String.valueOf(s.isPostPay()));
				ps.setString(7, s.getStud_id());
				ps.setString(8, s.getAdditional_comments());
				ps.setString(9, s.getId());
			}
		};
	}
	
	@Override
	protected BatchPreparedStatementSetter getInsertBatchPreparedStatementSetter(Collection<Scholarship> persistable) {
		final List<Scholarship> l = new ArrayList<>(persistable);
		return new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				Scholarship sch = l.get(i);
				ps.setString(1, sch.getId());
				ps.setString(2, sch.getExternal_ref());
				ps.setString(3, String.valueOf(sch.getType()));
				ps.setDouble(4, sch.getTotal_amount());
				ps.setDouble(5, sch.getPaid_amount());
				ps.setString(6, String.valueOf(sch.isFullyPaid()));
				ps.setString(7, String.valueOf(sch.isPostPay()));
				ps.setString(8, sch.getStud_id());
				ps.setString(9, sch.getAdditional_comments());
			}
			
			@Override
			public int getBatchSize() {
				return l.size();
			}
		};
	}
	
	@Override
	protected BatchPreparedStatementSetter getUpdateBatchPreparedStatementSetter(Collection<Scholarship> persistable) {
		final List<Scholarship> l = new ArrayList<>(persistable);
		return new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				Scholarship sch = l.get(i);
				ps.setString(1, sch.getExternal_ref());
				ps.setString(2, String.valueOf(sch.getType()));
				ps.setDouble(3, sch.getTotal_amount());
				ps.setDouble(4, sch.getPaid_amount());
				ps.setString(5, String.valueOf(sch.isFullyPaid()));
				ps.setString(6, String.valueOf(sch.isPostPay()));
				ps.setString(7, sch.getStud_id());
				ps.setString(8, sch.getAdditional_comments());
				ps.setString(9, sch.getId());
			}
			
			@Override
			public int getBatchSize() {
				return l.size();
			}
		};
	}

}

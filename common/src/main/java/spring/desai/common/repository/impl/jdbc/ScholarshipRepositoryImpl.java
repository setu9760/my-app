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
	public void delete(String id) throws RepositoryDataAccessException {
		throwUnsupportedOperationException("delete(id)", getClass().getName());
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
		return ps -> {
				ps.setString(1, s.getId());
				ps.setString(2, s.getExternalRef());
				ps.setString(3, String.valueOf(s.getType()));
				ps.setDouble(4, s.getTotalAmount());
				ps.setDouble(5, s.getPaidAmount());
				ps.setString(6, String.valueOf(s.isFullyPaid()));
				ps.setString(7, String.valueOf(s.isPostPay()));
				ps.setString(8, s.getStudentId());
				ps.setString(9, s.getAdditionalComments());
		};
	}
	
	@Override
	protected PreparedStatementSetter getUpdatePreparedStatementSetter(final Scholarship s) {
		return ps -> {
				ps.setString(1, s.getExternalRef());
				ps.setString(2, String.valueOf(s.getType()));
				ps.setDouble(3, s.getTotalAmount());
				ps.setDouble(4, s.getPaidAmount());
				ps.setString(5, String.valueOf(s.isFullyPaid()));
				ps.setString(6, String.valueOf(s.isPostPay()));
				ps.setString(7, s.getStudentId());
				ps.setString(8, s.getAdditionalComments());
				ps.setString(9, s.getId());
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
				ps.setString(2, sch.getExternalRef());
				ps.setString(3, String.valueOf(sch.getType()));
				ps.setDouble(4, sch.getTotalAmount());
				ps.setDouble(5, sch.getPaidAmount());
				ps.setString(6, String.valueOf(sch.isFullyPaid()));
				ps.setString(7, String.valueOf(sch.isPostPay()));
				ps.setString(8, sch.getStudentId());
				ps.setString(9, sch.getAdditionalComments());
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
				ps.setString(1, sch.getExternalRef());
				ps.setString(2, String.valueOf(sch.getType()));
				ps.setDouble(3, sch.getTotalAmount());
				ps.setDouble(4, sch.getPaidAmount());
				ps.setString(5, String.valueOf(sch.isFullyPaid()));
				ps.setString(6, String.valueOf(sch.isPostPay()));
				ps.setString(7, sch.getStudentId());
				ps.setString(8, sch.getAdditionalComments());
				ps.setString(9, sch.getId());
			}
			
			@Override
			public int getBatchSize() {
				return l.size();
			}
		};
	}

}

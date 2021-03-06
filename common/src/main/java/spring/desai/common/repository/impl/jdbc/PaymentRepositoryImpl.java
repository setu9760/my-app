package spring.desai.common.repository.impl.jdbc;

import static spring.desai.common.utils.DataBaseConstants.AMOUNT;
import static spring.desai.common.utils.DataBaseConstants.ID;
import static spring.desai.common.utils.DataBaseConstants.PAYMENT_TABLE_NAME;
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

import spring.desai.common.model.Payment;
import spring.desai.common.model.enums.PaymentType;
import spring.desai.common.repository.PaymentRepository;
import spring.desai.common.repository.exception.RepositoryDataAccessException;

@Repository(value = "paymentRepository")
public class PaymentRepositoryImpl extends BaseJdbcRepository<Payment> implements PaymentRepository {

	@Override
	public Collection<Payment> findByStudentId(String stud_id) throws RepositoryDataAccessException {
		return getJdbcTemplate().query(getFindBySql(PAYMENT_TABLE_NAME, STUD_ID), new Object[] { stud_id }, getRowMapper());
	}

	@Override
	public Collection<Payment> findByType(PaymentType type) throws RepositoryDataAccessException {
		return getJdbcTemplate().query(getFindBySql(PAYMENT_TABLE_NAME, TYPE), new Object[] { String.valueOf(type) },
				getRowMapper());
	}

	@Override
	public double getTotalPaid(String studId) throws RepositoryDataAccessException {
		Double totalPaid = getJdbcTemplate().queryForObject(
				"select SUM(" + AMOUNT + ") from " + PAYMENT_TABLE_NAME + " where " + STUD_ID + " = ?",
				new Object[] { studId }, Double.class);
		return totalPaid != null ? totalPaid : 0d;
	}
	
	@Override
	public void delete(String id) throws RepositoryDataAccessException {
		throwUnsupportedOperationException("delete(id)", getClass().getName());
	}

	@Override
	protected RowMapper<Payment> getRowMapper() {
		return paymentRowMapper;
	}

	@Override
	protected String getInsertSql() {
		return "INSERT INTO payment (id, amount, type, stud_id, additional_comments) VALUES (?, ?, ?, ?, ?)";
	}

	@Override
	protected String getUpdateSql() {
		return "UPDATE payment SET amount = ?, type = ?, stud_id = ?, additional_comments =? WHERE id = ?";
	}

	@Override
	protected String getTableName() {
		return PAYMENT_TABLE_NAME;
	}

	@Override
	protected String getNameField() {
		return ID;
	}

	@Override
	protected PreparedStatementSetter getInsertPreparedStatementSetter(final Payment p) {
		return ps -> {
				ps.setString(1, p.getId());
				ps.setDouble(2, p.getAmount());
				ps.setString(3, String.valueOf(p.getPaymentType()));
				ps.setString(4, p.getStudentId());
				ps.setString(5, p.getComments());
		};
	}

	@Override
	protected PreparedStatementSetter getUpdatePreparedStatementSetter(final Payment p) {
		return ps -> {
				ps.setDouble(1, p.getAmount());
				ps.setString(2, String.valueOf(p.getPaymentType()));
				ps.setString(3, p.getStudentId());
				ps.setString(4, p.getComments());
				ps.setString(5, p.getId());
		};
	}

	@Override
	protected BatchPreparedStatementSetter getInsertBatchPreparedStatementSetter(final Collection<Payment> persistable) {
		final List<Payment> l = new ArrayList<>(persistable);
		return new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				Payment p = l.get(i);
				ps.setString(1, p.getId());
				ps.setDouble(2, p.getAmount());
				ps.setString(3, String.valueOf(p.getPaymentType()));
				ps.setString(4, p.getStudentId());
				ps.setString(5, p.getComments());
			}

			@Override
			public int getBatchSize() {
				return l.size();
			}
		};
	}

	@Override
	protected BatchPreparedStatementSetter getUpdateBatchPreparedStatementSetter(final Collection<Payment> persistable) {
		final List<Payment> l = new ArrayList<>(persistable);
		return new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				Payment p = l.get(i);
				ps.setDouble(1, p.getAmount());
				ps.setString(2, String.valueOf(p.getPaymentType()));
				ps.setString(3, p.getStudentId());
				ps.setString(4, p.getComments());
				ps.setString(5, p.getId());
			}

			@Override
			public int getBatchSize() {
				return l.size();
			}
		};
	}
}

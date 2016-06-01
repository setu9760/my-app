package spring.desai.common.repository.impl;

import static spring.desai.common.utils.DataBaseConstants.PAYMENT_TABLE_NAME;
import static spring.desai.common.utils.DataBaseConstants.STUD_ID;
import static spring.desai.common.utils.DataBaseConstants.TYPE;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import spring.desai.common.model.enums.PaymentType;
import spring.desai.common.model.pojo.Payment;
import spring.desai.common.repository.AbstractBaseRepository;
import spring.desai.common.repository.PaymentRepository;
import spring.desai.common.repository.exception.RepositoryDataAccessException;

@Repository(value="paymentRepository")
public class PaymentRepositoryImpl extends AbstractBaseRepository<Payment> implements PaymentRepository {

	@Override
	public void saveImpl(Payment payment) throws RepositoryDataAccessException {
		try {
			checkPersitableValidity(payment);
			getJdbcTemplate().update(getInsertSql(), new Object[] {payment.getId(), payment.getAmount(), payment.getPaymentType().toString(), payment.getStud_id()});
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}
	
	@Override
	public void saveAllImpl(final Collection<Payment> persistables) throws RepositoryDataAccessException{
		try {
			final List<Payment> payments = new ArrayList<>(persistables);
			getJdbcTemplate().batchUpdate(getInsertSql(), new BatchPreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					Payment p = payments.get(i);
					checkPersitableValidity(p);
					ps.setString(1, p.getId());
					ps.setDouble(2, p.getAmount());
					ps.setString(3, p.getPaymentType().toString());
					ps.setString(4, p.getStud_id());
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
	public void updateImpl(Payment payment) throws RepositoryDataAccessException {
		try {
			checkPersitableValidity(payment);
			getJdbcTemplate().update(getUpdateSql(), new Object[] {payment.getAmount(), payment.getPaymentType().toString(), payment.getStud_id(), payment.getId()});
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}
	
	@Override
	public void updateAllImpl(final Collection<Payment> persistables) throws RepositoryDataAccessException{
		try {
			final List<Payment> payments = new ArrayList<>(persistables);
			getJdbcTemplate().batchUpdate(getUpdateSql(), new BatchPreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					Payment p = payments.get(i);
					checkPersitableValidity(p);
					ps.setDouble(1, p.getAmount());
					ps.setString(2, p.getPaymentType().toString());
					ps.setString(3, p.getStud_id());
					ps.setString(4, p.getId());
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
	public Collection<Payment> findbyStudentId(String stud_id) throws RepositoryDataAccessException {
		try {
			return getJdbcTemplate().query(getFindBySql(PAYMENT_TABLE_NAME, STUD_ID), new Object[] { stud_id }, getPaymentRowMapper());
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}

	@Override
	public Collection<Payment> findByType(PaymentType type) throws RepositoryDataAccessException {
		try {
			return getJdbcTemplate().query(getFindBySql(PAYMENT_TABLE_NAME, TYPE), new Object[]{ type.toString()}, getPaymentRowMapper());
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}
	
	@Override
	protected RowMapper<Payment> getRowMapper() {
		return getPaymentRowMapper();
	}
	
	@Override
	protected String getInsertSql() {
		return "INSERT INTO payment (id, amount, type, stud_id) VALUES (?, ?, ?, ?)";
	}

	@Override
	protected String getUpdateSql() {
		return "UPDATE payment SET amount = ?, type = ?, stud_id = ? WHERE id = ?";
	}

	@Override
	protected String getTableName() {
		return PAYMENT_TABLE_NAME;
	}

	@Override
	protected String getNameField() {
		return null;
	}
}

package spring.desai.common.repository.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.stereotype.Repository;

import spring.desai.common.model.enums.PaymentType;
import spring.desai.common.model.pojo.Payment;
import spring.desai.common.repository.AbstractBaseRepository;
import spring.desai.common.repository.PaymentRepository;
import spring.desai.common.repository.exception.RepositoryDataAccessException;
import spring.desai.common.utils.DataBaseConstants;

@Repository(value="paymentRepository")
public class PaymentRepositoryImpl extends AbstractBaseRepository implements PaymentRepository {

	@Override
	public void save(Payment payment) throws RepositoryDataAccessException {
		try {
			getJdbcTemplate().update(getInsertSql(), new Object[] {payment.getId(), payment.getAmount(), payment.getPaymentType().toString(), payment.getStud_id()});
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}
	
	@Override
	public void saveAll(final Collection<Payment> persistables) throws RepositoryDataAccessException{
		try {
			final List<Payment> payments = new ArrayList<>(persistables);
			getJdbcTemplate().batchUpdate(getInsertSql(), new BatchPreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					Payment p = payments.get(i);
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
	public void update(Payment payment) throws RepositoryDataAccessException {
		try {
			getJdbcTemplate().update(getUpdateSql(), new Object[] {payment.getAmount(), payment.getPaymentType().toString(), payment.getStud_id(), payment.getId()});
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}
	
	@Override
	public void updateAll(final Collection<Payment> persistables) throws RepositoryDataAccessException{
		try {
			final List<Payment> payments = new ArrayList<>(persistables);
			getJdbcTemplate().batchUpdate(getUpdateSql(), new BatchPreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					Payment p = payments.get(i);
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
	public Payment findById(String id) throws RepositoryDataAccessException {
		try {
			List<Payment> l = getJdbcTemplate().query(getFindBySql(DataBaseConstants.PAYMENT_TABLE_NAME, DataBaseConstants.ID), new Object[] { id }, getPaymentRowMapper());
			return (l != null && !l.isEmpty()) ? l.get(0) : null;
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}
	
	@Override
	public Collection<Payment> findByName(String name) throws RepositoryDataAccessException {
		throw new UnsupportedOperationException("findByBame operation is Unsupported with PaymentRepository");
	}

	@Override
	public Collection<Payment> findbyStudentId(String stud_id) throws RepositoryDataAccessException {
		try {
			return getJdbcTemplate().query(getFindBySql(DataBaseConstants.PAYMENT_TABLE_NAME, DataBaseConstants.STUD_ID), new Object[] { stud_id }, getPaymentRowMapper());
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}

	@Override
	public Collection<Payment> findByType(PaymentType type) throws RepositoryDataAccessException {
		try {
			return getJdbcTemplate().query(getFindBySql(DataBaseConstants.PAYMENT_TABLE_NAME, DataBaseConstants.TYPE), new Object[]{ type.toString()}, getPaymentRowMapper());
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}
	
	@Override
	public Collection<Payment> getAll() throws RepositoryDataAccessException {
		try {
			return (Collection<Payment>) getAllImpl(getCountAllSql(DataBaseConstants.PAYMENT_TABLE_NAME), Payment.class);
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}
	
	@Override
	public void deleteById(String id) throws RepositoryDataAccessException {
		deleteImpl(getDeleteBySql(DataBaseConstants.PAYMENT_TABLE_NAME, DataBaseConstants.ID), id);
	}
	
	@Override
	public void deleteAll() throws RepositoryDataAccessException {
		deleteAllImpl(DataBaseConstants.PAYMENT_TABLE_NAME);
	}

	@Override
	public int countAll() throws RepositoryDataAccessException {
		return countAllImpl(getCountAllSql(DataBaseConstants.PAYMENT_TABLE_NAME));
	}


	@Override
	protected String getInsertSql() {
		return "INSERT INTO payment (id, amount, type, stud_id) VALUES (?, ?, ?, ?)";
	}

	@Override
	protected String getUpdateSql() {
		return "UPDATE payment SET amount = ?, type = ?, stud_id = ? WHERE id = ?";
	}
}

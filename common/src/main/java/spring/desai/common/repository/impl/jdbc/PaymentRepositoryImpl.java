package spring.desai.common.repository.impl.jdbc;

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
import org.springframework.jdbc.core.PreparedStatementSetter;
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
	public Collection<Payment> findbyStudentId(String stud_id) throws RepositoryDataAccessException {
		try {
			return getJdbcTemplate().query(getFindBySql(PAYMENT_TABLE_NAME, STUD_ID), new Object[] { stud_id }, getRowMapper());
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}

	@Override
	public Collection<Payment> findByType(PaymentType type) throws RepositoryDataAccessException {
		try {
			return getJdbcTemplate().query(getFindBySql(PAYMENT_TABLE_NAME, TYPE), new Object[]{ type.toString()}, getRowMapper());
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}
	
	@Override
	protected RowMapper<Payment> getRowMapper() {
		return paymentRowMapper;
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
	
	@Override
	protected PreparedStatementSetter getInsertPreparedStatementSetter(final Payment p) {
		return new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, p.getId());
				ps.setDouble(2,p.getAmount());
				ps.setString(3,p.getPaymentType().toString());
				ps.setString(4,p.getStud_id());
			}
		};
	}
	
	@Override
	protected PreparedStatementSetter getUpdatePreparedStatementSetter(final Payment p) {
		return new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setDouble(1,p.getAmount());
				ps.setString(2,p.getPaymentType().toString());
				ps.setString(3,p.getStud_id());
				ps.setString(4, p.getId());
			}
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
					ps.setString(3, p.getPaymentType().toString());
					ps.setString(4, p.getStud_id());
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
					ps.setString(2, p.getPaymentType().toString());
					ps.setString(3, p.getStud_id());
					ps.setString(4, p.getId());
				}
				
				@Override
				public int getBatchSize() {
					return l.size();
				}
			};
	}
}

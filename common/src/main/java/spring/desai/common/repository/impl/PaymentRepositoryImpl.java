package spring.desai.common.repository.impl;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import spring.desai.common.model.enums.PaymentType;
import spring.desai.common.model.pojo.Payment;
import spring.desai.common.model.pojo.Persistable;
import spring.desai.common.repository.AbstractBaseRepository;
import spring.desai.common.repository.PaymentRepository;
import spring.desai.common.repository.RepositoryDataAccessException;
import spring.desai.common.utils.DataBaseConstants;

@Repository(value="paymentRepository")
public class PaymentRepositoryImpl extends AbstractBaseRepository implements PaymentRepository {

	@Override
	public Payment findById(String id) throws RepositoryDataAccessException {
		try {
			return getJdbcTemplate().query(getFindBySql(DataBaseConstants.PAYMENT_TABLE_NAME, DataBaseConstants.ID), new Object[] { id }, getPaymentRowMapper()).get(0);
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
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
	public void deleteById(String id) throws RepositoryDataAccessException {
		deleteImpl(getDeleteBySql(DataBaseConstants.PAYMENT_TABLE_NAME, DataBaseConstants.ID), id);
	}

	@Override
	public int countAll() throws RepositoryDataAccessException {
		return countAllImpl(getCountAllSql(DataBaseConstants.PAYMENT_TABLE_NAME));
	}

	@Override
	public Collection<Payment> findByType(PaymentType type) throws RepositoryDataAccessException {
		try {
			return null;
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}

	@Override
	public void save(Persistable persistable) throws RepositoryDataAccessException {
		try {
			
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}

	@Override
	public void update(Persistable persistable) throws RepositoryDataAccessException {
		try {
			
		} catch (DataAccessException e) {
			throw new RepositoryDataAccessException(e);
		}
	}

	@Override
	protected String getInsertSql() {
		return "INSERT INTO payment VALUES (?, ?, ?, ?)";
	}

	@Override
	protected String getUpdateSql() {
		return "UPDATE payment SET amount = ?, type = ?, student_id = ? where id = ?";
	}

}

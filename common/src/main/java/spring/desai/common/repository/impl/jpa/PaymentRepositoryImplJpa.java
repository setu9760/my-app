package spring.desai.common.repository.impl.jpa;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import spring.desai.common.model.enums.PaymentType;
import spring.desai.common.model.pojo.Payment;
import spring.desai.common.repository.PaymentRepository;
import spring.desai.common.repository.exception.RepositoryDataAccessException;

@Repository("paymentRepository")
public class PaymentRepositoryImplJpa extends BaseJpaRepository<Payment> implements PaymentRepository {

	@Override
	public void saveAll(Collection<Payment> persistables) throws RepositoryDataAccessException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateAll(Collection<Payment> persistables) throws RepositoryDataAccessException {
		// TODO Auto-generated method stub

	}

	@Override
	public Collection<Payment> findbyStudentId(String stud_id) throws RepositoryDataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Payment> findByType(PaymentType type) throws RepositoryDataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<Payment> getEntityClass() {
		return Payment.class;
	}

	@Override
	protected String getInsertSql() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getUpdateSql() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getNameField() {
		// TODO Auto-generated method stub
		return null;
	}

}

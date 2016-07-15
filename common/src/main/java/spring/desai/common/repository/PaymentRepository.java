package spring.desai.common.repository;

import java.util.Collection;

import spring.desai.common.model.Payment;
import spring.desai.common.model.enums.PaymentType;
import spring.desai.common.repository.exception.RepositoryDataAccessException;

public interface PaymentRepository extends BasePersistableRepository<Payment> {

	Collection<Payment> findbyStudentId(String studId) throws RepositoryDataAccessException;

	double getTotalPaid(String studId) throws RepositoryDataAccessException;
	
	Collection<Payment> findByType(PaymentType type) throws RepositoryDataAccessException;

}

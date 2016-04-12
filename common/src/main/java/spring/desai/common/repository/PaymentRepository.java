package spring.desai.common.repository;

import java.util.Collection;

import spring.desai.common.model.enums.PaymentType;
import spring.desai.common.model.pojo.Payment;
import spring.desai.common.repository.exception.RepositoryDataAccessException;

public interface PaymentRepository extends BasePersistableRepository<Payment> {

	Collection<Payment> findbyStudentId(String stud_id) throws RepositoryDataAccessException;

	Collection<Payment> findByType(PaymentType type) throws RepositoryDataAccessException;

}

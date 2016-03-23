package spring.desai.common.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;

import spring.desai.common.model.enums.PaymentType;
import spring.desai.common.model.pojo.Payment;

public interface PaymentRepository extends BasePersistableRepository {

	Payment findById(String id) throws DataAccessException;

	Collection<Payment> findbyStudentId(String stud_id) throws DataAccessException;

	Collection<Payment> findByType(PaymentType type) throws DataAccessException;

}

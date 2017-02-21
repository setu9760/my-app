package spring.desai.common.repository;

import java.util.Collection;

import spring.desai.common.model.Payment;
import spring.desai.common.model.enums.PaymentType;
import spring.desai.common.repository.exception.RepositoryDataAccessException;

/**
 *	Specific interface of {@link BasePersistableRepository} for type {@link Payment} 
 * @author desai
 */
public interface PaymentRepository extends BasePersistableRepository<Payment> {

	/**
	 * Returns all the payments made by a student from the id in a collection
	 * @param studId
	 * @return
	 * @throws RepositoryDataAccessException
	 */
	Collection<Payment> findByStudentId(String studId) throws RepositoryDataAccessException;

	/**
	 * Calculates and returns the total amount paid by a student from the id
	 * @param studId
	 * @return
	 * @throws RepositoryDataAccessException
	 */
	double getTotalPaid(String studId) throws RepositoryDataAccessException;
	
	/**
	 * Returns all the payment for the input {@link PaymentType}
	 * @param type
	 * @return
	 * @throws RepositoryDataAccessException
	 */
	Collection<Payment> findByType(PaymentType type) throws RepositoryDataAccessException;

}

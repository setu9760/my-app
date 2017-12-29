package spring.desai.common.repository;

import spring.desai.common.repository.exception.RepositoryDataAccessException;

/**
 * Repository used to provide functionalities to calculation of total payment and updating it.
 * @author desai
 */
public interface StudentTotalToPayRepository {

	/**
	 * Adds the given amount to totalToPay for this studentId
	 * @param studId
	 * @param addToTotal
	 * @return
	 * @throws RepositoryDataAccessException
	 */
	Double updateTotalToPay(String studId, double addToTotal) throws RepositoryDataAccessException;
	
	/**
	 * @param studId
	 * @return the total amount owed by student for payments.
	 * @throws RepositoryDataAccessException
	 */
	public Double getCurrentTotalToPay(String studId) throws RepositoryDataAccessException;
	
	public void addDefaultTotalToPayRow(String studId) throws RepositoryDataAccessException;
}

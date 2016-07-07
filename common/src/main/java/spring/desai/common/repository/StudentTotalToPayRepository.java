package spring.desai.common.repository;

import spring.desai.common.repository.exception.RepositoryDataAccessException;

public interface StudentTotalToPayRepository {

	double updateTotalToPayBy(String studId, double addToTotal) throws RepositoryDataAccessException;
	
	public double getCurrentTotalToPay(String studId) throws RepositoryDataAccessException;
}

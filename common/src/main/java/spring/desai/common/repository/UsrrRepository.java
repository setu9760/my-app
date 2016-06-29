package spring.desai.common.repository;

import org.springframework.security.core.userdetails.UserDetails;

import spring.desai.common.model.User;
import spring.desai.common.repository.exception.RepositoryDataAccessException;

public interface UsrrRepository {

	UserDetails getUserLoginDetails(String userId) throws RepositoryDataAccessException;
	
	void incrementFailedAttempt(String userId) throws RepositoryDataAccessException;
	
	void updatePassword(String userId, String encryptedPassword) throws RepositoryDataAccessException;

	void createUser(String userId, String encryptedPassword) throws RepositoryDataAccessException;
	
	void deleteUser(String userId) throws RepositoryDataAccessException;
}

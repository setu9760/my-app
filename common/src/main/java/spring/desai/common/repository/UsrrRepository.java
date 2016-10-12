package spring.desai.common.repository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import spring.desai.common.model.User.SIGN_ON_STATUS;
import spring.desai.common.repository.exception.RepositoryDataAccessException;

/**
 * 
 * @author desai
 */
public interface UsrrRepository {

	/**
	 * Constructs and returns {@link UserDetails} object to be used by {@link UserDetailsService} for authentication purposes.
	 * @param userId
	 * @return
	 * @throws RepositoryDataAccessException
	 */
	UserDetails getUserLoginDetails(String userId) throws RepositoryDataAccessException;
	
	/**
	 * Increment the failed attempts number for this user after failed authentication attempt.
	 * This needs to be called after each unsuccessful authentication attempt.
	 * @param userId
	 * @throws RepositoryDataAccessException
	 */
	void incrementFailedAttempt(String userId) throws RepositoryDataAccessException;
	
	/**
	 * Resets the failed attempt to 0 after the user has successfully logged it. 
	 * @param userId
	 * @throws RepositoryDataAccessException
	 */
	void resetFailedAttempt(String userId) throws RepositoryDataAccessException;
	
	/**
	 * Updates the sign on status for given userId 
	 * @param userId
	 * @param status
	 * @throws RepositoryDataAccessException
	 */
	public void updateSignOnStatus(String userId, SIGN_ON_STATUS status) throws RepositoryDataAccessException;
	
	/**
	 * Updates the user password
	 * @param userId
	 * @param encryptedPassword
	 * @throws RepositoryDataAccessException
	 */
	void updatePassword(String userId, String encryptedPassword) throws RepositoryDataAccessException;

	/**
	 * creates the user for given userId with given encryptedPassord
	 * @param userId
	 * @param encryptedPassword
	 * @throws RepositoryDataAccessException
	 */
	void createUser(String userId, String encryptedPassword) throws RepositoryDataAccessException;
	
	/**
	 * deletes the user from given userId
	 * @param userId
	 * @throws RepositoryDataAccessException
	 */
	void deleteUser(String userId) throws RepositoryDataAccessException;
}

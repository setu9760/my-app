package spring.desai.common.repository;

import java.util.Collection;

import spring.desai.common.model.User;
import spring.desai.common.repository.exception.RepositoryDataAccessException;

/**
 * Specific interface of {@link BasePersistableRepository} for type {@link User}
 * @author desai
 */
public interface UserRepository extends BasePersistableRepository<User> {

	/**
	 * @return returns collection of {@link User} which has status locked out in persistent store
	 * @throws RepositoryDataAccessException
	 */
	Collection<User> findLockedOutUsers() throws RepositoryDataAccessException;
	
	/**
	 * @return returns collection of currently active users
	 * @throws RepositoryDataAccessException
	 */
	Collection<User> findCurrentlyActiveUsers() throws RepositoryDataAccessException;
	
	/**
	 * @return returns collections of user who have exceede max failed attempts.
	 * @throws RepositoryDataAccessException
	 */
	Collection<User> findUsersWithMaxFailedAttempts() throws RepositoryDataAccessException;
	
	/**
	 * resets the given user's locked out status 
	 * @param user
	 * @throws RepositoryDataAccessException
	 */
	void resetLockedOutUser(User user) throws RepositoryDataAccessException;
	
	/**
	 * resets the given users' locked out status
	 * @param users
	 * @throws RepositoryDataAccessException
	 */
	void resetLockedOutUsers(Collection<User> users) throws RepositoryDataAccessException;
	
	/**
	 * Check if the user with given userId exists 
	 * @param userId
	 * @return
	 */
	boolean isExistingUser(String userId);
}

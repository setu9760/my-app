package spring.desai.common.repository;

import java.util.Collection;

import spring.desai.common.model.pojo.User;
import spring.desai.common.repository.exception.RepositoryDataAccessException;

public interface UserRepository extends BasePersistableRepository<User> {

	Collection<User> findLockedOutUsers() throws RepositoryDataAccessException;
	
	Collection<User> findCurrentlyActiveUsers() throws RepositoryDataAccessException;
	
	Collection<User> findUsersWithMaxFailedAttempts() throws RepositoryDataAccessException;
	
	void resetLockedOutUser(User user) throws RepositoryDataAccessException;
	
	void resetLockedOutUsers(Collection<User> users) throws RepositoryDataAccessException;
}

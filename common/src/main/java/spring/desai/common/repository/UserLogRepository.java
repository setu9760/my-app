package spring.desai.common.repository;

import spring.desai.common.repository.exception.RepositoryDataAccessException;

public interface UserLogRepository {

	void logUserActivity(String userId, int activity, String ipAddress) throws RepositoryDataAccessException;
	
}

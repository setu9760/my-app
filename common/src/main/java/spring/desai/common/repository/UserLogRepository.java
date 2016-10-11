package spring.desai.common.repository;

import spring.desai.common.repository.exception.RepositoryDataAccessException;

/**
 *  This repository provides auditing capabilities for user sign in/out attempts.  
 * @author desai
 */
public interface UserLogRepository {

	/**
	 * Logs the user activity in the persistent store. The activity refers to following states currently.
	 * 
	 * <p>
	 * <b>
	 * 0  : successful sign out
	 * <p>1  : successful sign in
	 * <p>-1 : Invalid sign in attempt
	 * </b>
	 * 
	 * @param userId userId of the user attempting to authenticate
	 * @param activity authentication activity
	 * @param ipAddress from where the authentication is being attempted.
	 * @throws RepositoryDataAccessException
	 */
	void logUserActivity(String userId, int activity, String ipAddress) throws RepositoryDataAccessException;
	
}

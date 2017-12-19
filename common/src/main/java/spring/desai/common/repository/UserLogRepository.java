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
	 * <p>1  : successful sign in
	 * <p>2  : successful sign out
	 * <p>3  : Credentials expired
	 * <p>4  : Account locked
	 * <p>5  : User already logged in
	 * <p>6  : User exceeded MAX_FAILED_ATTEMPTS
	 * <p>7  : Bad credentials supplied
	 * <p>8  : Username not found
	 * <p>-1 : Generic
	 * </b>
	 * 
	 * @param userId userId of the user attempting to authenticate
	 * @param activity authentication activity
	 * @param ipAddress from where the authentication is being attempted.
	 * @throws RepositoryDataAccessException
	 */
	void logUserActivity(String userId, int activity, String ipAddress) throws RepositoryDataAccessException;
	
}

package spring.desai.common.repository;

public interface UserLogRepository {

	void logUserActivity(String userId, int activity, String ipAddress);
	
}

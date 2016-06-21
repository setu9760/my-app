package spring.desai.common.repository;

import org.springframework.security.core.userdetails.UserDetails;

public interface UsrrRepository {

	UserDetails getUserLoginDetails(String userId);
	
	void incrementFailedAttempt(String userId);
}

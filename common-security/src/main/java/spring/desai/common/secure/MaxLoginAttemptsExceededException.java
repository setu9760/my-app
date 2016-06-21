package spring.desai.common.secure;

import org.springframework.security.core.AuthenticationException;

public class MaxLoginAttemptsExceededException extends AuthenticationException {

	private static final long serialVersionUID = 5700531640441037142L;

	public MaxLoginAttemptsExceededException(String userId, int loginAttemps) {
		super("User " + userId + " has exceeded MAX_FAILED_ATTEMPTS while loggin in. Current login attemps are " + loginAttemps);
	}
	
}

package spring.desai.common.secure;

import org.springframework.security.core.AuthenticationException;

public class UserAlreadyLoggedinException extends AuthenticationException {

	private static final long serialVersionUID = -4796376815624555320L;

	public UserAlreadyLoggedinException(String userId) {
		super("User " + userId + " is already logged in.");
	}
	
}

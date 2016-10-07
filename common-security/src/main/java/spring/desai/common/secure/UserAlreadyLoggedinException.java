package spring.desai.common.secure;

import org.springframework.security.authentication.AccountStatusException;

public class UserAlreadyLoggedinException extends AccountStatusException {

	private static final long serialVersionUID = -4796376815624555320L;

	public UserAlreadyLoggedinException(String userId) {
		super("User " + userId + " is already logged in.");
	}
	
}

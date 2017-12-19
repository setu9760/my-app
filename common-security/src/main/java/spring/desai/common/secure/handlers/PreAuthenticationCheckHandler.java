package spring.desai.common.secure.handlers;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.stereotype.Component;

import spring.desai.common.secure.MaxLoginAttemptsExceededException;
import spring.desai.common.secure.UserAlreadyLoggedinException;
import spring.desai.common.secure.user.UserLoginDetails;

@Component("preAuthenticationCheckHandler")
public class PreAuthenticationCheckHandler implements UserDetailsChecker {

	// TODO: Make this configurable
	private static final int MAX_FAILED_ATTEMPTS = 4;
	
	@Override
	public void check(UserDetails user) {
		if (!user.isAccountNonLocked())
			throw new LockedException("User account is locked");
		if (!user.isEnabled())
			throw new DisabledException("User is disabled");
		if (!user.isAccountNonExpired())
			throw new AccountExpiredException("User account has expired");
		if (user instanceof UserLoginDetails) {
			UserLoginDetails uld = (UserLoginDetails) user;
			if (uld.getFailedAttempts() >= MAX_FAILED_ATTEMPTS) 
				throw new MaxLoginAttemptsExceededException(uld.getUsername(), uld.getFailedAttempts());
			if (uld.isAlreadyLoggedIn()) 
				throw new UserAlreadyLoggedinException(uld.getUsername());
		}
	}

}

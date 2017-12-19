package spring.desai.common.secure.handlers;

import java.io.IOException;

import javax.security.auth.login.CredentialExpiredException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import spring.desai.common.repository.UserLogRepository;
import spring.desai.common.repository.UsrrRepository;
import spring.desai.common.secure.MaxLoginAttemptsExceededException;
import spring.desai.common.secure.UserAlreadyLoggedinException;

@Component("authenticationFailureHandler")
public class AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	private final Logger log = Logger.getLogger(getClass());

	@Autowired
	@Qualifier("userLogRepository")
	private UserLogRepository userLogRepository;

	@Autowired
	@Qualifier("usrrRepository")
	private UsrrRepository usrrRepository;

	public AuthenticationFailureHandler() {
		super("/login?error=1");
	}

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		String userName = request.getParameter("username");
		usrrRepository.incrementFailedAttempt(userName);
		int failureCode = determineFailureCause(exception);
		log.error(String.format("Invalid login attempt by user '%s' (error code '%s')", userName, failureCode));
		userLogRepository.logUserActivity(userName, failureCode, request.getRemoteHost());
		super.onAuthenticationFailure(request, response, exception);
	}

	private int determineFailureCause(Exception e) {
		if (e instanceof CredentialExpiredException)
			return 3;
		else if (e instanceof LockedException)
			return 4;
		else if (e instanceof UserAlreadyLoggedinException)
			return 5;
		else if (e instanceof MaxLoginAttemptsExceededException)
			return 6;
		else if (e instanceof BadCredentialsException)
			return 7;
		else if (e instanceof UsernameNotFoundException)
			return 8;
		return -1;
	}
}

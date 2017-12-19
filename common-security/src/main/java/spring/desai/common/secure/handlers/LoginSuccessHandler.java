package spring.desai.common.secure.handlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import spring.desai.common.model.User.SIGN_ON_STATUS;
import spring.desai.common.repository.UserLogRepository;
import spring.desai.common.repository.UsrrRepository;

@Component("loginSuccessHandler")
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
	
	private final Logger log = Logger.getLogger(getClass());
	
	@Autowired
	@Qualifier("userLogRepository")
	private UserLogRepository userLogRepository;
	
	@Autowired
	@Qualifier("usrrRepository")
	private UsrrRepository usrrRepository;
	
	@Autowired
	@Qualifier("ajaxAuthenticationSuccessHandler")
	private AuthenticationSuccessHandler defaultHandler;
	
	public LoginSuccessHandler() {
        super();
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	log.info(String.format("Logged In User '%s'", ud.getUsername()));
    	usrrRepository.resetFailedAttempt(ud.getUsername());
    	usrrRepository.updateSignOnStatus(ud.getUsername(), SIGN_ON_STATUS.LOGGED_IN);
        userLogRepository.logUserActivity(ud.getUsername(), 1, request.getRemoteHost());
        defaultHandler.onAuthenticationSuccess(request, response, authentication);
    }

}

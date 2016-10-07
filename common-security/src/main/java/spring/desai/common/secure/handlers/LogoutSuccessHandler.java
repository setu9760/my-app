package spring.desai.common.secure.handlers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import spring.desai.common.repository.UserLogRepository;

@Component("logoutSuccessHandler")
public class LogoutSuccessHandler implements LogoutHandler {

	@Autowired
	@Qualifier("userLogRepository")
	private UserLogRepository userLogRepository;
	
	public LogoutSuccessHandler() {
		super();
	}
	
	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println("Logged OUT User " + ud);
		userLogRepository.logUserActivity(ud.getUsername(), 2, request.getRemoteHost());
	}

}

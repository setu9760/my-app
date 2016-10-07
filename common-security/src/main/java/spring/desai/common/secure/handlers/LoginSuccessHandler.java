package spring.desai.common.secure.handlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import spring.desai.common.repository.UserLogRepository;

@Component("loginSuccessHandler")
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
	
	@Autowired
	@Qualifier("userLogRepository")
	private UserLogRepository userLogRepository;
	
	
	@Autowired
	@Qualifier("ajaxAuthenticationSuccessHandler")
	private AuthenticationSuccessHandler defaultHandler;
	
	public LoginSuccessHandler() {
        super();
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	System.out.println("Logged In User " + ud);
        userLogRepository.logUserActivity(ud.getUsername(), 1, request.getRemoteHost());
        defaultHandler.onAuthenticationSuccess(request, response, authentication);
    }

}

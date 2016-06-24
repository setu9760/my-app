package spring.desai.common.secure.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component("authenticationFacade")
public class AuthenticationFacadeImpl implements AuthenticationFacade {

	@Override
	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	@Override
	public boolean isGrantedFor(String role) {
		Authentication auth = getAuthentication();
		if (auth != null) {
			for (GrantedAuthority authority : auth.getAuthorities()) {
				if(authority.getAuthority().equals(role))
					return true;
			}
		}
		return false;
	}
}

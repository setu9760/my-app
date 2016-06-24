package spring.desai.common.secure.auth;

import org.springframework.security.core.Authentication;

/**
 * Authentication facade provides convenience methods to obtain the user authentication and check available roles for operations to be performed. 
 * Can be used in web and service layers as it does not tightly couple with http requests. 
 * @author desai
 *
 */
public interface AuthenticationFacade {

	/**
	 * Get authentication for current user.
	 * @return Current user authentication or null of no authentication performed.
	 */
	Authentication getAuthentication();
	
	/**
	 * Check the current user is authorised with the role. 
	 * @param role Roel to check against
	 * @return true only and only currently logged in user contains the role. false otherwise
	 */
	boolean isGrantedFor(String role);
}

package spring.desai.common.secure.service;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import spring.desai.common.model.User;
import spring.desai.common.repository.RoleRepository;
import spring.desai.common.repository.UserRepository;
import spring.desai.common.repository.UsrrRepository;
import spring.desai.common.secure.MaxLoginAttemptsExceededException;
import spring.desai.common.secure.UserAlreadyLoggedinException;
import spring.desai.common.secure.user.UserLoginDetails;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	@Qualifier("userRepository")
	private UserRepository userRepository;
	
	@Autowired
	@Qualifier("roleRepository")
	private RoleRepository roleRepository;
	
	@Autowired
	@Qualifier("usrrRepository")
	private UsrrRepository usrrRepository;
	
	private static final Logger log = Logger.getLogger(UserDetailsServiceImpl.class);
	
	// TODO: Fetch this via some sort of solution property.
	private static final int MAX_FAILED_ATTEMPTS = 4;
	
	@Override
//	@Transactional(noRollbackFor=AuthenticationException.class)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if (log.isDebugEnabled()) {
			log.debug("User " + username + " performing login.");
		}
		User user = userRepository.findById(username);
		if (user == null) {
			throw new UsernameNotFoundException("Username not found in database: " + username);
		}
		UserLoginDetails userLogin = (UserLoginDetails) usrrRepository.getUserLoginDetails(username);
		
		if (userLogin.getFailedAttempts() >= MAX_FAILED_ATTEMPTS) {
			throw new MaxLoginAttemptsExceededException(username, userLogin.getFailedAttempts());
		}
		if (userLogin.isAlreadyLoggedIn()) {
			throw new UserAlreadyLoggedinException(username);
		}
		
		//TODO Modify roleRepository to return collection of GrantedAuthority i.e. Roles
		Collection<? extends GrantedAuthority> roles = roleRepository.getRolesForUserId(username);
		userLogin.addAllAuthorities(roles);
		return userLogin;
	}
	
}

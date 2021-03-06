package spring.desai.common.secure.service;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import spring.desai.common.model.Role;
import spring.desai.common.model.User;
import spring.desai.common.repository.RoleRepository;
import spring.desai.common.repository.UserRepository;
import spring.desai.common.repository.UsrrRepository;
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
		
		Collection<Role> roles = roleRepository.getRolesForUserId(username);
		userLogin.addAllAuthorities(roles);
		return userLogin;
	}
	
}

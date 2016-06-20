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
import org.springframework.transaction.annotation.Transactional;

import spring.desai.common.model.pojo.User;
import spring.desai.common.repository.RoleRepository;
import spring.desai.common.repository.UserRepository;
import spring.desai.common.repository.UsrrRepository;
import spring.desai.common.security.user.UserLoginDetails;

@Service
public class SecurityUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	@Qualifier("userRepository")
	private UserRepository userRepository;
	
	@Autowired
	@Qualifier("roleRepository")
	private RoleRepository roleRepository;
	
	@Autowired
	private UsrrRepository usrrRepository;
	
	private static final Logger log = Logger.getLogger(SecurityUserDetailsServiceImpl.class);
	
	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findById(username);
		if (user == null) {
			throw new UsernameNotFoundException("Username not found in database: " + username);
		}
		UserLoginDetails userLogin = (UserLoginDetails) usrrRepository.getUserLoginDetails(username);
		
		//TODO Modify roleRepository to return collection of GrantedAuthority i.e. Roles
		@SuppressWarnings("unchecked")
		Collection<GrantedAuthority> roles = (Collection<GrantedAuthority>) roleRepository.getRolesForUserId(username);
		userLogin.addAllAuthorities(roles);
		return userLogin;
	}
	
}

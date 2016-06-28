package spring.desai.common.service;

import java.util.Collection;

import spring.desai.common.model.Role;
import spring.desai.common.model.User;

public interface AdminService {

	void createUser(User user, String encryptedPassword);
	
	boolean isExistingUser(String userId);
	
	void assignRole(User user, Role role);
	
	void assignRoles(User user, Collection<Role> roles);
}

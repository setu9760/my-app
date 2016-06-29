package spring.desai.common.service;

import java.util.Collection;

import spring.desai.common.model.Role;
import spring.desai.common.model.User;

public interface AdminService {

	void createUser(User user, String encryptedPassword) throws ServiceException;

	void createUser(User user, String encryptedPassword, Collection<Role> rolesToAssign) throws ServiceException;

	void removeUser(User user) throws ServiceException;
	
	boolean isExistingUser(String userId) throws ServiceException;

	void assignRoles(User user, Collection<Role> roles) throws ServiceException;
	
	void unassignRoles(User user, Collection<Role> roles) throws ServiceException;
	
	void revokeAllRoles(User user) throws ServiceException;
}

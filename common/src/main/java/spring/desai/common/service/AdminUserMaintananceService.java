package spring.desai.common.service;

import java.util.Collection;

import spring.desai.common.model.Role;
import spring.desai.common.model.User;
import spring.desai.common.service.exception.ServiceException;

public interface AdminUserMaintananceService {

	void createUser(User user, String rawPassword) throws ServiceException;

	void createUser(User user, String rawPassword, Collection<Role> rolesToAssign) throws ServiceException;

	public User getUser(String userId) throws ServiceException;
	
	boolean isExistingUser(String userId) throws ServiceException;

	void assignRoles(User user, Collection<Role> roles) throws ServiceException;
	
	void unassignRoles(User user, Collection<Role> roles) throws ServiceException;
	
	void revokeAllRoles(User user) throws ServiceException;
	
	void resetUserSignOn(String userId) throws ServiceException;

	void updateUserPersonalDetails(User user) throws ServiceException;

	Collection<User> getAllUsers() throws ServiceException;

	void removeUser(String userId) throws ServiceException;
	
}

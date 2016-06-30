package spring.desai.common.service;

import spring.desai.common.model.User;

public interface UserService {

	void updateUserDetails(User user) throws ServiceException;
	
	void updatePassword(User user, String newRawPassword) throws ServiceException;
	
}

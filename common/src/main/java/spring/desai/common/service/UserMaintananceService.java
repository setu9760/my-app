package spring.desai.common.service;

import spring.desai.common.model.User;
import spring.desai.common.service.exception.ServiceException;

public interface UserMaintananceService {

	void updateUserDetails(User user) throws ServiceException;
	
	void updatePassword(User user, String newRawPassword) throws ServiceException;
	
}

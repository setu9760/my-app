package spring.desai.common.secure.repository;

import spring.desai.common.security.user.UserLogin;

public interface UserLoginHelperRepository {

	UserLogin loginUser(UserLogin login);
	
	UserLogin logoutUser(UserLogin login);
}

package spring.desai.common.service.exception;

import spring.desai.common.utils.I18N;

public class UsernameNotUniqueException extends ServiceException {

	private static final long serialVersionUID = 17567L;

	public UsernameNotUniqueException(String userName) {
		super(I18N.getString("duplicate.username.exception", userName));
	}

}

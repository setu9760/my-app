package spring.desai.common.service.exception;

import spring.desai.common.utils.I18N;

public class ServiceException extends Exception {

	private static final long serialVersionUID = 1673575675676L;

	public ServiceException(String operationName, Throwable t) {
		super(I18N.getString("service.exception", operationName), t);
	}
	
	public ServiceException(String msg) {
		super(msg);
	}
	
	public ServiceException(Throwable t) {
		super(t);
	}
	
}

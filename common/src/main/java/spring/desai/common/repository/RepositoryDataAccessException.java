package spring.desai.common.repository;

import org.springframework.dao.DataAccessException;

public class RepositoryDataAccessException extends DataAccessException {

	private static final long serialVersionUID = 3865238751313316409L;

	public RepositoryDataAccessException(String msg) {
		super(msg);
	}

	public RepositoryDataAccessException(String msg, Throwable t) {
		super(msg, t);
	}
	
	public RepositoryDataAccessException(Throwable t){
		super("Error occured while accessing/modofying repository", t);
	}
}

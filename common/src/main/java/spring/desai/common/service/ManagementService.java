package spring.desai.common.service;

import spring.desai.common.model.Subject;
import spring.desai.common.model.Tutor;
import spring.desai.common.service.exception.ServiceException;

public interface ManagementService {

	void saveNewSubject(Subject subject) throws ServiceException;
	
	void saveNewTutor(Tutor tutor) throws ServiceException;
	
	void updateSubject(Subject subject) throws ServiceException;
	
	void updateTutor(Tutor tutor) throws ServiceException;
	
}

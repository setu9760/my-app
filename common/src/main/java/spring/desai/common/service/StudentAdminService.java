package spring.desai.common.service;

import java.util.Collection;

import spring.desai.common.model.Payment;
import spring.desai.common.model.Scholorship;
import spring.desai.common.model.Student;
import spring.desai.common.model.Subject;
import spring.desai.common.service.exception.ServiceException;

public interface StudentAdminService {

	void save(Student students) throws ServiceException;
	
	void saveAll(Collection<Student> students) throws ServiceException;
	
	void update(Student student) throws ServiceException;
	
	void updateAll(Collection<Student> students) throws ServiceException;
	
	void addToSubject(Student student, Subject subject) throws ServiceException;
	
	void makePayment(Payment payment) throws ServiceException;
	
	void awardScholorship(Scholorship scholorship) throws ServiceException;
	
	void removeFromSubject(Student student, Subject subject) throws ServiceException;
	
	void amendPayment(Payment payment) throws ServiceException;
	
	void amendScholorship(Scholorship scholorship) throws ServiceException;
}

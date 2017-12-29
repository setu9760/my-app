package spring.desai.common.service;

import java.util.Collection;

import spring.desai.common.model.Cost;
import spring.desai.common.model.Payment;
import spring.desai.common.model.Role;
import spring.desai.common.model.Scholarship;
import spring.desai.common.model.Student;
import spring.desai.common.model.Subject;
import spring.desai.common.model.Tutor;
import spring.desai.common.service.exception.ServiceException;

public interface SchoolService {

	void save(Student students) throws ServiceException;
	
	void saveAll(Collection<Student> students) throws ServiceException;
	
	void update(Student student) throws ServiceException;
	
	void updateAll(Collection<Student> students) throws ServiceException;
	
	void addToSubject(String studentId, String subjectId) throws ServiceException;
	
	void removeFromSubject(String studentId, String subjectId) throws ServiceException;
	
	void makePayment(Payment payment) throws ServiceException;
	
	void amendPayment(Payment payment) throws ServiceException;
	
	void issueRefund(Payment payment) throws ServiceException;
	
	void awardScholorship(Scholarship scholorship) throws ServiceException;
	
	
	void amendScholorship(Scholarship scholorship) throws ServiceException;

	void deactivateStudent(String studentId) throws ServiceException;
	
	void save(Subject subject) throws ServiceException;
	
	void update(Subject subject) throws ServiceException;
	
	void save(Cost cost) throws ServiceException;
	
	void update(Cost Cost) throws ServiceException;
	
	void save(Tutor tutor) throws ServiceException;
	
	void update(Tutor tutor) throws ServiceException;
	
	void save(Role role) throws ServiceException;
	
	void update(Role role) throws ServiceException;
}

package spring.desai.common.service;

import java.util.Collection;

import spring.desai.common.model.Payment;
import spring.desai.common.model.Student;
import spring.desai.common.model.Subject;
import spring.desai.common.model.Tutor;
import spring.desai.common.service.exception.ServiceException;

public interface ReadOnlyService {

	public Collection<Student> getAllStudents() throws ServiceException;

	public Student getStudentById(String id) throws ServiceException;

	public Collection<Subject> getAllSubjects() throws ServiceException;

	public Subject getSubjectById(String id) throws ServiceException;

	public Collection<Tutor> getAllTutors() throws ServiceException;

	public Tutor getTutorById(String id) throws ServiceException;

	public Collection<Tutor> getTutorsFromSubjectId(String subjectId) throws ServiceException;
	
	public Collection<Payment> getPaymentsForStudent(String stud_id) throws ServiceException;
	
	public Payment getPaymentById(String id) throws ServiceException;
	
}

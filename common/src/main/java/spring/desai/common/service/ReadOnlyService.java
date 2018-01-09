package spring.desai.common.service;

import java.util.Collection;

import spring.desai.common.model.Cost;
import spring.desai.common.model.Payment;
import spring.desai.common.model.Persistable;
import spring.desai.common.model.Role;
import spring.desai.common.model.Scholarship;
import spring.desai.common.model.Student;
import spring.desai.common.model.Subject;
import spring.desai.common.model.Tutor;
import spring.desai.common.service.exception.ServiceException;

public interface ReadOnlyService {

	public Persistable getEntityById(String entityType, String id) throws ServiceException;
	
	public Collection<? extends Persistable> getAllEntities(String entityType) throws ServiceException;
	
	public Double getTotalToPay(String studId) throws ServiceException;
	
	public Student getStudentById(String id) throws ServiceException;


	public Subject getSubjectById(String id) throws ServiceException;


	public Tutor getTutorById(String id) throws ServiceException;

	public Collection<Tutor> getTutorsFromSubjectId(String subjectId) throws ServiceException;

	public Collection<Payment> getPaymentsForStudent(String studid) throws ServiceException;

	public Payment getPaymentById(String id) throws ServiceException;

	public Role getRole(String role) throws ServiceException;

	public Role getAdminRole() throws ServiceException;

	public Role getRestUserRole() throws ServiceException;

	public Cost getCostByCostcode(String costCode) throws ServiceException;

	Scholarship getScholarshipById(String id) throws ServiceException;
}

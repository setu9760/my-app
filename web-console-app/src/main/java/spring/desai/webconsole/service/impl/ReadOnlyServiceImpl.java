package spring.desai.webconsole.service.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.desai.common.model.Cost;
import spring.desai.common.model.Payment;
import spring.desai.common.model.Persistable;
import spring.desai.common.model.Role;
import spring.desai.common.model.Scholarship;
import spring.desai.common.model.Student;
import spring.desai.common.model.Subject;
import spring.desai.common.model.Tutor;
import spring.desai.common.service.BaseService;
import spring.desai.common.service.ReadOnlyService;
import spring.desai.common.service.exception.ServiceException;

@Transactional(readOnly = true)
@Service(value = "readOnlyService")
public class ReadOnlyServiceImpl extends BaseService implements ReadOnlyService {

	@Override
	public Persistable getEntityById(String entityType, String id) throws ServiceException {
		notNull(entityType);
		notNull(id);
		Persistable p = null;
		switch (entityType.toLowerCase()) {
		case "student":
			p = getStudentById(id);
			break;
		case "subject":
			p = getSubjectById(id);
			break;
		case "payment":
			p = getPaymentById(id);
			break;
		case "scholorship":
			p = getScholarshipById(id);
			break;
		case "tutor":
			p = getTutorById(id);
			break;
		case "role":
			p = getRole(id);
			break;
		case "cost":
			p = getCostByCostcode(id);
			break;
		}
		return p;
	}
	
	@Override
	public Student getStudentById(String id) {
		Student s = studentRepository.findById(id);
		if (s != null) {
			s.setSubjects(subjectRepository.getSubjectsForStudentId(id));
			s.setPayments(paymentRepository.findByStudentId(id));
			s.setScholarships(scholarshipRepository.findByStudentId(id));
		}
		return s;
	}
	
	@Override
	public Subject getSubjectById(String id) {
		return subjectRepository.findById(id);
	}

	@Override
	public Tutor getTutorById(String id) {
		return tutorRepository.findById(id);
	}
	
	@Override
	public Payment getPaymentById(String id) {
		return paymentRepository.findById(id);
	}
	
	@Override
	public Scholarship getScholarshipById(String id) {
		return scholarshipRepository.findById(id);
	}
	
	@Override
	public Cost getCostByCostcode(String costCode) {
		return costCodeRepository.findById(costCode);
	}
	
	@Override
	public Collection<? extends Persistable> getAllEntities(String entityType) throws ServiceException {
		notNull(entityType);
		Collection<? extends Persistable> coll = null;
		switch (entityType.toLowerCase()) {
		case "student":
			coll = studentRepository.getAll();
			for (Persistable s : coll) {
				((Student) s).setSubjects(subjectRepository.getSubjectsForStudentId(s.getId()));
				((Student) s).setPayments(paymentRepository.findByStudentId(s.getId()));
				((Student) s).setScholarships(scholarshipRepository.findByStudentId(s.getId()));
			}
			break;
		case "subject":
			coll = subjectRepository.getAll();
			break;
		
		case "tutor":
			coll = tutorRepository.getAll();
			break;
		case "role":
			coll = roleRepository.getAll();
			break;
		case "cost":
			coll = costCodeRepository.getAll();
			break;
		default :
			throw new IllegalArgumentException(String.format("Calling readOnlyService.getAllEntities(entity) with %s is not valid", entityType));
		}
		if (null == coll)
			coll = new ArrayList<>();
		return coll;
	}
	
	

	@Override
	public Double getTotalToPay(String studId) {
		return studentTotalToPayRepository.getCurrentTotalToPay(studId);
	}

	@Override
	public Collection<Tutor> getTutorsFromSubjectId(String subjectId) {
		return tutorRepository.getTutorsForSubject(subjectId);
	}

	@Override
	public Collection<Payment> getPaymentsForStudent(String studId) {
		return paymentRepository.findByStudentId(studId);
	}

	@Override
	public Role getAdminRole() {
		return getRole("ROLE_ADMIN_USER");
	}

	@Override
	public Role getRestUserRole() {
		return getRole("ROLE_REST_USER");
	}

	@Override
	public Role getRole(String role) {
		return roleRepository.findById(role);
	}
}

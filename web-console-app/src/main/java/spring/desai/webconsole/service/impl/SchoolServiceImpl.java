package spring.desai.webconsole.service.impl;
import static java.lang.String.format;

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
import spring.desai.common.model.enums.PaymentType;
import spring.desai.common.service.BaseService;
import spring.desai.common.service.SchoolService;
import spring.desai.common.service.exception.ServiceException;
import spring.desai.common.utils.I18N;

@Transactional
@Service("studentAdminService")
public class SchoolServiceImpl extends BaseService implements SchoolService {

	public void save(Persistable p) throws ServiceException {
		notNull(p);
		if (p instanceof Student) {
			Student student = (Student) p;
			studentRepository.save(student);
			studentTotalToPayRepository.addDefaultTotalToPayRow(student.getId());
			for(Subject s : student.getSubjects())
				addToSubject(student.getId(), s.getId());
			for(Payment payment : student.getPayments())
				makePayment(payment);
			for(Scholarship s : student.getScholarships())
				awardScholorship(s);
		} else if (p instanceof Subject) {
			subjectRepository.save((Subject) p);
		} else if (p instanceof Tutor) {
			tutorRepository.save((Tutor) p);
		} else if (p instanceof Cost) {
			costCodeRepository.save((Cost) p);
		} else if (p instanceof Role) {
			roleRepository.save((Role) p);
		} else {
			throw new IllegalArgumentException(format("Calling schoolService.save(persistable) for %s is not valid", p.getClass().getName()));
		}
	}
	
	@Override
	public void update(Persistable p) throws ServiceException {
		notNull(p);
		if (p instanceof Student) {
			studentRepository.update((Student)p);
		} else if (p instanceof Subject) {
			subjectRepository.update((Subject) p);
		} else if (p instanceof Tutor) {
			costCodeRepository.update((Cost) p);
		} else if (p instanceof Cost) {
			tutorRepository.update((Tutor) p);
		} else if (p instanceof Role) {
			roleRepository.update((Role) p);
		} else {
			throw new IllegalArgumentException(format("Calling schoolService.update(persistable) for %s is not valid", p.getClass().getName()));
		}
	}
	
	@Override
	public void saveAll(Collection<Student> students) throws ServiceException {
		notNull(students);
		// TODO This should use the saveAll methods from repositories instead of looping through the list. 
		for (Student s : students)
			save(s);
	}

	/**
	 * <b>NOTE - </b>This method must only be used to update personal details of the students. 
	 */
	@Override
	public void updateAll(Collection<Student> students) throws ServiceException {
		studentRepository.updateAll(students);
	}
	
	@Override
	public void deactivateStudent(String studentId) throws ServiceException {
		notNull(studentId);
		studentRepository.setActiveStatusById(studentId, -1);
	}

	@Override
	public void addToSubject(String studentId, String subjectId) throws ServiceException {
		notNull(studentId);
		notNull(subjectId);
		Subject subject = subjectRepository.findById(subjectId);
		if (null == subject)
			log.warn(format("Tried to add student to non-existing subjectId '%s'", subjectId));
		if (!subjectRepository.isStudentInSubject(studentId, subject)) {
			subjectRepository.addStudentToSubject(studentId, subject);
			studentTotalToPayRepository.updateTotalToPay(studentId, studentTotalToPayRepository.getCurrentTotalToPay(studentId)+costCodeRepository.findById(subject.getCostCode()).getAmount());
		} else {
			log.warn("Tried to add student to a subject more than once.");
		}
	}

	@Override
	public void removeFromSubject(String studentId, String subjectId) throws ServiceException {
		notNull(studentId);
		notNull(subjectId);
		Subject subject = subjectRepository.findById(subjectId);
		if (null == subject)
			log.warn(format("Tried to remove student from non-existing subjectId '%s'", subjectId));
		if (subjectRepository.isStudentInSubject(studentId, subject)) {
			subjectRepository.removeStudentFromSubject(studentId, subject);
			studentTotalToPayRepository.updateTotalToPay(studentId, studentTotalToPayRepository.getCurrentTotalToPay(studentId)-costCodeRepository.findById(subject.getCostCode()).getAmount());
		} else {
			log.warn("Tried to remove student for where student is not added yet to the subject");
		}
	}

	@Override
	public void makePayment(Payment payment) throws ServiceException {
		makePayment(payment, false);
	}

	private void makePayment(Payment payment, boolean isScholorshipPayment) throws ServiceException {
		notNull(payment);
		double totalToPay = studentTotalToPayRepository.getCurrentTotalToPay(payment.getStudentId());
		double totalPaid = paymentRepository.getTotalPaid(payment.getStudentId());
		if ((totalPaid + payment.getAmount()) <= totalToPay || payment.getAmount() < 0d) {
			paymentRepository.save(payment);
		} else {
			// If scholarship payment then allow payment to be higher then totaltopay.
			if (isScholorshipPayment)
				paymentRepository.save(payment);
			else
				throw new ServiceException(I18N.getString("higher.than.total.to.pay.not.allowed", new Object[]{ totalToPay, totalPaid, payment.getAmount()}))  ;
		}
		// I don't know how valid this is. updating total to pay here might need revisiting.
		studentTotalToPayRepository.updateTotalToPay(payment.getStudentId(), totalToPay - payment.getAmount());
	}

	@Override
	public void amendPayment(Payment payment) throws ServiceException {
		notNull(payment);
		Payment orig = paymentRepository.findById(payment.getId());
		if (!orig.getStudentId().equalsIgnoreCase(payment.getStudentId())) {
			log.warn(I18N.getString("error.update.studid.not.allowed"));
			throw new ServiceException(I18N.getString("error.update.studid.not.allowed"));
		} 
		double totalTopay = studentTotalToPayRepository.getCurrentTotalToPay(payment.getStudentId());
//		Payment oldPayment = paymentRepository.findById(payment.getId());
		double totalPaid = paymentRepository.getTotalPaid(payment.getStudentId());
		if ((totalPaid + payment.getAmount()) <= totalTopay) {
			paymentRepository.update(payment);
		} else {
			throw new ServiceException(I18N.getString("error.higher.payment.than.required", new Object[] {totalTopay, totalPaid}));
		}
		// TODO amend totalToPay here aswell.
		studentTotalToPayRepository.updateTotalToPay(payment.getStudentId(), totalTopay + orig.getAmount());
		totalTopay = studentTotalToPayRepository.getCurrentTotalToPay(payment.getStudentId());
		studentTotalToPayRepository.updateTotalToPay(payment.getStudentId(), totalTopay - payment.getAmount());
	}
	
	@Override
	public void issueRefund(Payment payment) throws ServiceException {
		makePayment(payment);
	}
	
	@Override
	public void awardScholorship(Scholarship scholorship) throws ServiceException {
		notNull(scholorship);
		scholarshipRepository.save(scholorship);
		// TODO create payment id properly. However I think it is valid to have same id as scholarship.
		makePayment(new Payment(scholorship.getId(), scholorship.getTotalAmount(), PaymentType.SCHOLORSHIP, scholorship.getStudentId(), "SCHLR_ID:" + scholorship.getId()), true);
	}
	
	@Override
	public void amendScholorship(Scholarship scholorship) throws ServiceException {
		notNull(scholorship);
		scholarshipRepository.update(scholorship);
		Payment p = paymentRepository.findById(scholorship.getId());
		p.setAmount(scholorship.getTotalAmount());
		amendPayment(p);
	}
}
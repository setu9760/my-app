package spring.desai.webconsole.service.impl;

import static org.springframework.util.Assert.notNull;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.desai.common.model.Payment;
import spring.desai.common.model.Scholarship;
import spring.desai.common.model.Student;
import spring.desai.common.model.Subject;
import spring.desai.common.model.enums.PaymentType;
import spring.desai.common.repository.StudentTotalToPayRepository;
import spring.desai.common.service.BaseService;
import spring.desai.common.service.StudentAdminService;
import spring.desai.common.service.exception.ServiceException;
import spring.desai.common.utils.I18N;

@Transactional
@Service("studentAdminService")
public class StudentAdminServiceImpl extends BaseService implements StudentAdminService {

	@Override
	public void save(Student student) throws ServiceException {
		studentRepository.save(student);
		studentTotalToPayRepository.addDefaultTotalToPayRow(student.getId());
		for(Subject s : student.getSubjects())
			addToSubject(student, s);
		for(Payment p : student.getPayments())
			makePayment(p);
		for(Scholarship s : student.getScholarships())
			awardScholorship(s);
		
	}

	@Override
	public void saveAll(Collection<Student> students) throws ServiceException {
		notNull(students);
		// TODO This should use the saveAll methods from repositories instead of looping through the list. 
		for (Student s : students)
			save(s);
	}

	/**
	 * <b>NOTE - </b>This method must only be used to update personal details of the student. 
	 */
	@Override
	public void update(Student student) throws ServiceException {
		studentRepository.update(student);
	}

	/**
	 * <b>NOTE - </b>This method must only be used to update personal details of the students. 
	 */
	@Override
	public void updateAll(Collection<Student> students) throws ServiceException {
		studentRepository.updateAll(students);
	}

	@Override
	public void addToSubject(Student student, Subject subject) throws ServiceException {
		notNull(student);
		if (!subjectRepository.isStudentInSubject(student.getId(), subject)) {
			subjectRepository.addStudentToSubject(student.getId(), subject);
			studentTotalToPayRepository.updateTotalToPayBy(student.getId(), studentTotalToPayRepository.getCurrentTotalToPay(student.getId())+costCodeRepository.findById(subject.getCostCode()).getAmount());
		} else {
			log.warn("Tried to add student to a subject more than once.");
		}
	}

	@Override
	public void removeFromSubject(Student student, Subject subject) throws ServiceException {
		notNull(student);
		if (subjectRepository.isStudentInSubject(student.getId(), subject)) {
			subjectRepository.removeStudentFromSubject(student.getId(), subject);
			studentTotalToPayRepository.updateTotalToPayBy(student.getId(), studentTotalToPayRepository.getCurrentTotalToPay(student.getId())-costCodeRepository.findById(subject.getCostCode()).getAmount());
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
		double totalTopay = studentTotalToPayRepository.getCurrentTotalToPay(payment.getStud_id());
		double totalPaid = paymentRepository.getTotalPaid(payment.getStud_id());
		if ((totalPaid + payment.getAmount()) <= totalTopay || payment.getAmount() < 0d) {
			paymentRepository.save(payment);
		} else {
			// If scholarship payment then allow payment to be higher then total
			// topay.
			if (isScholorshipPayment)
				paymentRepository.save(payment);
			else
				throw new IllegalArgumentException("You cannot make payment higher than totalToPay.\ntotalToPay "
						+ totalTopay + " : previouslyTotalPaid " + totalPaid + " : new payment: " + payment.getAmount());
		}
		// I don't know how valid this is. updating total to pay here might need
		// revisiting.
		studentTotalToPayRepository.updateTotalToPayBy(payment.getStud_id(), totalTopay - payment.getAmount());
	}

	@Override
	public void awardScholorship(Scholarship scholorship) throws ServiceException {
		notNull(scholorship);
		scholarshipRepository.save(scholorship);
		// TODO create payment id properly. However I think it is valid to have same id as scholarship.
		makePayment(new Payment(scholorship.getId(), scholorship.getTotal_amount(), PaymentType.SCHOLORSHIP, scholorship.getStud_id(), "SCHLR_ID:" + scholorship.getId()), true);
	}

	@Override
	public void amendPayment(Payment payment) throws ServiceException {
		notNull(payment);
		Payment orig = paymentRepository.findById(payment.getId());
		if (!orig.getStud_id().equalsIgnoreCase(payment.getStud_id())) {
			log.warn(I18N.getString("error.update.studid.not.allowed"));
			throw new ServiceException(I18N.getString("error.update.studid.not.allowed"));
		} 
		double totalTopay = studentTotalToPayRepository.getCurrentTotalToPay(payment.getStud_id());
//		Payment oldPayment = paymentRepository.findById(payment.getId());
		double totalPaid = paymentRepository.getTotalPaid(payment.getStud_id());
		if ((totalPaid + payment.getAmount()) <= totalTopay) {
			paymentRepository.update(payment);
		} else {
			throw new IllegalArgumentException(I18N.getString("error.higher.payment.than.required", new Object[] {totalTopay, totalPaid}));
		}
		// TODO amend totalToPay here aswell.
		studentTotalToPayRepository.updateTotalToPayBy(payment.getStud_id(), totalTopay + orig.getAmount());
		totalTopay = studentTotalToPayRepository.getCurrentTotalToPay(payment.getStud_id());
		studentTotalToPayRepository.updateTotalToPayBy(payment.getStud_id(), totalTopay - payment.getAmount());
	}
	
	@Override
	public void issueRefund(Payment payment) throws ServiceException {
		makePayment(payment);
	}
	
	@Override
	public void amendScholorship(Scholarship scholorship) throws ServiceException {
		notNull(scholorship);
		// TODO if amount has changed then need to update payment through here aswell but is it correct to create payment object here?. 
		// Actually thinking about it I could load the payment from repository and then amend the amount from scholorship.  
		scholarshipRepository.update(scholorship);
		Payment p = paymentRepository.findById(scholorship.getId());
		p.setAmount(scholorship.getTotal_amount());
		amendPayment(p);
	}
}
package spring.desai.webconsole.service.impl;

import static org.springframework.util.Assert.notNull;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.desai.common.model.Payment;
import spring.desai.common.model.Scholorship;
import spring.desai.common.model.Student;
import spring.desai.common.model.Subject;
import spring.desai.common.model.enums.PaymentType;
import spring.desai.common.repository.CostCodeRepository;
import spring.desai.common.repository.PaymentRepository;
import spring.desai.common.repository.ScholorshipRepository;
import spring.desai.common.repository.StudentRepository;
import spring.desai.common.repository.StudentTotalToPayRepository;
import spring.desai.common.repository.SubjectRepository;
import spring.desai.common.repository.exception.RepositoryDataAccessException;
import spring.desai.common.service.StudentAdminService;
import spring.desai.common.service.exception.ServiceException;

@Service("studentAdminService")
@Transactional
public class StudentAdminServiceImpl implements StudentAdminService {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private ScholorshipRepository scholorshipRepository;

	@Autowired
	private SubjectRepository subjectRepository;
	
	@Autowired 
	private CostCodeRepository costCodeRepository;

	@Autowired
	private StudentTotalToPayRepository studentTotalToPayRepository;
	
	@Override
	public void save(Student student) throws ServiceException {
		try {
			studentRepository.save(student);
		} catch (RepositoryDataAccessException e) {
			throw new ServiceException("save(student)", e);
		}
	}

	@Override
	public void saveAll(Collection<Student> students) throws ServiceException {
		try {
			studentRepository.saveAll(students);
		} catch (RepositoryDataAccessException e) {
			throw new ServiceException("saveAll(students)", e);
		}
	}

	@Override
	public void update(Student student) throws ServiceException {
		try {
			studentRepository.update(student);
		} catch (RepositoryDataAccessException e) {
			throw new ServiceException("update(student)", e);
		}
	}

	@Override
	public void updateAll(Collection<Student> students) throws ServiceException {
		try {
			studentRepository.updateAll(students);
		} catch (RepositoryDataAccessException e) {
			throw new ServiceException("updateAll(students)", e);
		}
	}

	@Override
	public void addToSubject(Student student, Subject subject) throws ServiceException {
		notNull(student);
		try {
			if (!subjectRepository.isStudentInSubject(student.getId(), subject)) {
				subjectRepository.addStudentToSubject(student.getId(), subject);
				studentTotalToPayRepository.updateTotalToPayBy(student.getId(), costCodeRepository.findById(subject.getCostCode()).getAmount());
			}
		} catch (RepositoryDataAccessException e) {
			throw new ServiceException("addToSubject(student,subject)", e);
		}
	}
	
	@Override
	public void removeFromSubject(Student student, Subject subject) throws ServiceException {
		notNull(student);
		try {
			if (subjectRepository.isStudentInSubject(student.getId(), subject)) {
				subjectRepository.removeStudentFromSubject(student.getId(), subject);
				studentTotalToPayRepository.updateTotalToPayBy(student.getId(), -costCodeRepository.findById(subject.getCostCode()).getAmount());
			}
		} catch (RepositoryDataAccessException e) {
			throw new ServiceException("", e);
		}
	}

	@Override
	public void makePayment(Payment payment) throws ServiceException {
		makePayment(payment, false);
	}
	
	private void makePayment(Payment payment, boolean isScholorshipPayment) throws ServiceException {
		notNull(payment);
		try {
			double totalTopay = studentTotalToPayRepository.getCurrentTotalToPay(payment.getStud_id());
			Collection<Payment> payments = paymentRepository.findbyStudentId(payment.getStud_id());
			double totalPaid = 0;
			for (Payment p : payments) {
				totalPaid += p.getAmount();
			}
			if ((totalPaid + payment.getAmount()) <= totalTopay) {
				paymentRepository.save(payment);
			} else {
				if (isScholorshipPayment) 
					paymentRepository.save(payment);
				else
					throw new IllegalArgumentException("You cannot make payment higher than totalToPay.\ntotalToPay " + totalTopay + " : totalPaid " + totalPaid);
			}
			
		} catch (RepositoryDataAccessException e) {
			throw new ServiceException("makePayment(payment)", e);
		}
	}

	@Override
	public void awardScholorship(Scholorship scholorship) throws ServiceException {
		try {
			scholorshipRepository.save(scholorship);
			// TODO create payment id properly.
			makePayment(new Payment(scholorship.getId(), scholorship.getTotal_amount(), PaymentType.SCHOLORSHIP, scholorship.getStud_id(), "SCHLR_ID:" + scholorship.getId()), true);
		} catch (RepositoryDataAccessException e) {
			throw new ServiceException("awardScholorship(scholorship)", e);
		}
	}

	@Override
	public void amendPayment(Payment payment) throws ServiceException {
		try {
			double totalTopay = studentTotalToPayRepository.getCurrentTotalToPay(payment.getStud_id());
			Collection<Payment> payments = paymentRepository.findbyStudentId(payment.getStud_id());
			double totalPaid = 0;
			for (Payment p : payments) {
				totalPaid += p.getAmount();
			}
			if ((totalPaid + payment.getAmount()) <= totalTopay) {
				paymentRepository.update(payment);
			} else {
				throw new IllegalArgumentException("You cannot update payment with amount higher than totalToPay.\ntotalToPay " + totalTopay + " : totalPaid " + totalPaid);
			}
			
		} catch (RepositoryDataAccessException e) {
			throw new ServiceException("amendPayment(payment)", e);
		}
	}

	@Override
	public void amendScholorship(Scholorship scholorship) throws ServiceException {
		try {
			// TODO if amount has changed then need to update payment through here aswell but is it correct to create payment object here?. 
			scholorshipRepository.update(scholorship);
			amendPayment(new Payment(scholorship.getId(), scholorship.getTotal_amount(), PaymentType.SCHOLORSHIP, scholorship.getStud_id(),  "SCHLR_ID:" + scholorship.getId()));
		} catch (RepositoryDataAccessException e) {
			throw new ServiceException("amendScholorship(scholorship)", e);
		}
	}
}
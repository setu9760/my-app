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
import spring.desai.common.repository.PaymentRepository;
import spring.desai.common.repository.ScholorshipRepository;
import spring.desai.common.repository.StudentRepository;
import spring.desai.common.repository.SubjectRepository;
import spring.desai.common.repository.exception.RepositoryDataAccessException;
import spring.desai.common.service.StudentAdminService;
import spring.desai.common.service.exception.ServiceException;

@Service("studentAdminService")
@Transactional
public class StudentAdminServiceImpl implements StudentAdminService {

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	PaymentRepository paymentRepository;

	@Autowired
	ScholorshipRepository scholorshipRepository;

	@Autowired
	SubjectRepository subjectRepository;

	@Override
	public void save(Student student) throws ServiceException {
		try {
			studentRepository.save(student);
		} catch (RepositoryDataAccessException e) {
			throw new ServiceException("", e);
		}
	}

	@Override
	public void saveAll(Collection<Student> students) throws ServiceException {
		try {
			studentRepository.saveAll(students);
		} catch (RepositoryDataAccessException e) {
			throw new ServiceException("", e);
		}
	}

	@Override
	public void update(Student student) throws ServiceException {
		try {
			studentRepository.update(student);
		} catch (RepositoryDataAccessException e) {
			throw new ServiceException("", e);
		}
	}

	@Override
	public void updateAll(Collection<Student> students) throws ServiceException {
		try {
			studentRepository.updateAll(students);
		} catch (RepositoryDataAccessException e) {
			throw new ServiceException("", e);
		}
	}

	@Override
	public void addToSubject(Student student, Subject subject) throws ServiceException {
		notNull(student);
		try {
			if (!subjectRepository.isStudentInSubject(student.getId(), subject)) {
				subjectRepository.addStudentToSubject(student.getId(), subject);
			}
		} catch (RepositoryDataAccessException e) {
			throw new ServiceException("", e);
		}
	}

	@Override
	public void makePayment(Payment payment) throws ServiceException {
		try {
			paymentRepository.save(payment);
		} catch (RepositoryDataAccessException e) {
			throw new ServiceException("", e);
		}
	}

	@Override
	public void awardScholorship(Scholorship scholorship) throws ServiceException {
		try {
			scholorshipRepository.save(scholorship);
		} catch (RepositoryDataAccessException e) {
			throw new ServiceException("", e);
		}
	}

	@Override
	public void removeFromSubject(Student student, Subject subject) throws ServiceException {
		notNull(student);
		try {
			if (!subjectRepository.isStudentInSubject(student.getId(), subject)) {
				subjectRepository.removeStudentFromSubject(student.getId(), subject);
			}
		} catch (RepositoryDataAccessException e) {
			throw new ServiceException("", e);
		}
	}

	@Override
	public void amendPayment(Payment payment) throws ServiceException {
		try {
			paymentRepository.update(payment);
		} catch (RepositoryDataAccessException e) {
			throw new ServiceException("", e);
		}
	}

	@Override
	public void amendScholorship(Scholorship scholorship) throws ServiceException {
		try {
			scholorshipRepository.update(scholorship);
		} catch (RepositoryDataAccessException e) {
			throw new ServiceException("", e);
		}
	}

}
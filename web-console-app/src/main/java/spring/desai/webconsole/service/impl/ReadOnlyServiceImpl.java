package spring.desai.webconsole.service.impl;

import java.util.Collection;

import org.springframework.stereotype.Service;

import spring.desai.common.model.Payment;
import spring.desai.common.model.Student;
import spring.desai.common.model.Subject;
import spring.desai.common.model.Tutor;
import spring.desai.common.service.BaseService;
import spring.desai.common.service.ReadOnlyService;

@Service(value = "readOnlyService")
public class ReadOnlyServiceImpl extends BaseService implements ReadOnlyService {

	@Override
	public Student getStudentById(String id) {
		Student s = studentRepository.findById(id);
		s.setSubjects(subjectRepository.getSubjectsForStudentId(id));
		s.setPayments(paymentRepository.findbyStudentId(id));
		s.setScholorships(scholorshipRepository.findByStudentId(id));
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
	public Collection<Tutor> getTutorsFromSubjectId(String subjectId) {
		return tutorRepository.getTutorsForSubject(subjectId);
	}

	@Override
	public Collection<Payment> getPaymentsForStudent(String studId) {
		return paymentRepository.findbyStudentId(studId);
	}

	@Override
	public Payment getPaymentById(String id) {
		return paymentRepository.findById(id);
	}

	@Override
	public Collection<Student> getAllStudents() {
		return studentRepository.getAll();
	}

	@Override
	public Collection<Subject> getAllSubjects() {
		return subjectRepository.getAll();
	}

	@Override
	public Collection<Tutor> getAllTutors() {
		return tutorRepository.getAll();
	}
}

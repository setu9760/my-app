package spring.desai.webconsole.service.impl;

import java.util.Collection;

import org.springframework.stereotype.Service;

import spring.desai.common.model.Payment;
import spring.desai.common.model.Student;
import spring.desai.common.model.Subject;
import spring.desai.common.model.Tutor;
import spring.desai.common.service.BaseService;
import spring.desai.common.service.ReadOnlyService;

@Service(value="readOnlyService")
public class ReadOnlyServiceImpl extends BaseService implements ReadOnlyService {

	@Override
	public Collection<Student> getAllStudents() {
		Collection<Student> students = studentRepository.getAll();
		for (Student student : students) {
			student.setSubjects(subjectRepository.getSubjectsForStudentId(student.getId()));
		}
		return students;
	}

	@Override
	public Student getStudentById(String id) {
		Student student = studentRepository.findById(id);
		student.setSubjects(subjectRepository.getSubjectsForStudentId(id));
		return student;
	}

	@Override
	public Collection<Subject> getAllSubjects() {
		return subjectRepository.getAll();
	}

	@Override
	public Subject getSubjectById(String id) {
		return subjectRepository.findById(id);
	}

	@Override
	public Collection<Tutor> getAllTutors() {
		Collection<Tutor> tutors = tutorRepository.getAll();
		for (Tutor tutor : tutors) {
			tutor.setSubject(getSubjectById(tutor.getSubject().getId()));
		}
		return tutors;
	}

	@Override
	public Tutor getTutorById(String id) {
		Tutor tutor = tutorRepository.findById(id);
		tutor.setSubject(getSubjectById(tutor.getSubject().getId()));
		return tutor;
	}

	@Override
	public Collection<Tutor> getTutorsFromSubjectId(String subjectId) {
		return tutorRepository.getTutorsForSubject(subjectId);
	}
	
	@Override
	public Payment getPaymentById(String id) {
		return paymentRepository.findById(id);
	}
	
	@Override
	public Collection<Payment> getPaymentsForStudent(String stud_id) {
		return paymentRepository.findbyStudentId(stud_id);
	}

}

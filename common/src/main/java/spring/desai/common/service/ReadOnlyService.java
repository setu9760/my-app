package spring.desai.common.service;

import java.util.Collection;

import spring.desai.common.model.pojo.Payment;
import spring.desai.common.model.pojo.Student;
import spring.desai.common.model.pojo.Subject;
import spring.desai.common.model.pojo.Tutor;

public interface ReadOnlyService {

	public Collection<Student> getAllStudents();

	public Student getStudentById(String id);

	public Collection<Subject> getAllSubjects();

	public Subject getSubjectById(String id);

	public Collection<Tutor> getAllTutors();

	public Tutor getTutorById(String id);

	public Collection<Tutor> getTutorsFromSubjectId(String subjectId);
	
	public Collection<Payment> getPaymentsForStudent(String stud_id);
	
	public Payment getPaymentById(String id);
	
}

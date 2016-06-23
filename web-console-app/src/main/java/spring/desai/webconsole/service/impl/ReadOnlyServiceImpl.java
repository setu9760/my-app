package spring.desai.webconsole.service.impl;

import java.util.Collection;

import org.springframework.stereotype.Service;

import spring.desai.common.model.pojo.Payment;
import spring.desai.common.model.pojo.Student;
import spring.desai.common.model.pojo.Subject;
import spring.desai.common.model.pojo.Tutor;
import spring.desai.common.service.BaseService;
import spring.desai.common.service.ReadOnlyService;

@Service(value="readOnlyService")
public class ReadOnlyServiceImpl extends BaseService implements ReadOnlyService {

	@Override
	public Collection<Student> getAllStudents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student getStudentById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Subject> getAllSubjects() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Subject getSubjectById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Tutor> getAllTutors() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tutor getTutorById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Tutor> getTutorsFromSubjectId(String subjectId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Payment> getPaymentsForStudent(String stud_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Payment getPaymentById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	

}

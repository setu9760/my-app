package spring.desai.common.model.dto;

import static org.springframework.util.Assert.notNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import spring.desai.common.model.Cost;
import spring.desai.common.model.Payment;
import spring.desai.common.model.Scholorship;
import spring.desai.common.model.Student;
import spring.desai.common.model.Subject;
import spring.desai.common.model.Tutor;

public final class DTOFactory {

	private DTOFactory() {
	}

	private static final DTOFactory instance = new DTOFactory();
	
	public static DTOFactory getInstance(){
		return instance;
	}
	
	public TutorDTO fromTutor(Tutor t) {
		notNull(t);
		// TODO 
		return null;
	}

	public SubjectDTO fromSubject(Subject s) {
		notNull(s);
		// TODO
		return null;
	}
	
	public StudentDTO fromStudent(Student s) {
		notNull(s);
		// TODO
		return null;
	}
	
	public ScholorshipDTO fromScholorship(Scholorship s) {
		notNull(s);
		// TODO
		return null;
	}
	
	public PaymentDTO fromPayment(Payment p) {
		notNull(p);
		// TODO
		return null;
	}
	
	public CostDTO fromCost(Cost c) {
		notNull(c);
		// TODO
		return null;
	}
	
	public Collection<SubjectDTO> fromSubjects(Collection<Subject> subjects){
		notNull(subjects);
		Set<SubjectDTO> subs = new HashSet<>(subjects.size());
		for (Subject subject : subjects) {
			subs.add(fromSubject(subject));
		}
		return subs;
	}
	
	public Collection<PaymentDTO> fromPayments(Collection<Payment> payments){
		notNull(payments);
		Set<PaymentDTO> pays = new HashSet<>(payments.size());
		for (Payment p : payments) {
			pays.add(fromPayment(p));
		}
		return pays;
	}
	
	public Collection<ScholorshipDTO> fromScholorships(Collection<Scholorship> scholorships) {
		notNull(scholorships);
		Set<ScholorshipDTO> ss = new HashSet<>(scholorships.size());
		for (Scholorship s: scholorships) { 
			ss.add(fromScholorship(s));
		}
		return ss;
	}
	
	public Collection<StudentDTO> fromstudents(Collection<Student> students){
		notNull(students);
		Set<StudentDTO> ss = new HashSet<>(students.size());
		for (Student s : students){
			ss.add(fromStudent(s));
		}
		return ss;
	}
	
	public Collection<TutorDTO> fromTutors(Collection<Tutor> tutors) {
		notNull(tutors);
		Set<TutorDTO> ts = new HashSet<>(tutors.size());
		for(Tutor t : tutors){
			ts.add(fromTutor(t));
		}
		return ts;
	}
}
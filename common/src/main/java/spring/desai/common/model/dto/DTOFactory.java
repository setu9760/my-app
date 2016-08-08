package spring.desai.common.model.dto;

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
	
	/****************************************************
	*
	*	Object to DTO methods
	*
	****************************************************/
	public TutorDTO fromTutor(Tutor t) {
		return t != null ? new TutorDTO(t.getId(), t.getF_name(), t.getL_name(), t.getAddress(), t.getSubj_id(), t.isFulltime()) : null;
	}

	public SubjectDTO fromSubject(Subject s) {
		return s != null ?  new SubjectDTO(s.getId(), s.getName(), s.getCostCode(), s.isMandatory()) : null;
	}
	
	public StudentDTO fromStudent(Student s) {
		return s != null ? new StudentDTO(s.getId(), s.getF_name(), s.getL_name(), s.getAddress(), s.getAge(), fromSubjects(s.getSubjects()), fromPayments(s.getPayments()), fromScholorships(s.getScholorships())): null;
	}
	
	public ScholorshipDTO fromScholorship(Scholorship s) {
		return s != null ?new ScholorshipDTO(s.getId(), s.getExternal_ref(), s.getTypeString(), s.getTotal_amount(), s.getPaid_amount(), s.isFullyPaid(), s.isPostPay(), s.getStud_id(), s.getAdditional_comments()): null;
	}
	
	public PaymentDTO fromPayment(Payment p) {
		return p != null ? new PaymentDTO(p.getId(), p.getAmount(), p.getPaymentTypeString(), p.getStud_id(), p.getPaymentDateTime().toDate(), p.getComments()) : null;
	}
	
	public CostDTO fromCost(Cost c) {
		return c != null ? new CostDTO(c.getCostCode(), c.getAmount()) : null;
	}
	
	public Collection<SubjectDTO> fromSubjects(Collection<Subject> subjects){
		Set<SubjectDTO> subs = new HashSet<>(subjects.size());
		for (Subject subject : subjects) {
			subs.add(fromSubject(subject));
		}
		return subs;
	}
	
	public Collection<PaymentDTO> fromPayments(Collection<Payment> payments){
		Set<PaymentDTO> pays = new HashSet<>(payments.size());
		for (Payment p : payments) {
			pays.add(fromPayment(p));
		}
		return pays;
	}
	
	public Collection<ScholorshipDTO> fromScholorships(Collection<Scholorship> scholorships) {
		Set<ScholorshipDTO> ss = new HashSet<>(scholorships.size());
		for (Scholorship s: scholorships) { 
			ss.add(fromScholorship(s));
		}
		return ss;
	}
	
	public Collection<StudentDTO> fromstudents(Collection<Student> students){
		Set<StudentDTO> ss = new HashSet<>(students.size());
		for (Student s : students){
			ss.add(fromStudent(s));
		}
		return ss;
	}
	
	public Collection<TutorDTO> fromTutors(Collection<Tutor> tutors) {
		Set<TutorDTO> ts = new HashSet<>(tutors.size());
		for(Tutor t : tutors){
			ts.add(fromTutor(t));
		}
		return ts;
	}
	
	/****************************************************
	*
	*	DTO to Object methods
	*
	****************************************************/
	public Student fromStudentDTO(StudentDTO dto){
		// TODO		
		return null;
	}
	
	public Tutor fromTutorDTO(TutorDTO dto){
		// TODO
		return null;
	}
	
	public Subject fromSubjectDTO(SubjectDTO dto){
		// TODO
		return null;
	}
	
	public Cost fromCostDTO(CostDTO dto){
		// TODO
		return null;
	}
	
	public Scholorship fromScholorshipDTO(ScholorshipDTO dto){
		// TODO
		return null;
	}
	
	public Payment fromPaymentDTO(PaymentDTO dto) {
		// TODO
		return null;
	}
	
	public Collection<Student> fromStudentDTOs(Collection<StudentDTO> dtos){
		for (StudentDTO s : dtos) {
			
		}
		return null;
	}
	
	public Collection<Tutor> fromTutorDTOs(Collection<TutorDTO> dtos){
		for (TutorDTO t : dtos) {
			
		}
		return null;
	}
	
	public Collection<Subject> fromSubjectDTOs(Collection<SubjectDTO> dtos){
		for (SubjectDTO s : dtos) {
			
		}
		return null;
	}
	
	public Collection<Cost> fromCostDTOs(Collection<CostDTO> dtos){
		for (CostDTO c : dtos) {
			
		}
		return null;
	}
	
	public Collection<Scholorship> fromScholorshipDTOs(Collection<ScholorshipDTO> dtos){
		for (ScholorshipDTO s : dtos) {
			
		}
		return null;
	}
	
	public Collection<Payment> fromPaymentDTOs(Collection<PaymentDTO> dtos) {
		// TODO
		return null;
	}
}

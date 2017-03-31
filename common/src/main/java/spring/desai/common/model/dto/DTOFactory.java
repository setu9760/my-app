package spring.desai.common.model.dto;

import java.util.Collection;
import java.util.ArrayList;
import java.util.List;

import spring.desai.common.model.Cost;
import spring.desai.common.model.Payment;
import spring.desai.common.model.Scholarship;
import spring.desai.common.model.Student;
import spring.desai.common.model.Subject;
import spring.desai.common.model.Tutor;
import spring.desai.common.model.User;
import spring.desai.common.model.enums.PaymentType;
import spring.desai.common.model.enums.ScholorshipType;

public final class DTOFactory {

	private static final ThreadLocal<DTOFactory> instance = new ThreadLocal<DTOFactory>(){
		protected DTOFactory initialValue() {
			return new DTOFactory();			
		};
	};
	
	public static DTOFactory getInstance(){
		return instance.get();
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
		return s != null ? new StudentDTO(s.getId(), s.getF_name(), s.getL_name(), s.getAge(), s.getAddress(), fromSubjects(s.getSubjects()), fromPayments(s.getPayments()), fromScholorships(s.getScholarships())): null;
	}
	
	public ScholorshipDTO fromScholorship(Scholarship s) {
		return s != null ?new ScholorshipDTO(s.getId(), s.getExternal_ref(), s.getTypeString(), s.getTotal_amount(), s.getPaid_amount(), s.isFullyPaid(), s.isPostPay(), s.getStud_id(), s.getAdditional_comments()): null;
	}
	
	public PaymentDTO fromPayment(Payment p) {
		return p != null ? new PaymentDTO(p.getId(), p.getAmount(), p.getPaymentTypeString(), p.getStud_id(), p.getPaymentDateTime().toDate(), p.getComments()) : null;
	}
	
	public CostDTO fromCost(Cost c) {
		return c != null ? new CostDTO(c.getCostCode(), c.getAmount()) : null;
	}
	
	public PersonDTO fromUserToPersonDTOs(User u){
		return u != null ? new PersonDTO(u.getId(), u.getF_name(), u.getL_name(), u.getAddress()) : null;
	}
	
	public Collection<SubjectDTO> fromSubjects(Collection<Subject> subjects){
		List<SubjectDTO> subs = new ArrayList<>(subjects.size());
		for (Subject subject : subjects) {
			subs.add(fromSubject(subject));
		}
		return subs;
	}
	
	public Collection<PaymentDTO> fromPayments(Collection<Payment> payments){
		List<PaymentDTO> pays = new ArrayList<>(payments.size());
		for (Payment p : payments) {
			pays.add(fromPayment(p));
		}
		return pays;
	}
	
	public Collection<ScholorshipDTO> fromScholorships(Collection<Scholarship> scholorships) {
		List<ScholorshipDTO> ss = new ArrayList<>(scholorships.size());
		for (Scholarship s: scholorships) { 
			ss.add(fromScholorship(s));
		}
		return ss;
	}
	
	public Collection<StudentDTO> fromstudents(Collection<Student> students){
		List<StudentDTO> ss = new ArrayList<>(students.size());
		for (Student s : students){
			ss.add(fromStudent(s));
		}
		return ss;
	}
	
	public Collection<TutorDTO> fromTutors(Collection<Tutor> tutors) {
		List<TutorDTO> ts = new ArrayList<>(tutors.size());
		for(Tutor t : tutors){
			ts.add(fromTutor(t));
		}
		return ts;
	}
	
	public Collection<PersonDTO> fromUsersToPersonDTOs(Collection<User> users) {
		List<PersonDTO> ps = new ArrayList<>(users.size());
		for (User u : users) {
			ps.add(fromUserToPersonDTOs(u));
		}
		return ps;
	}
	
	/****************************************************
	*
	*	DTO to Object methods
	*
	****************************************************/
	public Student fromStudentDTO(StudentDTO dto){
		Student s = new Student(dto.getId(), dto.getF_name(), dto.getL_name(), dto.getAge(), dto.getAddress());
		s.setSubjects(fromSubjectDTOs(dto.getSubjects()));
		s.setScholarships(fromScholorshipDTOs(dto.getScholorships()));
		s.setPayments(fromPaymentDTOs(dto.getPayments()));
		return s;
	}
	
	public Tutor fromTutorDTO(TutorDTO dto){
		return new Tutor(dto.getId(), dto.getF_name(), dto.getL_name(), dto.getSubject(), dto.getAddress(), dto.isFulltime());
	}
	
	public Subject fromSubjectDTO(SubjectDTO dto){
		return new Subject(dto.getId(), dto.getName(), dto.getCostCode(), dto.isMandatory());
	}
	
	public Cost fromCostDTO(CostDTO dto){
		return new Cost(dto.getCostCode(), dto.getAmount());
	}
	
	public Scholarship fromScholorshipDTO(ScholorshipDTO dto){
		return new Scholarship(dto.getId(), dto.getExternalRef(), ScholorshipType.valueOf(dto.getType()), dto.getTotalAmount(), dto.getPaidAmount(), dto.getIsFullyPaid(), dto.getIsPostPay(), dto.getAdditionalComments(), dto.getStudId());
	}
	
	public Payment fromPaymentDTO(PaymentDTO dto) {
		return new Payment(dto.getId(), dto.getAmount(), PaymentType.valueOf(dto.getPaymentType()), dto.getStudentId(), dto.getComments());
	}
	
	public User fromPersonDTOToUser(PersonDTO p) {
		return p != null ? new User(p.getId(),p.getF_name(), p.getL_name(), p.getAddress()) : null;
	}
	
	public Collection<Student> fromStudentDTOs(Collection<StudentDTO> dtos){
		if(dtos == null)
			return new ArrayList<>();
		List<Student> studs = new ArrayList<>(dtos.size());
		for (StudentDTO s : dtos) {
			studs.add(fromStudentDTO(s));
		}
		return studs;
	}
	
	public Collection<Tutor> fromTutorDTOs(Collection<TutorDTO> dtos){
		if(dtos == null)
			return new ArrayList<>();
		List<Tutor> tutors= new ArrayList<>(dtos.size());
		for (TutorDTO t : dtos) {
			tutors.add(fromTutorDTO(t));
		}
		return tutors;
	}
	
	public Collection<Subject> fromSubjectDTOs(Collection<SubjectDTO> dtos){
		if(dtos == null)
			return new ArrayList<>();
		List<Subject> subs = new ArrayList<>(dtos.size());
		for (SubjectDTO s : dtos) {
			subs.add(fromSubjectDTO(s));
		}
		return subs;
	}
	
	public Collection<Cost> fromCostDTOs(Collection<CostDTO> dtos){
		if(dtos == null)
			return new ArrayList<>();
		List<Cost> costs = new ArrayList<>(dtos.size());
		for (CostDTO c : dtos) {
			costs.add(fromCostDTO(c));
		}
		return costs;
	}
	
	public Collection<Scholarship> fromScholorshipDTOs(Collection<ScholorshipDTO> dtos){
		if(dtos == null)
			return new ArrayList<>();
		List<Scholarship> schols = new ArrayList<>(dtos.size());
		for (ScholorshipDTO s : dtos) {
			schols.add(fromScholorshipDTO(s));
		}
		return schols;
	}
	
	public Collection<Payment> fromPaymentDTOs(Collection<PaymentDTO> dtos) {
		if(dtos == null)
			return new ArrayList<>();
		List<Payment> payments = new ArrayList<>(dtos.size());
		for (PaymentDTO p : dtos) {
			payments.add(fromPaymentDTO(p));
		}
		return payments;
	}
}
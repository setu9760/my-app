package spring.desai.common.model.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import spring.desai.common.model.Cost;
import spring.desai.common.model.Payment;
import spring.desai.common.model.Persistable;
import spring.desai.common.model.Role;
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
	public DTO fromPersistable(Persistable p) {
		if (p instanceof Student)
			return fromStudent((Student) p);
		else if (p instanceof Scholarship)
			return fromScholorship((Scholarship) p);
		else if (p instanceof Payment)
			return fromPayment((Payment) p);
		else if (p instanceof Subject)
			return fromSubject((Subject) p);
		else if (p instanceof Tutor)
			return fromTutor((Tutor) p);
		else if (p instanceof Cost)
			return fromCost((Cost) p);
		else if (p instanceof Role)
			return fromRole((Role)p);
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public Collection<? extends DTO> fromPersistables(String entityType, Collection<? extends Persistable> coll) {
		Collection<? extends DTO> c = new ArrayList<>(coll.size());
		switch (entityType.toLowerCase()) {
		case "student":
			c = fromstudents((Collection<Student>) coll);
			break;
		case "subject":
			c = fromSubjects((Collection<Subject>) coll);
			break;
		case "payment":
			c = fromPayments((Collection<Payment>) coll);
			break;
		case "scholorship":
			c = fromScholorships((Collection<Scholarship>) coll);
			break;
		case "tutor":
			c = fromTutors((Collection<Tutor>) coll);
			break;
		case "role":
			c = fromRoles((Collection<Role>) coll);
			break;
		case "cost":
			c = fromCosts((Collection<Cost>) coll);
			break;
		}
		return c;
	}
	
	public TutorDTO fromTutor(Tutor t) {
		return t != null ? new TutorDTO(t.getId(), t.getFirstname(), t.getLastname(), t.getAddress(), t.getSubj_id(), t.isFulltime()) : null;
	}

	public SubjectDTO fromSubject(Subject s) {
		return s != null ?  new SubjectDTO(s.getId(), s.getName(), s.getCostCode(), s.isMandatory()) : null;
	}
	
	public StudentDTO fromStudent(Student s) {
		return s != null ? new StudentDTO(s.getId(), s.getFirstname(), s.getLastname(), s.getAge(), s.getAddress(), fromSubjects(s.getSubjects()), fromPayments(s.getPayments()), fromScholorships(s.getScholarships())): null;
	}
	
	public ScholarshipDTO fromScholorship(Scholarship s) {
		return s != null ?new ScholarshipDTO(s.getId(), s.getExternalRef(), s.getTypeString(), s.getTotalAmount(), s.getPaidAmount(), s.isFullyPaid(), s.isPostPay(), s.getStudentId(), s.getAdditionalComments()): null;
	}
	
	public PaymentDTO fromPayment(Payment p) {
		return p != null ? new PaymentDTO(p.getId(), p.getAmount(), p.getPaymentTypeString(), p.getStudentId(), p.getPaymentDateTime().toDate(), p.getComments()) : null;
	}
	
	public CostDTO fromCost(Cost c) {
		return c != null ? new CostDTO(c.getCostCode(), c.getAmount()) : null;
	}
	
	public RoleDTO fromRole(Role r) {
		return r != null ? new RoleDTO(r.getId(), r.getRoleFull(), r.getDescription()) : null;
	}
	
	public PersonDTO fromUserToPersonDTOs(User u){
		return u != null ? new PersonDTO(u.getId(), u.getFirstname(), u.getLastname(), u.getAddress()) : null;
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
	
	public Collection<ScholarshipDTO> fromScholorships(Collection<Scholarship> scholorships) {
		List<ScholarshipDTO> ss = new ArrayList<>(scholorships.size());
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
	
	public Collection<RoleDTO> fromRoles(Collection<Role> roles) {
		Collection<RoleDTO> rr = new ArrayList<>(roles.size());
		for (Role role : roles) {
			rr.add(fromRole(role));
		}
		return rr;
	}
	
	public Collection<CostDTO> fromCosts(Collection<Cost> costs) {
		Collection<CostDTO> cc = new ArrayList<>(costs.size());
		for (Cost cost : costs) {
			cc.add(fromCost(cost));
		}
		return cc;
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
	public Persistable fromDTO(DTO dto) {
		if (dto instanceof StudentDTO) 
			return fromStudentDTO((StudentDTO) dto);
		else if (dto instanceof ScholarshipDTO)
			return fromScholorshipDTO((ScholarshipDTO) dto);
		else if (dto instanceof PaymentDTO)
			return fromPaymentDTO((PaymentDTO) dto);
		else if (dto instanceof SubjectDTO)
			return fromSubjectDTO((SubjectDTO) dto);
		else if (dto instanceof TutorDTO)
			return fromTutorDTO((TutorDTO) dto);
		else if (dto instanceof CostDTO)
			return fromCostDTO((CostDTO) dto);
		else if (dto instanceof RoleDTO)
			return fromRoleDTO((RoleDTO) dto);
		return null;
	}
	
	public Student fromStudentDTO(StudentDTO dto){
		Student s = new Student(dto.getId(), dto.getFirstname(), dto.getLastname(), dto.getAge(), dto.getAddress());
		s.setSubjects(fromSubjectDTOs(dto.getSubjects()));
		s.setScholarships(fromScholorshipDTOs(dto.getScholarships()));
		s.setPayments(fromPaymentDTOs(dto.getPayments()));
		return s;
	}
	
	public Tutor fromTutorDTO(TutorDTO dto){
		return new Tutor(dto.getId(), dto.getFirstname(), dto.getLastname(), dto.getSubject(), dto.getAddress(), dto.isFulltime());
	}
	
	public Subject fromSubjectDTO(SubjectDTO dto){
		return new Subject(dto.getId(), dto.getName(), dto.getCostCode(), dto.isMandatory());
	}
	
	public Cost fromCostDTO(CostDTO dto){
		return new Cost(dto.getCostCode(), dto.getAmount());
	}
	
	public Role fromRoleDTO(RoleDTO dto) {
		return new Role(dto.getId(), dto.getRoleFull(), dto.getDescription());
	}
	
	public Scholarship fromScholorshipDTO(ScholarshipDTO dto){
		return new Scholarship(dto.getId(), dto.getExternalRef(), ScholorshipType.valueOf(dto.getType()), dto.getTotalAmount(), dto.getPaidAmount(), dto.getIsFullyPaid(), dto.getIsPostPay(), dto.getAdditionalComments(), dto.getStudentId());
	}
	
	public Payment fromPaymentDTO(PaymentDTO dto) {
		return new Payment(dto.getId(), dto.getAmount(), PaymentType.valueOf(dto.getPaymentType()), dto.getStudentId(), dto.getComments());
	}
	
	public User fromPersonDTOToUser(PersonDTO p) {
		return p != null ? new User(p.getId(),p.getFirstname(), p.getLastname(), p.getAddress()) : null;
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
	
	public Collection<Scholarship> fromScholorshipDTOs(Collection<ScholarshipDTO> dtos){
		if(dtos == null)
			return new ArrayList<>();
		List<Scholarship> schols = new ArrayList<>(dtos.size());
		for (ScholarshipDTO s : dtos) {
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
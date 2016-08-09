package spring.desai.common.model.dto;

import java.util.Collection;

public class StudentDTO extends PersonDTO {
	private int age;
	private Collection<SubjectDTO> subjects;
	private Collection<PaymentDTO> payments;
	private Collection<ScholorshipDTO> scholorships;
	
	public StudentDTO() {
		super();
	}
	
	public StudentDTO(String id, String f_name, String l_name, int age, String address){
		super(id, f_name, l_name, address);
		this.age = age;
	}
	
	public StudentDTO(String id, String f_name, String l_name, int age, String address, Collection<SubjectDTO> subjects, Collection<PaymentDTO> payments, Collection<ScholorshipDTO> scholorships) {
		super(id, f_name, l_name, address);
		this.age = age;
		this.subjects = subjects;
		this.payments = payments;
		this.scholorships = scholorships;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Collection<SubjectDTO> getSubjects() {
		return subjects;
	}

	public void setSubject(Collection<SubjectDTO> subjects) {
		this.subjects = subjects;
	}
	
	public Collection<PaymentDTO> getPayments() {
		return payments;
	}
	
	public Collection<ScholorshipDTO> getScholorships() {
		return scholorships;
	}
	
}

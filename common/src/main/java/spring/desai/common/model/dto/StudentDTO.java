package spring.desai.common.model.dto;

import java.util.Collection;

import org.hibernate.validator.constraints.Range;

public class StudentDTO extends PersonDTO {
	
	@Range(min=18, max=100)
	private int age;
	private Collection<SubjectDTO> subjects;
	private Collection<PaymentDTO> payments;
	private Collection<ScholarshipDTO> scholarships;
	
	public StudentDTO() {
		super();
	}
	
	public StudentDTO(String id, String f_name, String l_name, int age, String address){
		super(id, f_name, l_name, address);
		this.age = age;
	}
	
	public StudentDTO(String id, String f_name, String l_name, int age, String address, Collection<SubjectDTO> subjects, Collection<PaymentDTO> payments, Collection<ScholarshipDTO> scholarships) {
		super(id, f_name, l_name, address);
		this.age = age;
		this.subjects = subjects;
		this.payments = payments;
		this.scholarships = scholarships;
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
	
	public Collection<ScholarshipDTO> getScholarships() {
		return scholarships;
	}
	
}

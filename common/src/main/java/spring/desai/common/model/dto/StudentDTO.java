package spring.desai.common.model.dto;

import java.util.Collection;

public class StudentDTO extends PersonDTO {
	int age;
	Collection<SubjectDTO> subjects;
	Collection<PaymentDTO> payments;
	Collection<ScholorshipDTO> scholorships;
	
	public StudentDTO(String id, String f_name, String l_name, String address, int age, Collection<SubjectDTO> subjects, Collection<PaymentDTO> payments, Collection<ScholorshipDTO> scholorships) {
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

package spring.desai.common.model.dto;

import java.util.Set;

public class StudentDTO extends PersonDTO{
	int age;
	Set<SubjectDTO> subjects;
	
	public StudentDTO(String id, String f_name, String l_name, String address, int age, Set<SubjectDTO> subjects) {
		super(id, f_name, l_name, address);
		this.age = age;
		this.subjects = subjects;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Set<SubjectDTO> getSubjects() {
		return subjects;
	}

	public void setSubject(Set<SubjectDTO> subjects) {
		this.subjects = subjects;
	}
	
	
}

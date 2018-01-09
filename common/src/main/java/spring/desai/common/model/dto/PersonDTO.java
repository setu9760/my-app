package spring.desai.common.model.dto;

import org.hibernate.validator.constraints.Length;

import spring.desai.common.annotations.NotEmptyAndMinMaxSizeID;

public class PersonDTO implements DTO {
	
	@NotEmptyAndMinMaxSizeID
	private String id;
	@Length(min=3, max=45, message="firstname needs to be between 3 and 45 characters")
	private String firstname;
	@Length(min=3, max=45, message="lastname needs to be between 3 and 45 characters")
	private String lastname;
	@Length(min=3, max=100, message="address needs to be between 3 and 100 characters")
	private String address;

	public PersonDTO() {
		super();
	}
	
	public PersonDTO(String id, String firstname, String lastname, String address) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.address = address;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String f_name) {
		this.firstname = f_name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String l_name) {
		this.lastname = l_name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
}

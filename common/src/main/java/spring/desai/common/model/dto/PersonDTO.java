package spring.desai.common.model.dto;

import org.hibernate.validator.constraints.NotEmpty;

public class PersonDTO implements DTO {
	
	@NotEmpty(message="Person id cannot be null while persisting")
	private String id;
	private String firstname;
	private String lastname;
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

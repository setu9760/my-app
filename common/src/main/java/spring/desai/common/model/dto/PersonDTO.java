package spring.desai.common.model.dto;

public abstract class PersonDTO {
	String id;
	String f_name;
	String l_name;
	String address;

	public PersonDTO() {
		super();
	}
	
	public PersonDTO(String id, String f_name, String l_name, String address) {
		super();
		this.id = id;
		this.f_name = f_name;
		this.l_name = l_name;
		this.address = address;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getF_name() {
		return f_name;
	}

	public void setF_name(String f_name) {
		this.f_name = f_name;
	}

	public String getL_name() {
		return l_name;
	}

	public void setL_name(String l_name) {
		this.l_name = l_name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
}

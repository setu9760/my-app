package spring.desai.common.model;

import static spring.desai.common.utils.DataBaseConstants.ADDRESS;
import static spring.desai.common.utils.DataBaseConstants.F_NAME;
import static spring.desai.common.utils.DataBaseConstants.ID;
import static spring.desai.common.utils.DataBaseConstants.L_NAME;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Person implements Persistable {

	private static final long serialVersionUID = 6253063559299039389L;
	
	@Id
	@Column(name = ID, length = 36, nullable = false)
	protected String id;
	
	@Column(name = F_NAME, length = 36, nullable = false)
	protected String firstname;
	
	@Column(name = L_NAME, length = 36, nullable = false)
	protected String lastname;
	
	@Column(name = ADDRESS, length = 255)
	protected String address;
	
	@Column(name = "is_active")
	protected int isActive = 1;

	public Person() {
	}
	
	protected Person(String id, String firstname, String lastname, String address) {
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.address = address;
	}

	public String getId() {
		return id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getFullName() {
		return firstname + " " + lastname;
	}
	
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	
	public int getIsActive() {
		return isActive;
	}

	public abstract String toString();

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Person))
			return false;
		Person other = (Person) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastname == null) {
			if (other.lastname != null)
				return false;
		} else if (!lastname.equals(other.lastname))
			return false;
		return true;
	}

}

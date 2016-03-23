package spring.desai.common.model.pojo;

public abstract class Person implements Persistable {

	private static final long serialVersionUID = 6253063559299039389L;

	protected String id;
	protected String f_name;
	protected String l_name;
	protected String address;

	public Person(String f_name, String l_name, String address) {
		this("", f_name, l_name, address);
	}

	private Person(String id, String f_name, String l_name, String address) {
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

	public String getFullName() {
		return f_name + " " + l_name;
	}

	public abstract String toString();

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((f_name == null) ? 0 : f_name.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((l_name == null) ? 0 : l_name.hashCode());
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
		if (f_name == null) {
			if (other.f_name != null)
				return false;
		} else if (!f_name.equals(other.f_name))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (l_name == null) {
			if (other.l_name != null)
				return false;
		} else if (!l_name.equals(other.l_name))
			return false;
		return true;
	}

}

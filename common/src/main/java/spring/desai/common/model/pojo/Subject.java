package spring.desai.common.model.pojo;

import spring.desai.common.model.enums.CostCode;

public class Subject implements Persistable {

	private static final long serialVersionUID = -4367164667167006540L;
	private String id;
	private String name;
	private CostCode cost_code;
	private boolean isMandatory;

	public Subject(String name, CostCode costCode) {
		this(null, name, costCode, true);
	}

	public Subject(String name, CostCode costCode, boolean isMandatory) {
		this(null, name, costCode, isMandatory);
	}
	
	public Subject(String id, String name, CostCode costCode, boolean isMandatory) {
		this.id = id;
		this.name = name;
		this.cost_code = costCode;
		this.isMandatory = isMandatory;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CostCode getCost_code() {
		return cost_code;
	}

	public void setCost_code(CostCode cost_code) {
		this.cost_code = cost_code;
	}

	public boolean isMandatory() {
		return isMandatory;
	}

	public void setMandatory(boolean isMandatory) {
		this.isMandatory = isMandatory;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cost_code == null) ? 0 : cost_code.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (isMandatory ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Subject))
			return false;
		Subject other = (Subject) obj;
		if (cost_code != other.cost_code)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isMandatory != other.isMandatory)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Subject [id= ");
		builder.append(id);
		builder.append(", name= ");
		builder.append(name);
		builder.append(", cost_code= ");
		builder.append(cost_code);
		builder.append(", isMandatory= ");
		builder.append(isMandatory);
		builder.append("]");
		return builder.toString();
	}
}

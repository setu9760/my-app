package spring.desai.common.model.pojo;

import static spring.desai.common.utils.DataBaseConstants.COST_CODE;
import static spring.desai.common.utils.DataBaseConstants.ID;
import static spring.desai.common.utils.DataBaseConstants.IS_MANDATORY;
import static spring.desai.common.utils.DataBaseConstants.NAME;
import static spring.desai.common.utils.DataBaseConstants.SUBJECT_TABLE_NAME;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = SUBJECT_TABLE_NAME)
public class Subject implements Persistable {

	private static final long serialVersionUID = -4367164667167006540L;
	
	@Id
	@Column(name = ID, nullable = false)
	private String id;
	
	@Column(name = NAME)
	private String name;
	
//	@ManyToOne(targetEntity = Cost.class)
//	@JoinColumn(name = COST_CODE)
	@Column(name = COST_CODE)
	private String costCode;
	
	@Column(name = IS_MANDATORY)
	private boolean isMandatory;

	public Subject() {
	}
	
	public Subject(String name, String costCode) {
		this(null, name, costCode, true);
	}

	public Subject(String name, String costCode, boolean isMandatory) {
		this(null, name, costCode, isMandatory);
	}
	
	public Subject(String id, String name, String costCode, boolean isMandatory) {
		this.id = id;
		this.name = name;
		this.costCode = costCode;
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

	public String getCostCode() {
		return costCode;
	}
	
	public void setCostCode(String costCode) {
		this.costCode = costCode;
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
		result = prime * result + ((costCode == null) ? 0 : costCode.hashCode());
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
		if (costCode != other.costCode)
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
		builder.append(", costCode= ");
		builder.append(costCode);
		builder.append(", isMandatory= ");
		builder.append(isMandatory);
		builder.append("]");
		return builder.toString();
	}
}

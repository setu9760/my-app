package spring.desai.common.model;

import static spring.desai.common.utils.DataBaseConstants.IS_FULLTIME;
import static spring.desai.common.utils.DataBaseConstants.SUBJ_ID;
import static spring.desai.common.utils.DataBaseConstants.TUTOR_TABLE_NAME;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = TUTOR_TABLE_NAME)
public class Tutor extends Person {

	private static final long serialVersionUID = 7912941565632566952L;
	
//	@ManyToOne(targetEntity = Subject.class)
//	@JoinColumn(name = SUBJ_ID, nullable = false, referencedColumnName = ID)
	@Column(name = SUBJ_ID)
	private String subj_id;
	
	@Column(name = IS_FULLTIME)
	private boolean isFulltime;
	
	public Tutor() {
		this(null, null, null, null, null, false);
	}
	
	public Tutor(String f_name, String l_name, String subj_id) {
		this(f_name, l_name, subj_id, "", true);
	}

	public Tutor(String f_name, String l_name, String subj_id, String address, boolean isFulltime) {
		this(null, f_name, l_name, subj_id, address, isFulltime);
	}
	
	public Tutor(String id, String f_name, String l_name, String subj_id, String address, boolean isFulltime) {
		super(id, f_name, l_name, address);
		this.subj_id = subj_id;
		this.isFulltime = isFulltime;
	}

	public String getSubj_id() {
		return subj_id;
	}
	
	public void setSubj_id(String subj_id) {
		this.subj_id = subj_id;
	}

	public boolean isFulltime() {
		return isFulltime;
	}

	public void setFulltime(boolean isFulltime) {
		this.isFulltime = isFulltime;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Tutor [id=");
		builder.append(id);
		builder.append(", f_name=");
		builder.append(f_name);
		builder.append(", l_name=");
		builder.append(l_name);
		builder.append(", address=");
		builder.append(address);
		builder.append(", subj_id=");
		builder.append(subj_id);
		builder.append(", isFulltime=");
		builder.append(isFulltime);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (isFulltime ? 1231 : 1237);
		result = prime * result + ((subj_id == null) ? 0 : subj_id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Tutor))
			return false;
		Tutor other = (Tutor) obj;
		if (isFulltime != other.isFulltime)
			return false;
		if (subj_id == null) {
			if (other.subj_id != null)
				return false;
		} else if (!subj_id.equals(other.subj_id))
			return false;
		return true;
	}


	
}

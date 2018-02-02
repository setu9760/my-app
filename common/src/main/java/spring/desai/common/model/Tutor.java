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
	private String subjectId;
	
	@Column(name = IS_FULLTIME)
	private boolean isFulltime;
	
	public Tutor(String firstname, String lastname, String subj_id) {
		this(firstname, lastname, subj_id, "", true);
	}

	public Tutor(String firstname, String lastname, String subj_id, String address, boolean isFulltime) {
		this(null, firstname, lastname, subj_id, address, isFulltime);
	}
	
	public Tutor(String id, String firstname, String lastname, String subj_id, String address, boolean isFulltime) {
		super(id, firstname, lastname, address);
		this.subjectId = subj_id;
		this.isFulltime = isFulltime;
	}

	public String getSubj_id() {
		return subjectId;
	}
	
	public void setSubj_id(String subj_id) {
		this.subjectId = subj_id;
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
		builder.append(", firstname=");
		builder.append(firstname);
		builder.append(", lastname=");
		builder.append(lastname);
		builder.append(", address=");
		builder.append(address);
		builder.append(", subjectId=");
		builder.append(subjectId);
		builder.append(", isFulltime=");
		builder.append(isFulltime);
		builder.append(", isActive=");
		builder.append(isActive);
		builder.append(']');
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (isFulltime ? 1231 : 1237);
		result = prime * result + ((subjectId == null) ? 0 : subjectId.hashCode());
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
		if (subjectId == null) {
			if (other.subjectId != null)
				return false;
		} else if (!subjectId.equals(other.subjectId))
			return false;
		return true;
	}


	
}

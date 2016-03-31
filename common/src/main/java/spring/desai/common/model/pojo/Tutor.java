package spring.desai.common.model.pojo;

public class Tutor extends Person {

	private static final long serialVersionUID = 7912941565632566952L;
	private String subj_id;
	private boolean isFulltime;

	public Tutor() {
		this("", "", null);
	}

	public Tutor(String f_name, String l_name, String subj_id) {
		this(f_name, l_name, subj_id, "", true);
	}

	public Tutor(String f_name, String l_name, String subj_id, String address, boolean isFulltime) {
		super(f_name, l_name, address);
		this.subj_id = subj_id;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Tutor [subj_id=");
		builder.append(subj_id);
		builder.append(", isFulltime=");
		builder.append(isFulltime);
		builder.append(", id=");
		builder.append(id);
		builder.append(", f_name=");
		builder.append(f_name);
		builder.append(", l_name=");
		builder.append(l_name);
		builder.append(", address=");
		builder.append(address);
		builder.append("]");
		return builder.toString();
	}
}

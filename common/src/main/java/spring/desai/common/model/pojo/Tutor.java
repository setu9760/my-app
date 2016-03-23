package spring.desai.common.model.pojo;

public class Tutor extends Person {

	private static final long serialVersionUID = 7912941565632566952L;
	private Subject subject;
	private boolean isFulltime;

	public Tutor() {
		this("", "", null);
	}

	public Tutor(String f_name, String l_name, Subject subject) {
		this(f_name, l_name, subject, "", true);
	}

	public Tutor(String f_name, String l_name, Subject subject, String address, boolean isFulltime) {
		super(f_name, l_name, address);
		this.subject = subject;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
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
		builder.append("Tutor [id= ");
		builder.append(id);
		builder.append(", f_name= ");
		builder.append(f_name);
		builder.append(", l_name= ");
		builder.append(l_name);
		builder.append(", address= ");
		builder.append(address);
		builder.append(", subject= ");
		builder.append(subject);
		builder.append(", isFulltime= ");
		builder.append(isFulltime);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (isFulltime ? 1231 : 1237);
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
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
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
			return false;
		return true;
	}

}

package spring.desai.common.model;

public class User extends Person {

	private static final long serialVersionUID = 1234324545L;
	
	private int failed_attempts;
	private boolean isAccountNonLocked;
	private SIGN_ON_STATUS signOnStatus;
	
	public enum SIGN_ON_STATUS {
		LOGGED_IN,
		LOGGED_OUT
	}
	
	public User(String id, String f_name, String l_name, String address, int failed_attempts, boolean isAccountNonLocked, SIGN_ON_STATUS signOnStatus) {
		super(id, f_name, l_name, address);
		this.failed_attempts = failed_attempts;
		this.isAccountNonLocked = isAccountNonLocked;
		this.signOnStatus = signOnStatus;
	}
	
	public User(String user_id, String f_name, String l_name, String address) {
		this(user_id, f_name, l_name, address, 0, true, SIGN_ON_STATUS.LOGGED_OUT);
	}
	
	public int getFailed_attempts() {
		return failed_attempts;
	}

	public void setFailed_attempts(int failed_attempts) {
		this.failed_attempts = failed_attempts;
	}

	public boolean isAccountNonLocked() {
		return isAccountNonLocked;
	}

	public void setAccountNonLocked(boolean isAccountNonLocked) {
		this.isAccountNonLocked = isAccountNonLocked;
	}

	public SIGN_ON_STATUS getSignOnStatus() {
		return signOnStatus;
	}

	public void setSignOnStatus(SIGN_ON_STATUS signOnStatus) {
		this.signOnStatus = signOnStatus;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [failed_attempts=");
		builder.append(failed_attempts);
		builder.append(", isAccountLocked=");
		builder.append(isAccountNonLocked);
		builder.append(", signOnStatus=");
		builder.append(signOnStatus);
		builder.append(", id=");
		builder.append(id);
		builder.append(", f_name=");
		builder.append(f_name);
		builder.append(", l_name=");
		builder.append(l_name);
		builder.append(", address=");
		builder.append(address);
		builder.append(']');
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + failed_attempts;
		result = prime * result + (isAccountNonLocked ? 1231 : 1237);
		result = prime * result + signOnStatus.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof User))
			return false;
		User other = (User) obj;
		if (failed_attempts != other.failed_attempts)
			return false;
		if (isAccountNonLocked != other.isAccountNonLocked)
			return false;
		if (signOnStatus != other.signOnStatus)
			return false;
		return true;
	}
}

package spring.desai.common.model;

public class User extends Person {

	private static final long serialVersionUID = 1234324545L;
	
	private int failedAttempts;
	private boolean isAccountNonLocked;
	private SIGN_ON_STATUS signOnStatus;
	
	public enum SIGN_ON_STATUS {
		LOGGED_IN,
		LOGGED_OUT
	}
	
	public User(String user_id, String firstname, String lastname, String address) {
		this(user_id, firstname, lastname, address, 0, true, SIGN_ON_STATUS.LOGGED_OUT);
	}
	
	public User(String id, String firstname, String lastname, String address, int failedAttempts, boolean isAccountNonLocked, SIGN_ON_STATUS signOnStatus) {
		super(id, firstname, lastname, address);
		this.failedAttempts = failedAttempts;
		this.isAccountNonLocked = isAccountNonLocked;
		this.signOnStatus = signOnStatus;
	}
	
	public int getFailedAttempts() {
		return failedAttempts;
	}

	public void setFailedAttempts(int failedAttempts) {
		this.failedAttempts = failedAttempts;
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
		builder.append("User [failedAttempts=");
		builder.append(failedAttempts);
		builder.append(", isAccountLocked=");
		builder.append(isAccountNonLocked);
		builder.append(", signOnStatus=");
		builder.append(signOnStatus);
		builder.append(", id=");
		builder.append(id);
		builder.append(", firstname=");
		builder.append(firstname);
		builder.append(", lastname=");
		builder.append(lastname);
		builder.append(", address=");
		builder.append(address);
		builder.append(", isActive=");
		builder.append(isActive);
		builder.append(']');
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + failedAttempts;
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
		if (failedAttempts != other.failedAttempts)
			return false;
		if (isAccountNonLocked != other.isAccountNonLocked)
			return false;
		if (signOnStatus != other.signOnStatus)
			return false;
		return true;
	}
}

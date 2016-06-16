package spring.desai.common.security.user;

import static spring.desai.common.utils.UserAuthenticationConstants.NO_ACTIVITY_PERFORMED;

import java.io.Serializable;
import java.util.Arrays;

public class UserLogin implements Serializable {

	private static final long serialVersionUID = 7370326212952865693L;
	private final String userId;
	private final char[] password;
	private int state = NO_ACTIVITY_PERFORMED;

	public UserLogin(String userId, char[] password) {
		this.userId = userId;
		this.password = password;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getState() {
		return state;
	}

	public String getUserId() {
		return userId;
	}

	public char[] getPassword() {
		return password;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserLogin [userId=");
		builder.append(userId);
		builder.append(", state=");
		builder.append(state);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(password);
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof UserLogin))
			return false;
		UserLogin other = (UserLogin) obj;
		if (!Arrays.equals(password, other.password))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

}

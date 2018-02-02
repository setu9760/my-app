package spring.desai.common.model;

import java.util.Locale;

import org.springframework.security.core.GrantedAuthority;

public class Role implements Persistable, GrantedAuthority {

	private static final long serialVersionUID = -3981099290239790487L;
	private String role;
	private String roleFull;
	private String description;
	private int isActive;

	public Role(String role, String roleFull) {
		this(role, roleFull, null);
	}
	
	public Role(String role, String roleFull, String description) {
		this.role = role.toUpperCase(Locale.getDefault());
		this.roleFull = roleFull;
		this.description = description;
		isActive = 1;
	}
	
	@Override
	public String getId() {
		return getRole();
	}

	private String getRole() {
		return role;
	}

	public String getRoleFull() {
		return roleFull;
	}

	public String getDescription() {
		return description;
	}
	
	public int getIsActive() {
		return isActive;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + isActive;
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((roleFull == null) ? 0 : roleFull.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (isActive != other.isActive)
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (roleFull == null) {
			if (other.roleFull != null)
				return false;
		} else if (!roleFull.equals(other.roleFull))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Role [role=");
		builder.append(role);
		builder.append(", roleFull=");
		builder.append(roleFull);
		builder.append(", description=");
		builder.append(description);
		builder.append(", isActive=");
		builder.append(isActive);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public String getAuthority() {
		return role;
	}
}

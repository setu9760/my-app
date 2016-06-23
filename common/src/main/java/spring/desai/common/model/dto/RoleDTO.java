package spring.desai.common.model.dto;

public class RoleDTO {
	String role;
	String roleFull;
	String description;

	public RoleDTO(String role, String roleFull, String description) {
		super();
		this.role = role;
		this.roleFull = roleFull;
		this.description = description;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getRoleFull() {
		return roleFull;
	}

	public void setRoleFull(String roleFull) {
		this.roleFull = roleFull;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}

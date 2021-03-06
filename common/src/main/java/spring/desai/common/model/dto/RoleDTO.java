package spring.desai.common.model.dto;

import spring.desai.common.annotations.NotEmptyAndMinMaxSizeID;

public class RoleDTO implements DTO {
	
	@NotEmptyAndMinMaxSizeID
	private String role;
	private String roleFull;
	private String description;

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
	
	@Override
	public String getId() {
		return role;
	}
}

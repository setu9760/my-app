package spring.desai.common.model.dto;

import spring.desai.common.annotations.NotEmptyAndMinMaxSizeID;

public class SubjectDTO implements DTO {
	
	@NotEmptyAndMinMaxSizeID
	private String id;
	private String name;
	private String costCode;
	private boolean isMandatory;

	public SubjectDTO() {
	}
	
	public SubjectDTO(String id, String name, String costCode, boolean isMandatory) {
		super();
		this.id = id;
		this.name = name;
		this.costCode = costCode;
		this.isMandatory = isMandatory;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCostCode() {
		return costCode;
	}

	public void setCostCode(String costCode) {
		this.costCode = costCode;
	}

	public boolean isMandatory() {
		return isMandatory;
	}

	public void setMandatory(boolean isMandatory) {
		this.isMandatory = isMandatory;
	}

}

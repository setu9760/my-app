package spring.desai.common.model.dto;

import spring.desai.common.annotations.NotEmptyAndMinMaxSizeID;

public class ScholarshipDTO implements DTO {
	
	@NotEmptyAndMinMaxSizeID
	private String id;
	private String externalRef;
	private String type;
	private Double totalAmount;
	private Double paidAmount;
	private Boolean isFullyPaid;
	private Boolean isPostPay;
	private String studentId;
	private String additionalComments;

	public ScholarshipDTO() {
	}
	
	public ScholarshipDTO(String id, String externalRef, String type, Double totalAmount, Double paidAmount,
			Boolean isFullyPaid, Boolean isPostPay, String studentId, String additionalComments) {
		super();
		this.id = id;
		this.externalRef = externalRef;
		this.type = type;
		this.totalAmount = totalAmount;
		this.paidAmount = paidAmount;
		this.isFullyPaid = isFullyPaid;
		this.isPostPay = isPostPay;
		this.studentId = studentId;
		this.additionalComments = additionalComments;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getExternalRef() {
		return externalRef;
	}

	public void setExternalRef(String externalRef) {
		this.externalRef = externalRef;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Double getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(Double paidAmount) {
		this.paidAmount = paidAmount;
	}

	public Boolean getIsFullyPaid() {
		return isFullyPaid;
	}

	public void setIsFullyPaid(Boolean isFullyPaid) {
		this.isFullyPaid = isFullyPaid;
	}

	public Boolean getIsPostPay() {
		return isPostPay;
	}

	public void setIsPostPay(Boolean isPostPay) {
		this.isPostPay = isPostPay;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getAdditionalComments() {
		return additionalComments;
	}

	public void setAdditionalComments(String additionalComments) {
		this.additionalComments = additionalComments;
	}

}

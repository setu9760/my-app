package spring.desai.common.model.dto;

import org.hibernate.validator.constraints.Range;

import spring.desai.common.annotations.NotEmptyAndMinMaxSizeID;

public class CostDTO implements DTO {
	
	@NotEmptyAndMinMaxSizeID
	private String costCode;
	@Range(min=0, message="amount must be greater than zero")
	private Double amount;

	public CostDTO() {
	}
	
	public CostDTO(String costCode, Double amount) {
		super();
		this.costCode = costCode;
		this.amount = amount;
	}

	public String getCostCode() {
		return costCode;
	}
	public void setCostCode(String costCode) {
		this.costCode = costCode;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Override
	public String getId() {
		return costCode;
	}
}

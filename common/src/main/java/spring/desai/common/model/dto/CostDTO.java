package spring.desai.common.model.dto;

public class CostDTO implements DTO {
	private String costCode;
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

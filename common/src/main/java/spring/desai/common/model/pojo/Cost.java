package spring.desai.common.model.pojo;

import spring.desai.common.model.enums.CostCode;

public class Cost implements Persistable {

	private static final long serialVersionUID = 123412341341L;
	private CostCode costCode;
	private double amount;

	public Cost(CostCode costCode, double amount) {
		this.costCode = costCode;
		this.amount = amount;
	}

	public void setCost_code(CostCode cost_code) {
		this.costCode = cost_code;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String getId() {
		return costCode.toString();
	}

	public String getCostCode() {
		return getId();
	}

	public double getAmount() {
		return amount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((costCode == null) ? 0 : costCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Cost))
			return false;
		Cost other = (Cost) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (costCode != other.costCode)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Cost [cost_code=");
		builder.append(costCode);
		builder.append(", amount=");
		builder.append(amount);
		builder.append("]");
		return builder.toString();
	}
}

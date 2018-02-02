package spring.desai.common.model;

import static spring.desai.common.utils.DataBaseConstants.AMOUNT;
import static spring.desai.common.utils.DataBaseConstants.COST_CODE;
import static spring.desai.common.utils.DataBaseConstants.COST_TABLE_NAME;
import static spring.desai.common.utils.DataBaseConstants.IS_ACTIVE;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = COST_TABLE_NAME)
public class Cost implements Persistable {

	private static final long serialVersionUID = 123412341341L;

	@Id
	@Column(name = COST_CODE)
	private String costCode;

	@Column(name = AMOUNT)
	private double amount;

	@Column(name = IS_ACTIVE)
	private int isActive;

	public Cost(String costCode, double amount) {
		this.costCode = costCode;
		this.amount = amount;
		isActive = 1;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	@Override
	public String getId() {
		return costCode;
	}

	public String getCostCode() {
		return costCode;
	}

	public double getAmount() {
		return amount;
	}

	public int getIsActive() {
		return isActive;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((costCode == null) ? 0 : costCode.hashCode());
		result = prime * result + isActive;
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
		Cost other = (Cost) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (costCode == null) {
			if (other.costCode != null)
				return false;
		} else if (!costCode.equals(other.costCode))
			return false;
		if (isActive != other.isActive)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cost [costCode=" + costCode + ", amount=" + amount + ", isActive=" + isActive + "]";
	}
}

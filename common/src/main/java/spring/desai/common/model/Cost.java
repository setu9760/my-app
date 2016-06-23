package spring.desai.common.model;

import static spring.desai.common.utils.DataBaseConstants.AMOUNT;
import static spring.desai.common.utils.DataBaseConstants.COST_CODE;
import static spring.desai.common.utils.DataBaseConstants.COST_TABLE_NAME;
import static spring.desai.common.utils.DataBaseConstants.SUBJECT_TABLE_NAME;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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

	public Cost() {
	}
	
	public Cost(String costCode, double amount) {
		this.costCode = costCode;
		this.amount = amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
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

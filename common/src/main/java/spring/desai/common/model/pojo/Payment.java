package spring.desai.common.model.pojo;

import org.joda.time.DateTime;

import spring.desai.common.model.enums.PaymentType;

public class Payment implements Persistable {

	private static final long serialVersionUID = 1L;
	private String id;
	private double amount;
	private PaymentType paymentType;
	private DateTime paymentDateTime;
	private String stud_id;

	public Payment() {
		this("", 1.0, null);
	}

	public Payment(String id, double amount, PaymentType paymentType) {
		this.id = id;
		this.amount = amount;
		this.paymentType = paymentType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public DateTime getPaymentDateTime() {
		return paymentDateTime;
	}

	public void setPaymentDateTime(DateTime paymentDateTime) {
		this.paymentDateTime = paymentDateTime;
	}

	public String getStud_id() {
		return stud_id;
	}

	public void setStud_id(String stud_id) {
		this.stud_id = stud_id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((paymentDateTime == null) ? 0 : paymentDateTime.hashCode());
		result = prime * result + ((paymentType == null) ? 0 : paymentType.hashCode());
		result = prime * result + ((stud_id == null) ? 0 : stud_id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Payment))
			return false;
		Payment other = (Payment) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (paymentDateTime == null) {
			if (other.paymentDateTime != null)
				return false;
		} else if (!paymentDateTime.equals(other.paymentDateTime))
			return false;
		if (paymentType != other.paymentType)
			return false;
		if (stud_id == null) {
			if (other.stud_id != null)
				return false;
		} else if (!stud_id.equals(other.stud_id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Payment [id=");
		builder.append(id);
		builder.append(", amount=");
		builder.append(amount);
		builder.append(", paymentType=");
		builder.append(paymentType);
		builder.append(", paymentDateTime=");
		builder.append(paymentDateTime);
		builder.append(", stud_id=");
		builder.append(stud_id);
		builder.append("]");
		return builder.toString();
	}

}

package spring.desai.common.model.pojo;

import static spring.desai.common.utils.DataBaseConstants.ADDITIONAL_COMMENTS;
import static spring.desai.common.utils.DataBaseConstants.AMOUNT;
import static spring.desai.common.utils.DataBaseConstants.DATETIME;
import static spring.desai.common.utils.DataBaseConstants.ID;
import static spring.desai.common.utils.DataBaseConstants.PAYMENT_TABLE_NAME;
import static spring.desai.common.utils.DataBaseConstants.STUD_ID;
import static spring.desai.common.utils.DataBaseConstants.TYPE;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import spring.desai.common.model.enums.PaymentType;

@Entity
@Table(name = PAYMENT_TABLE_NAME)
public class Payment implements Persistable {

	private static final long serialVersionUID = 1231412342241L;
	@Id
	@Column(name = ID)
	private String id;
	
	@Column(name = AMOUNT)
	private double amount;
	
	@Transient
	private PaymentType paymentType;
	
//	@ManyToOne(targetEntity = Payment.class)
//	@JoinColumn(name = STUD_ID, nullable = false, referencedColumnName = ID)
	@Column(name = STUD_ID)
	private String stud_id;
	
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @Column(name=DATETIME, nullable=false, columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP")
	private DateTime paymentDateTime;
	
	@Column(name = ADDITIONAL_COMMENTS, nullable = true)
	private String comments;

	public Payment() {
		this(null, 0, null, null, null);
	}
	
	public Payment(String id, double amount, PaymentType paymentType) {
		this(id, amount, paymentType, null);
	}
	
	public Payment(double amount, PaymentType paymentType, String stud_id) {
		this(null, amount, paymentType, stud_id);
	}
	
	public Payment(String id, double amount, PaymentType paymentType, String stud_id) {
		this(id, amount, paymentType, stud_id, null);
	}
	
	public Payment(String id, double amount, PaymentType paymentType, String stud_id, String comments) {
		this.id = id;
		this.amount = amount;
		this.paymentType = paymentType;
		this.stud_id = stud_id;
		this.comments = comments;
		this.paymentDateTime = DateTime.now();
	}

	public String getId() {
		return id;
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
	
	@Column(name = TYPE)
	public String getPaymentTypeString(){
		return String.valueOf(paymentType);
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
	
	public String getComments() {
		return comments;
	}
	
	public void setComments(String comments) {
		this.comments = comments;
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
		
//		if (paymentDateTime == null) {
//			if (other.paymentDateTime != null)
//				return false;
//		} else if (!paymentDateTime.equals(other.paymentDateTime))
//			return false;
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
		builder.append(", stud_id=");
		builder.append(stud_id);
		builder.append(", paymentDateTime=");
		builder.append(paymentDateTime);
		builder.append(", comments=");
		builder.append(comments);
		builder.append("]");
		return builder.toString();
	}

}

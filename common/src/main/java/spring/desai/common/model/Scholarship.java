package spring.desai.common.model;

import static spring.desai.common.utils.DataBaseConstants.ADDITIONAL_COMMENTS;
import static spring.desai.common.utils.DataBaseConstants.EXTERNAL_REF;
import static spring.desai.common.utils.DataBaseConstants.ID;
import static spring.desai.common.utils.DataBaseConstants.IS_FULLY_PAID;
import static spring.desai.common.utils.DataBaseConstants.IS_POST_PAY;
import static spring.desai.common.utils.DataBaseConstants.PAID_AMOUNT;
import static spring.desai.common.utils.DataBaseConstants.SCHOLORSHIP_TABLE_NAME;
import static spring.desai.common.utils.DataBaseConstants.STUD_ID;
import static spring.desai.common.utils.DataBaseConstants.TOTAL_AMOUNT;
import static spring.desai.common.utils.DataBaseConstants.TYPE;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import spring.desai.common.model.enums.ScholorshipType;

@Entity
@Table(name = SCHOLORSHIP_TABLE_NAME)
public class Scholarship implements Persistable{

	private static final long serialVersionUID = 234123434141L;
	
	@Id
	@Column(name = ID, nullable = false)
	private String id;
	
	@Column(name = EXTERNAL_REF)
	private String externalRef;
	
	@Transient
	private ScholorshipType type;
	
	@Column(name = TOTAL_AMOUNT)
	private double totalAmount;
	
	@Column(name = PAID_AMOUNT)
	private double paidAmount;
	
	@Column(name = IS_FULLY_PAID)
	private boolean isFullyPaid;
	
	@Column(name = IS_POST_PAY)
	private boolean isPostPay;
	
	@ManyToOne(targetEntity = Scholarship.class)
	@JoinColumn(name = STUD_ID, nullable = false, referencedColumnName = ID)
	private String studentId;
	
	@Column(name = ADDITIONAL_COMMENTS)
	private String additionalComments;

	public Scholarship() {
	}
	
	public Scholarship(String id, ScholorshipType type, double totalAmount) {
		this.id = id;
		this.type = type;
		this.totalAmount = totalAmount;
	}

	public Scholarship(String id, ScholorshipType type, double totalAmount, double paidAmount, boolean isFullyPaid, boolean isPostPay, String additionalComments,  String studentId) {
		this(id, "N/A", type, totalAmount, paidAmount, isFullyPaid, isPostPay, additionalComments, studentId);
	}
	
	public Scholarship(String id, String externalRef, ScholorshipType type, double totalAmount, double paidAmount, boolean isFullyPaid, boolean isPostPay, String additionalComments,  String studentId) {
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

	public ScholorshipType getType() {
		return type;
	}
	
	@Column(name = TYPE)
	public String getTypeString() {
		return String.valueOf(type);
	}

	public void setType(ScholorshipType type) {
		this.type = type;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public double getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(double paidAmount) {
		this.paidAmount = paidAmount;
	}

	public boolean isFullyPaid() {
		return isFullyPaid;
	}

	public void setFullyPaid(boolean isFullyPaid) {
		this.isFullyPaid = isFullyPaid;
	}

	public boolean isPostPay() {
		return isPostPay;
	}

	public void setPostPay(boolean isPostPay) {
		this.isPostPay = isPostPay;
	}
	
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	
	public String getStudentId() {
		return studentId;
	}

	public String getAdditionalComments() {
		return additionalComments;
	}

	public void setAdditionalComments(String additionalComments) {
		this.additionalComments = additionalComments;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Scholorship [id=");
		builder.append(id);
		builder.append(", externalRef=");
		builder.append(externalRef);
		builder.append(", type=");
		builder.append(type);
		builder.append(", totalAmount=");
		builder.append(totalAmount);
		builder.append(", paidAmount=");
		builder.append(paidAmount);
		builder.append(", isFullyPaid=");
		builder.append(isFullyPaid);
		builder.append(", isPostPay=");
		builder.append(isPostPay);
		builder.append(", studentId=");
		builder.append(studentId);
		builder.append(", additionalComments=");
		builder.append(additionalComments);
		builder.append(']');
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((externalRef == null) ? 0 : externalRef.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((studentId == null) ? 0 : studentId.hashCode());
		long temp;
		temp = Double.doubleToLongBits(totalAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Scholarship))
			return false;
		Scholarship other = (Scholarship) obj;
		if (externalRef == null) {
			if (other.externalRef != null)
				return false;
		} else if (!externalRef.equals(other.externalRef))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (studentId == null) {
			if (other.studentId != null)
				return false;
		} else if (!studentId.equals(other.studentId))
			return false;
		if (Double.doubleToLongBits(totalAmount) != Double.doubleToLongBits(other.totalAmount))
			return false;
		if (type != other.type)
			return false;
		return true;
	}
}

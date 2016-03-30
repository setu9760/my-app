package spring.desai.common.model.pojo;

import spring.desai.common.model.enums.ScholorshipType;

public class Scholorship implements Persistable{

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String external_ref;
	private ScholorshipType type;
	private double total_amount;
	private double paid_amount;
	private boolean isFullyPaid;
	private boolean isPostPay;
	private String stud_id;
	private String additional_comments;

	public Scholorship() {
		this("", null, 0);
	}

	public Scholorship(String id, ScholorshipType type, double total_amount) {
		this.id = id;
		this.type = type;
		this.total_amount = total_amount;
	}

	public Scholorship(String id, String external_ref, ScholorshipType type, double total_amount, double paid_amount, boolean isFullyPaid, boolean isPostPay, String additional_comments,  String stud_id) {
		super();
		this.id = id;
		this.external_ref = external_ref;
		this.type = type;
		this.total_amount = total_amount;
		this.paid_amount = paid_amount;
		this.isFullyPaid = isFullyPaid;
		this.isPostPay = isPostPay;
		this.stud_id = stud_id;
		this.additional_comments = additional_comments;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getExternal_ref() {
		return external_ref;
	}

	public void setExternal_ref(String external_ref) {
		this.external_ref = external_ref;
	}

	public ScholorshipType getType() {
		return type;
	}

	public void setType(ScholorshipType type) {
		this.type = type;
	}

	public double getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(double total_amount) {
		this.total_amount = total_amount;
	}

	public double getPaid_amount() {
		return paid_amount;
	}

	public void setPaid_amount(double paid_amount) {
		this.paid_amount = paid_amount;
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
	
	public void setStud_id(String stud_id) {
		this.stud_id = stud_id;
	}
	
	public String getStud_id() {
		return stud_id;
	}

	public String getAdditional_comments() {
		return additional_comments;
	}

	public void setAdditional_comments(String additional_comments) {
		this.additional_comments = additional_comments;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Scholorship [id=");
		builder.append(id);
		builder.append(", external_ref=");
		builder.append(external_ref);
		builder.append(", type=");
		builder.append(type);
		builder.append(", total_amount=");
		builder.append(total_amount);
		builder.append(", paid_amount=");
		builder.append(paid_amount);
		builder.append(", isFullyPaid=");
		builder.append(isFullyPaid);
		builder.append(", isPostPay=");
		builder.append(isPostPay);
		builder.append(", stud_id=");
		builder.append(stud_id);
		builder.append(", additional_comments=");
		builder.append(additional_comments);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((external_ref == null) ? 0 : external_ref.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((stud_id == null) ? 0 : stud_id.hashCode());
		long temp;
		temp = Double.doubleToLongBits(total_amount);
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
		if (!(obj instanceof Scholorship))
			return false;
		Scholorship other = (Scholorship) obj;
		if (external_ref == null) {
			if (other.external_ref != null)
				return false;
		} else if (!external_ref.equals(other.external_ref))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (stud_id == null) {
			if (other.stud_id != null)
				return false;
		} else if (!stud_id.equals(other.stud_id))
			return false;
		if (Double.doubleToLongBits(total_amount) != Double.doubleToLongBits(other.total_amount))
			return false;
		if (type != other.type)
			return false;
		return true;
	}
}

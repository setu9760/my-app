package spring.desai.common.model.pojo;

public class Scholorship implements Persistable{

	private static final long serialVersionUID = 1L;

	public enum ScholorshipType {
		MGMT_FULL("Management paid - Full"),
		MGMT_PART("Management paid - Part"),
		STATE_FULL("State paid - Full"),
		STATE_PART("State paid - Part"),
		NATIONAL_FULL("Natinal paid - Full"),
		NATIONAL_PART("National paid - Part"),
		OTHER("Other source (See externalRef)");
		
		private final String description;
		
		private ScholorshipType() {
			this("No Description");
		}
		
		private ScholorshipType (String description) {
			this.description = description;
		}
		
		public String getDescription() {
			return description;
		}
	}

	private String id;
	private String external_ref;
	private ScholorshipType type;
	private double total_amount;
	private double paid_amount;
	private boolean isFullyPaid;
	private boolean isPostPay;
	private String additional_comments;

	public Scholorship() {
		this("", null, 0);
	}

	public Scholorship(String id, ScholorshipType type, double total_amount) {
		this.id = id;
		this.type = type;
		this.total_amount = total_amount;
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
		builder.append("( ");
		builder.append(type.getDescription());
		builder.append(" )");
		builder.append(", total_amount=");
		builder.append(total_amount);
		builder.append(", paid_amount=");
		builder.append(paid_amount);
		builder.append(", isFullyPaid=");
		builder.append(isFullyPaid);
		builder.append(", isPostPay=");
		builder.append(isPostPay);
		builder.append(", additional_comments=");
		builder.append(additional_comments);
		builder.append("]");
		return builder.toString();
	}

}

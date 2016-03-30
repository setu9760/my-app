package spring.desai.common.model.enums;

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
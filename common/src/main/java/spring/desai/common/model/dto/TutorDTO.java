package spring.desai.common.model.dto;

public class TutorDTO extends PersonDTO {

	private String subjectId;
	private Boolean isFulltime;

	public TutorDTO() {
		super();
	}

	public TutorDTO(String id, String f_name, String l_name, String address, String subjectId, Boolean isFulltime) {
		super(id, f_name, l_name, address);
		this.subjectId = subjectId;
		this.isFulltime = isFulltime;
	}

	public String getSubject() {
		return subjectId;
	}

	public void setSubject(String subjectId) {
		this.subjectId = subjectId;
	}

	public Boolean isFulltime() {
		return isFulltime;
	}

	public void setFulltime(boolean isFulltime) {
		this.isFulltime = isFulltime;
	}

}

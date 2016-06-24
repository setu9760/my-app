package spring.desai.common.model.dto;

public class TutorDTO extends PersonDTO {

	SubjectDTO subject;
	Boolean isFulltime;

	public TutorDTO(String id, String f_name, String l_name, String address, SubjectDTO subject, Boolean isFulltime) {
		super(id, f_name, l_name, address);
		this.subject = subject;
		this.isFulltime = isFulltime;
	}

	public SubjectDTO getSubject() {
		return subject;
	}

	public void setSubject(SubjectDTO subject) {
		this.subject = subject;
	}

	public Boolean isFulltime() {
		return isFulltime;
	}

	public void setFulltime(boolean isFulltime) {
		this.isFulltime = isFulltime;
	}

}

package spring.desai.common.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ExceptionDTO {

	private String responseCode;
	private String developerMessage;
	private String errorMessageStack;
	
	@JsonIgnore
	private Exception e;
	
	public ExceptionDTO() {
	}
	
	public ExceptionDTO(String responseCode, String developerMessage, Exception e) {
		super();
		this.responseCode = responseCode;
		this.developerMessage = developerMessage;
		this.e = e;
		this.errorMessageStack = generateErrorStackMessage(e);
	}
	
	/**
	 * Generate an error message that is the concatenation of all the stacks
	 * @param t
	 * @return String
	 */
	private String generateErrorStackMessage(Throwable t) {
		StringBuilder buf = new StringBuilder(t.getMessage()==null ? "" : t.getMessage());
		Throwable cause = t.getCause();
		while (cause != null) {
			buf.append(System.lineSeparator());
			buf.append(cause.getMessage());
			cause = cause.getCause();
		}
		return buf.toString();
	}
	

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getDeveloperMessage() {
		return developerMessage;
	}

	public void setDeveloperMessage(String developerMessage) {
		this.developerMessage = developerMessage;
	}

	public String getStackTrace() {
		return errorMessageStack;
	}

	public Exception getE() {
		return e;
	}

	public void setE(Exception e) {
		this.e = e;
	}

}

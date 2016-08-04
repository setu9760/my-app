package spring.desai.common.model.dto;

public class ExceptionDTO {

	String responseCode;
	String developerMessage;
	StackTraceElement[] stackTrace;
	Exception e;

	public ExceptionDTO(String responseCode, String developerMessage, StackTraceElement[] stackTrace, Exception e) {
		super();
		this.responseCode = responseCode;
		this.developerMessage = developerMessage;
		this.stackTrace = stackTrace;
		this.e = e;
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

	public StackTraceElement[] getStackTrace() {
		return stackTrace;
	}

	public void setStackTrace(StackTraceElement[] stackTrace) {
		this.stackTrace = stackTrace;
	}

	public Exception getE() {
		return e;
	}

	public void setE(Exception e) {
		this.e = e;
	}

}

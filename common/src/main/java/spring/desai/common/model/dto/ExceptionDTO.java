package spring.desai.common.model.dto;

import org.apache.commons.lang3.exception.ExceptionUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ExceptionDTO {

	private String responseCode;
	private String userMessage;
	private String errorMessageStack;
	
	@JsonIgnore
	private Exception e;
	
	public ExceptionDTO() {
	}
	
	public ExceptionDTO(String responseCode, String userMessage, Exception e) {
		super();
		this.responseCode = responseCode;
		this.userMessage = userMessage;
		this.e = e;
		this.errorMessageStack = ExceptionUtils.getStackTrace(e);
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}
	
	public String getUserMessage() {
		return userMessage;
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

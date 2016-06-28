package spring.desai.common.service;

/**
 * Composite response object from service to weblayer. Services can enclose the
 * actual repository result along with any other details.
 * 
 * @author desai
 *
 */
public class ServiceResponse {

	private int responseCode;
	private String responseMessage;
	private Throwable throwable;
	private Object actualResponseObject;

	private ServiceResponse() {

	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public Throwable getThrowable() {
		return throwable;
	}

	public void setThrowable(Throwable throwable) {
		this.throwable = throwable;
	}

	public Object getActualResponseObject() {
		return actualResponseObject;
	}

	public void setActualResponseObject(Object actualResponseObject) {
		this.actualResponseObject = actualResponseObject;
	}

}

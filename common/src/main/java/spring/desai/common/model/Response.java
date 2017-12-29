package spring.desai.common.model;

/**
 * Composite response object from weblayer. Controllers can can enclose the actual 
 * results from service layer along with any other details i.e. response code, exception detail etc.
 * 
 * @author desai
 *
 */
public class Response<T> {

	private String responseMessage;
	private T responseObject;
	private Throwable throwable;

//	public Response() {
//	}
	
//	public Response(String responseMessage) {
//		this(responseMessage, null, null);
//	}
//	
	public Response(String responseMessage, Throwable throwable) {
		this(responseMessage, null, throwable);
	}

	public Response(String responseMessage, T responseObject) {
		this(responseMessage, responseObject, null);
	}
	
	public Response(String responseMessage, T responseObject, Throwable throwable) {
		this.responseMessage = responseMessage;
		this.responseObject = responseObject;
		this.throwable = throwable;
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

	public T getResponseObject() {
		return responseObject;
	}

	public void setResponseObject(T responseObject) {
		this.responseObject = responseObject;
	}

}

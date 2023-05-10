package app.core.exceptions;

public class CustomersException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CustomersException() {
	}

	public CustomersException(String message) {
		super(message);
	}

	public CustomersException(Throwable cause) {
		super(cause);
	}

	public CustomersException(String message, Throwable cause) {
		super(message, cause);
	}

	public CustomersException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}

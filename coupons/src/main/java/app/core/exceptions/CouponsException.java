package app.core.exceptions;

public class CouponsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CouponsException() {
	}

	public CouponsException(String message) {
		super(message);
	}

	public CouponsException(Throwable cause) {
		super(cause);
	}

	public CouponsException(String message, Throwable cause) {
		super(message, cause);
	}

	public CouponsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}

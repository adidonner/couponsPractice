package app.core.exceptions;

public class CompaniesException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CompaniesException() {
	}

	public CompaniesException(String message) {
		super(message);
	}

	public CompaniesException(Throwable cause) {
		super(cause);
	}

	public CompaniesException(String message, Throwable cause) {
		super(message, cause);
	}

	public CompaniesException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}

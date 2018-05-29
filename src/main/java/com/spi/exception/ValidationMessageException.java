package com.spi.exception;

public class ValidationMessageException extends RuntimeException {

	private static final long serialVersionUID = -5425147660454204397L;
	
	public ValidationMessageException(String message) {
        super(message);
    }

    public ValidationMessageException() {
        super();
    }

    public ValidationMessageException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidationMessageException(Throwable cause) {
        super(cause);
    }

}

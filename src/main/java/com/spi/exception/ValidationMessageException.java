package com.spi.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.springframework.http.ResponseEntity;

import com.spi.response.ErrorResponse;
import com.spi.response.ValidationError;

public class ValidationMessageException extends RuntimeException {

	private static final long serialVersionUID = -5425147660454204397L;
	private final int status = 400;
	private String errorMessage;
	private String developerMessage;
	private List<ValidationError> errors = new ArrayList<ValidationError>();

	public ValidationMessageException() {
		errorMessage = "Validation Error";
		developerMessage = "The data passed in the request was invalid. Please check and resubmit";
	}

	public ValidationMessageException(String message) {
		 super(message);
		errorMessage = message;
		ValidationError error = new ValidationError();
		error.setMessage(message);
	}

	public ValidationMessageException(String message, String propertyName) {
		super();
		errorMessage = message;
		ValidationError error = new ValidationError();
		error.setMessage(message);
		error.setPropertyName(propertyName);
		errors.add(error);
	}

	public ValidationMessageException(Set<? extends ConstraintViolation<?>> violations) {
		this();
		for (ConstraintViolation<?> constraintViolation : violations) {
			ValidationError error = new ValidationError();
			error.setMessage(constraintViolation.getMessage());
			error.setPropertyName(constraintViolation.getPropertyPath().toString());
			error.setPropertyValue(
					constraintViolation.getInvalidValue() != null ? constraintViolation.getInvalidValue().toString()
							: null);
			errors.add(error);
		}
	}

	public ResponseEntity getResponse() {
		return ResponseEntity.status(status).build();
	}

	public ErrorResponse getErrorResponse() {
		ErrorResponse response = new ErrorResponse();
		response.setApplicationMessage(developerMessage);
		response.setConsumerMessage(errorMessage);
		response.setValidationErrors(errors);
		return response;
	}

}

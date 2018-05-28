package com.spi.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.spi.response.ErrorResponse;
import com.spi.response.ValidationError;

public class ValidationException extends RuntimeException{

	private static final long serialVersionUID = 8472573814841809461L;
	private final int status = 400;
	private String errorMessage;
	private String developerMessage;
	private List<ValidationError> errors = new ArrayList<ValidationError>();

	public ValidationException() {
		errorMessage = "Validation Error";
		developerMessage = "The data passed in the request was invalid. Please check and resubmit";
	}

	public ValidationException(String message) {
		//super();
		errorMessage = message;
                ValidationError error = new ValidationError();
                error.setMessage(message);
	}
        
        public ValidationException(String message, String propertyName) {
		super();
		errorMessage = message;
                ValidationError error = new ValidationError();
                error.setMessage(message);
                error.setPropertyName(propertyName);
                errors.add(error);
	}

	public ValidationException(Set<? extends ConstraintViolation<?>> violations) {
		this();
		for (ConstraintViolation<?> constraintViolation : violations) {
			ValidationError error = new ValidationError();
			error.setMessage(constraintViolation.getMessage());
			error.setPropertyName(constraintViolation.getPropertyPath().toString());
			error.setPropertyValue(constraintViolation.getInvalidValue() != null ? constraintViolation.getInvalidValue().toString() : null);
			errors.add(error);
		}
	}

	public ResponseEntity getResponse() {
		return ResponseEntity.status(status)
				.build();
	}

	public ErrorResponse getErrorResponse() {
		ErrorResponse response = new ErrorResponse();
		response.setApplicationMessage(developerMessage);
		response.setConsumerMessage(errorMessage);
		response.setValidationErrors(errors);
		return response;
	}

}

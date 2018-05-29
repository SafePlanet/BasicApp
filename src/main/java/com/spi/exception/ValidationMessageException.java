package com.spi.exception;

import org.springframework.core.MethodParameter;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

public class ValidationMessageException extends MethodArgumentNotValidException {

	public ValidationMessageException(MethodParameter parameter, BindingResult bindingResult) {
		super(parameter, bindingResult);
		
		System.out.println(parameter);
		System.out.println(bindingResult);
		
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -5425147660454204397L;

}

package com.spi.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spi.config.ValidationMessageConfig;
import com.spi.dm.LoginRequest;
import com.spi.dm.User;
import com.spi.exception.ValidationMessageException;

@Service
public class UserSignupValidator {
	public static final Logger LOG = LoggerFactory.getLogger(UserSignupValidator.class);

	@Autowired
	private ValidationMessageConfig validationMessageConfig;
	
	@Autowired
	private EmailValidator emailValidator;

	public void validateUser(User externalUser) {
		LOG.info("validating User");
		
		StringBuffer errorFields = new StringBuffer();
		
		final String errors = errorFields.toString();
		if (!errors.isEmpty()) {
			throw new ValidationMessageException(errors);
		}

	}

	public void validateLoginRequest(LoginRequest loginRequest) {
		LOG.info("validating loginRequest");
		
		StringBuffer errorFields = new StringBuffer();

		final String errors = errorFields.toString();
		if (!errors.isEmpty()) {
			throw new ValidationMessageException(errors);
		}
		
	}
}

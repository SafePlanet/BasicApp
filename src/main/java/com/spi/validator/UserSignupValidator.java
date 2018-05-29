package com.spi.validator;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spi.config.ValidationMessageConfig;
import com.spi.exception.ValidationMessageException;
import com.spi.service.dto.User;

@Service
public class UserSignupValidator {
	public static final Logger LOG = LoggerFactory.getLogger(UserSignupValidator.class);

	@Autowired
	private ValidationMessageConfig validationMessageConfig;
	
	@Autowired
	private EmailValidator emailValidator;

	public void validate(User externalUser) {
		LOG.info("Validation for Invoice Generation Starts");
		
		StringBuffer errorFields = new StringBuffer();
		
		if (!StringUtils.isNotBlank(externalUser.getFirstName())) {
			errorFields.append(validationMessageConfig.EXTERNAL_USER_FISRTNAME_REQUIRED);
		}
		if (!StringUtils.isNotBlank(externalUser.getLastName())) {
			errorFields.append(validationMessageConfig.EXTERNAL_USER_LASTNAME_REQUIRED);
		}
		if (!StringUtils.isNotBlank(externalUser.getEmailAddress())) {
			errorFields.append(validationMessageConfig.EXTERNAL_USER_EMAIL_REQUIRED);
		} else if(!emailValidator.validate(externalUser.getEmailAddress())) {
			errorFields.append(validationMessageConfig.EXTERNAL_USER_EMAIL_INVALID);
		}
		

		if (!StringUtils.isNotBlank(externalUser.getMobile())) {
			errorFields.append(validationMessageConfig.EXTERNAL_USER_MOBILE_REQUIRED);
		} else if (externalUser.getMobile().length() < 10 || !externalUser.getMobile().matches("[0-9]+")) {
			errorFields.append(validationMessageConfig.EXTERNAL_USER_MOBILE_INVALID);
		}

		if (!StringUtils.isNotBlank(externalUser.getHouseNo())) {
			errorFields.append(validationMessageConfig.EXTERNAL_USER_HOUSE_REQUIRED);
		}
		if (!StringUtils.isNotBlank(externalUser.getAddress())) {
			errorFields.append(validationMessageConfig.EXTERNAL_USER_ADDRESS_REQUIRED);
		}
		if (!StringUtils.isNotBlank(externalUser.getCity())) {
			errorFields.append(validationMessageConfig.EXTERNAL_USER_CITY_REQUIRED);
		}
		if (!StringUtils.isNotBlank(externalUser.getState())) {
			errorFields.append(validationMessageConfig.EXTERNAL_USER_STATE_REQUIRED);
		}
		if (!StringUtils.isNotBlank(externalUser.getPinCode())) {
			errorFields.append(validationMessageConfig.EXTERNAL_USER_PINCODE_REQUIRED);
		}
		
		final String errors = errorFields.toString();
		if (!errors.isEmpty()) {
			throw new ValidationMessageException(errors);
		}

	}
}

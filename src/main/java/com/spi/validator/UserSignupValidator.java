package com.spi.validator;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spi.config.ValidationMessageConfig;
import com.spi.service.dto.ExternalUser;

@Service
public class UserSignupValidator {
    public static final Logger LOG = LoggerFactory.getLogger(UserSignupValidator.class);
    
    @Autowired
    private ValidationMessageConfig validationMessageConfig;

    public void validate(ExternalUser externalUser) {
    	StringBuffer errors  = new StringBuffer();
        try {
            LOG.info("Validation for Invoice Generation Starts");
            if(externalUser.getFirstName() == null && StringUtils.isNotBlank(externalUser.getFirstName())) {
            	errors.append(validationMessageConfig.getExternalUserNameRequired());;
            }
            System.out.println(validationMessageConfig.xyz);
            if(externalUser.getLastName() == null && StringUtils.isNotBlank(externalUser.getLastName())) {
            	
            }
            if(externalUser.getMobile() == null && StringUtils.isNotBlank(externalUser.getMobile())) {
            	
            }
//            LOG.info("Validation for Invoice Generation Ends with errors " + errors.getErrorCount());
        } catch (Exception e) {
            LOG.error("Error in Validation ", e);
//            errors.reject(ErrorCodes.BAD_REQUEST.getCode());
        }
    }
}

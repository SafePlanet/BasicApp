/* Copyright (C) SafePlanet Innovations, LLP - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by SafePlanet Innovations <spinnovations.india@gmail.com>, October 2015
 */

package com.spi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * User: porter Date: 17/05/2012 Time: 19:07
 */
@Configuration
@PropertySource({ "classpath:/validationMessages.properties" })
public class ValidationMessageConfig {


	@Value("${external.user.username.required}")
	private final static String EXTERNAL_USER_USERNAME_REQUIRED = "external.user.username.required";
	
	@Value("${external.user.fisrtname.required}")
	private final static String EXTERNAL_USER_FISRTNAME_REQUIRED= "external.user.fisrtname.required";
	
	@Value("${external.user.lastname.required}")
	private final static String EXTERNAL_USER_LASTNAME_REQUIRED = "external.user.lastname.required";
	
	@Value("${external.user.email.required}")
	private final static String EXTERNAL_USER_EMAIL_REQUIRED = "external.user.email.required";
	
	@Value("${external.user.email.invalid}")
	private final static String EXTERNAL_USER_EMAIL_INVALID = "external.user.email.invalid";
	
	@Value("${external.user.password.required}")
	private final static String EXTERNAL_USER_PASSWORD_REQUIRED = "external.user.password.required";
	
	@Value("${external.user.password.not.matching}")
	private final static String EXTERNAL_USER_PASSWORD_NOT_MATCHING = "external.user.password.not.matching";
	
	@Value("${external.user.password.length}")
	private final static String EXTERNAL_USER_PASSWORD_LENGTH = "external.user.password.length";
	
	@Value("${external.user.password.length}")
	public String xyz;

}

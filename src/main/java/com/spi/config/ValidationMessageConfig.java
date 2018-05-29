/* Copyright (C) SafePlanet Innovations, LLP - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by SafePlanet Innovations <spinnovations.india@gmail.com>, October 2015
 */

package com.spi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource({ "classpath:/validationMessages.properties" })
public class ValidationMessageConfig {


	@Value("${external.user.username.required}")
	public String EXTERNAL_USER_USERNAME_REQUIRED;
	
	@Value("${external.user.fisrtname.required}")
	public String EXTERNAL_USER_FISRTNAME_REQUIRED;
	
	@Value("${external.user.lastname.required}")
	public String EXTERNAL_USER_LASTNAME_REQUIRED;
	
	@Value("${external.user.email.required}")
	public String EXTERNAL_USER_EMAIL_REQUIRED;
	
	@Value("${external.user.email.invalid}")
	public String EXTERNAL_USER_EMAIL_INVALID;
	
	@Value("${external.user.password.required}")
	public String EXTERNAL_USER_PASSWORD_REQUIRED;
	
	@Value("${external.user.password.not.matching}")
	public String EXTERNAL_USER_PASSWORD_NOT_MATCHING;
	
	@Value("${external.user.password.length}")
	public String EXTERNAL_USER_PASSWORD_LENGTH;
	
	@Value("${external.user.mobile.required}")
	public String EXTERNAL_USER_MOBILE_REQUIRED;
	
	@Value("${external.user.mobile.invalid}")
	public String EXTERNAL_USER_MOBILE_INVALID;
	
	@Value("${external.user.house.required}")
	public String EXTERNAL_USER_HOUSE_REQUIRED;
	
	@Value("${external.user.address.required}")
	public String EXTERNAL_USER_ADDRESS_REQUIRED;
	
	@Value("${external.user.city.required}")
	public String EXTERNAL_USER_CITY_REQUIRED;
	
	@Value("${external.user.state.required}")
	public String EXTERNAL_USER_STATE_REQUIRED;
	
	@Value("${external.user.pinCode.required}")
	public String EXTERNAL_USER_PINCODE_REQUIRED;
	
	

}

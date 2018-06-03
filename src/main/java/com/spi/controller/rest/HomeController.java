package com.spi.controller.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spi.service.UserService;
import com.spi.service.dto.LoginRequest;
import com.spi.service.dto.User;
import com.spi.validator.UserSignupValidator;

@RestController
@RequestMapping(path="/rest")
public class HomeController {
	
	private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserSignupValidator userValidator;
	
	@PostMapping(path="/signup")
	public void signUp(@RequestBody User externalUser) throws Exception {
		LOG.info("Inside user signup {}" + externalUser.toString());
		userValidator.validateUser(externalUser);
		userService.signUp(externalUser);
	}
	

}

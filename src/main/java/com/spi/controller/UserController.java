package com.spi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spi.service.UserService;
import com.spi.service.dto.User;
import com.spi.validator.UserSignupValidator;

@RestController
@RequestMapping(path="/user")
public class UserController {
	
	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserSignupValidator val;
	
	@PostMapping(path="/signup")
	public void signUp(@RequestBody User externalUser) {
		LOG.info("Inside user signup {}" + externalUser.toString());
		val.validate(externalUser);
		userService.signUp(externalUser);
	}
	
	@PostMapping(path="/login")
	public void login() {
		userService.login();
	}

}

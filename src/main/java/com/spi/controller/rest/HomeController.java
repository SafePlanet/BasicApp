package com.spi.controller.rest;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spi.dm.LoginRequest;
import com.spi.dm.User;
import com.spi.service.UserService;
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
	public void signUp(@Valid @RequestBody User externalUser) throws Exception {
		LOG.info("Inside user signup {}" + externalUser.toString());
//		userValidator.validateUser(externalUser);
		userService.signUp(externalUser);
	}
	

}

package com.spi.controller;

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
	
	
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserSignupValidator val;
	
	@PostMapping(path="/signup")
	public void signUp(@RequestBody User externalUser) {

		val.validate(externalUser);
		userService.signUp(externalUser);
	}
	
	@PostMapping(path="/login")
	public void login() {
		userService.login();
	}

}

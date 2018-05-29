package com.spi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spi.service.UserService;
import com.spi.service.dto.ExternalUser;

@RestController
@RequestMapping(path="/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping(path="/signup")
	public void signUp(@RequestBody ExternalUser externalUser) {
		userService.signUp(externalUser);
	}
	
	@PostMapping(path="/login")
	public void login() {
		userService.login();
	}

}

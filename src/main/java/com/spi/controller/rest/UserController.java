package com.spi.controller.rest;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spi.dm.LoginRequest;
import com.spi.dm.User;
import com.spi.service.UserService;
import com.spi.validator.UserSignupValidator;

@RestController
@RequestMapping(path="/rest/user")
public class UserController {
	
	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserSignupValidator userValidator;
	
	@GetMapping
	@PreAuthorize(value="hasRole('anonymous')")
	public List<User> getAllUsers(Principal principal){
		LOG.debug("getAllUsers " + principal.toString());
		return userService.getAllUsers();
	}
	

}

package com.spi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spi.repository.UserRepository;
import com.spi.service.dto.Role;
import com.spi.service.dto.User;

@Service
public class UserService {
	
	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	UserRepository userRepository;
	
	public void signUp(User user) {
		user.setRole(Role.anonymous.toString());
		LOG.debug("processing user data for signup");
		userRepository.save(user);
	}
	
	public void login() {
		System.out.println("login");
	}

}

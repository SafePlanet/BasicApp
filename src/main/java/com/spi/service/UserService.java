package com.spi.service;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spi.repository.UserRepository;
import com.spi.service.dto.LoginRequest;
import com.spi.service.dto.Role;
import com.spi.service.dto.User;
import com.spi.utils.PasswordUtil;

@Service
public class UserService {
	
	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordUtil passwordUtil;
	
	public void signUp(User user) throws Exception {
		user.setRole(Role.anonymous.toString());
		LOG.debug("processing user data for signup");
		user.setUuid(UUID.randomUUID().toString());
		user.setPassword(passwordUtil.hashPassword(user.getPassword(), user.getUuid()));
		userRepository.save(user);
	}
	
	public void login(LoginRequest loginRequest) throws Exception {
		System.out.println("login");
		User user = userRepository.findByEmailAddress(loginRequest.getUsername());
		String hashedPassword = passwordUtil.hashPassword(loginRequest.getPassword(), user.getUuid());
		if(user.getPassword().equals(hashedPassword)) {
			System.out.println("loging sucess");
		}
	}

}

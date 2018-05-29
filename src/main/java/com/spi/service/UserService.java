package com.spi.service;

import org.springframework.stereotype.Service;

import com.spi.service.dto.ExternalUser;

@Service
public class UserService {
	
	public void signUp(ExternalUser externalUser) {
		System.out.println("Signup");
	}
	
	public void login() {
		System.out.println("login");
	}

}

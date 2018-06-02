package com.spi.service;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.spi.config.ValidationMessageConfig;
import com.spi.exception.ValidationMessageException;
import com.spi.mail.EmailService;
import com.spi.mail.Mail;
import com.spi.repository.UserRepository;
import com.spi.service.dto.LoginRequest;
import com.spi.service.dto.Role;
import com.spi.service.dto.User;
import com.spi.utils.PasswordUtil;

import freemarker.template.TemplateException;

@Service(value = "userService")
public class UserService implements UserDetailsService{

	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder encoder; 
	
	@Autowired
	private EmailService emailService;

	@Value("${password.attempt.limit}")
	private String passwordAttemptLimit;

	public void signUp(User user) throws Exception {
		user.setRole(Role.anonymous.toString());
		LOG.debug("processing user data for signup");
		user.setPassword(encoder.encode(user.getPassword()));
		userRepository.save(user);
		sendWelcomeEmail();
	}
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if(user == null){
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		
		return user;
	}
	
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	public void sendWelcomeEmail() throws MessagingException, IOException, TemplateException {
		Mail mail = new Mail();
        mail.setFrom("no-reply@memorynotfound.com");
        mail.setTo("info@memorynotfound.com");
        mail.setSubject("Sending Email with Freemarker HTML Template Example");

        Map<String, String> model = new HashMap<>();
        model.put("name", "Memorynotfound.com");
        model.put("location", "Belgium");
        model.put("signature", "https://memorynotfound.com");
        mail.setModel(model);

        emailService.sendWelcomeAuthEmail(mail);
	}

}

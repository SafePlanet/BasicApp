package com.spi.service;

import java.io.IOException;
import java.security.SecureRandom;
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
	private PasswordUtil passwordUtil;
	
	@Autowired
	private PasswordEncoder encoder; 
	
	@Autowired
	private EmailService emailService;

	@Autowired
	private ValidationMessageConfig validationMessageConfig;
	
	@Value("${password.attempt.limit}")
	private String passwordAttemptLimit;

	public void signUp(User user) throws Exception {
		user.setRole(Role.anonymous.toString());
		LOG.debug("processing user data for signup");
		user.setUuid(UUID.randomUUID().toString());
		user.setPassword(encoder.encode(user.getPassword()));
		userRepository.save(user);
		sendEmail();
	}
	
	public void login(LoginRequest loginRequest) throws Exception {
		System.out.println("login");
		User user = userRepository.findByEmailAddress(loginRequest.getUsername());
		if (encoder.matches(loginRequest.getPassword(), user.getPassword())) {
			System.out.println("loging sucess");
		} else if (user.getPasswordAttempts() < Integer.parseInt(passwordAttemptLimit)) {
			user.incrementPasswordAttempt();
			userRepository.save(user);
		} else {
			throw new ValidationMessageException(validationMessageConfig.PASSWORD_ATTEMPT_LIMIT);
		}

	}
	
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(userId);
		if(user == null){
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority());
	}
	
	private List<SimpleGrantedAuthority> getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}
	
	public void sendEmail() throws MessagingException, IOException, TemplateException {
		Mail mail = new Mail();
        mail.setFrom("no-reply@memorynotfound.com");
        mail.setTo("info@memorynotfound.com");
        mail.setSubject("Sending Email with Freemarker HTML Template Example");

        Map<String, String> model = new HashMap<>();
        model.put("name", "Memorynotfound.com");
        model.put("location", "Belgium");
        model.put("signature", "https://memorynotfound.com");
        mail.setModel(model);

        emailService.sendSimpleMessage(mail);
	}

}

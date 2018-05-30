package com.spi.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

@Service
public class UserService {

	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordUtil passwordUtil;
	
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
		user.setPassword(passwordUtil.hashPassword(user.getPassword(), user.getUuid()));
		userRepository.save(user);
		sendEmail();
	}

	public void login(LoginRequest loginRequest) throws Exception {
		System.out.println("login");
		User user = userRepository.findByEmailAddress(loginRequest.getUsername());
		String hashedPassword = passwordUtil.hashPassword(loginRequest.getPassword(), user.getUuid());
		if (user.getPassword().equals(hashedPassword)) {
			
			System.out.println("loging sucess");
		} else if (user.getPasswordAttempts() < Integer.parseInt(passwordAttemptLimit)) {
			user.incrementPasswordAttempt();
			userRepository.save(user);
		} else {
			throw new ValidationMessageException(validationMessageConfig.PASSWORD_ATTEMPT_LIMIT);
		}

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

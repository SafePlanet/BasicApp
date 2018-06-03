package com.spi.social;


import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UserProfile;
import org.springframework.stereotype.Component;

import com.spi.form.AppUserForm;
import com.spi.repository.UserRepository;
import com.spi.service.dto.Role;
import com.spi.service.dto.User;

@Component
public class ConnectionSignUpImpl implements ConnectionSignUp {
 
    
	@Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
	@Autowired
    private UserRepository userRepository;
    
    // After logging in social networking.
    // This method will be called to create a corresponding App_User record
    // if it does not already exist.
    @Override
    public String execute(Connection<?> connection) {
 
        User account = createAppUser(connection);
        return account.getUsername();
    }
    
 // Auto create App User Account.
    public User createAppUser(Connection<?> connection) {
  
        ConnectionKey key = connection.getKey();
        // (facebook,12345), (google,123) ...
  
        System.out.println("key= (" + key.getProviderId() + "," + key.getProviderUserId() + ")");
  
        UserProfile userProfile = connection.fetchUserProfile();
  
        String email = userProfile.getEmail();
        User appUser = userRepository.findByEmailAddress(email);
        if (appUser != null) {
            return appUser;
        }
//        String userName_prefix = userProfile.getFirstName().trim().toLowerCase()//
//                + "_" + userProfile.getLastName().trim().toLowerCase();
//  
//        String userName = this.findAvailableUserName(userName_prefix);
        //
        // Random Password! TODO: Need send email to User!
        //
        String randomPassword = UUID.randomUUID().toString().substring(0, 5);
        String encrytedPassword = passwordEncoder.encode(randomPassword);
        //
        appUser = new User();
//        appUser.setEnabled(true);
        appUser.setPassword(encrytedPassword);
        appUser.setUsername(email);
        appUser.setEmailAddress(email);
        appUser.setFirstName(userProfile.getFirstName());
        appUser.setLastName(userProfile.getLastName());
        appUser.setRole(Role.user.toString());
  
        appUser = userRepository.save(appUser);
  
        return appUser;
    }
  
    public User registerNewUserAccount(AppUserForm appUserForm, List<String> roleNames) {
        User appUser = new User();
        appUser.setUsername(appUserForm.getUserName());
        appUser.setEmailAddress(appUserForm.getEmail());
        appUser.setFirstName(appUserForm.getFirstName());
        appUser.setLastName(appUserForm.getLastName());
//        appUser.setEnabled(true);
        appUser.setPassword(passwordEncoder.encode(appUserForm.getPassword()));
  
        return appUser;
    }

 
}
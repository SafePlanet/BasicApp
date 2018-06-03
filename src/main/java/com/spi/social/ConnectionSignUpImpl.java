package com.spi.social;


import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Component;

import com.spi.form.AppUserForm;
import com.spi.repository.UserRepository;
import com.spi.service.dto.Role;
import com.spi.service.dto.SocialTypes;
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
 
        User account = createSocialUser(connection);
        return account.getUsername();
    }
    
 // Auto create App User Account.
    public User createSocialUser(Connection<?> connection) {
    	  
        ConnectionKey key = connection.getKey();
  
        System.out.println("key= (" + key.getProviderId() + "," + key.getProviderUserId() + ")");
        User socialUser;
        if(SocialTypes.facebook.toString().equalsIgnoreCase(key.getProviderId())) {
        	Facebook facebook = (Facebook) connection.getApi();
            String [] fields = { "id", "about", "age_range", "birthday", "context", "cover", "currency", "devices", "education", "email", "favorite_athletes", 
            		"favorite_teams", "first_name", "gender", "hometown", "inspirational_people", "installed", "install_type", "is_verified", "languages", "last_name", 
            		"link", "locale", "location", "meeting_for", "middle_name", "name", "name_format", "political", "quotes", "payment_pricepoints", "relationship_status", 
            		"religion", "security_settings", "significant_other", "sports", "test_group", "timezone", "third_party_id", "updated_time", "verified", 
            		"video_upload_limits", "viewer_can_send_gift", "website", "work" };
            
            org.springframework.social.facebook.api.User userProfile = facebook.fetchObject("me", org.springframework.social.facebook.api.User.class, fields);
            String email = userProfile.getEmail();
            socialUser = userRepository.findByEmailAddress(email);
            if (socialUser != null) {
                return socialUser;
            }

            String randomPassword = UUID.randomUUID().toString().substring(0, 5);
            String encrytedPassword = passwordEncoder.encode(randomPassword);
            socialUser = new User();
            socialUser.setPassword(encrytedPassword);
            socialUser.setUsername(email);
            socialUser.setEmailAddress(email);
            socialUser.setFirstName(userProfile.getFirstName());
            socialUser.setLastName(userProfile.getLastName());
            socialUser.setRole(Role.user.toString());
            
        } else {
        	UserProfile userProfile = connection.fetchUserProfile();
        	String email = userProfile.getEmail();
            socialUser = userRepository.findByEmailAddress(email);
            if (socialUser != null) {
                return socialUser;
            }
            String randomPassword = UUID.randomUUID().toString().substring(0, 5);
            String encrytedPassword = passwordEncoder.encode(randomPassword);
            //
            socialUser = new User();
            socialUser.setPassword(encrytedPassword);
            socialUser.setUsername(email);
            socialUser.setEmailAddress(email);
            socialUser.setFirstName(userProfile.getFirstName());
            socialUser.setLastName(userProfile.getLastName());
            socialUser.setRole(Role.user.toString());
        }
        socialUser.setSource(key.getProviderId());
        
        socialUser = userRepository.save(socialUser);
  
        return socialUser;
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
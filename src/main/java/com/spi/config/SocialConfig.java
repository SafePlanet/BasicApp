package com.spi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.mongo.ConnectionConverter;
import org.springframework.social.connect.mongo.MongoConnectionService;
import org.springframework.social.connect.mongo.MongoUsersConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.linkedin.connect.LinkedInConnectionFactory;
import org.springframework.social.security.AuthenticationNameUserIdSource;
import org.springframework.social.twitter.connect.TwitterConnectionFactory;

import com.mongodb.MongoClient;
import com.spi.repository.UserRepository;
import com.spi.social.ConnectionSignUpImpl;
  
@Configuration
@EnableSocial
// Load to Environment.
@PropertySource("classpath:social-cfg.properties")
public class SocialConfig implements SocialConfigurer {
  
    private boolean autoSignUp = false;
  
    @Autowired
    private MongoTemplate mongoTemplate;
    
    @Autowired
    private MongoConnectionService connectionService;
    
    @Autowired
    private ConnectionSignUpImpl connectionSignUpImpl;
    
    @Autowired
    private ConnectionConverter connectionConverter; 
  
    // @env: read from social-cfg.properties file.
    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer cfConfig, Environment env) {
  
        try {
            this.autoSignUp = Boolean.parseBoolean(env.getProperty("social.auto-signup"));
        } catch (Exception e) {
            this.autoSignUp = false;
        }
         
        // Twitter
        TwitterConnectionFactory tfactory = new TwitterConnectionFactory(//
                env.getProperty("twitter.consumer.key"), //
                env.getProperty("twitter.consumer.secret"));
  
        // tfactory.setScope(env.getProperty("twitter.scope"));
  
        cfConfig.addConnectionFactory(tfactory);
  
        // Facebook
        FacebookConnectionFactory ffactory = new FacebookConnectionFactory(//
                env.getProperty("facebook.app.id"), //
                env.getProperty("facebook.app.secret"));
  
        ffactory.setScope(env.getProperty("facebook.scope"));
  
        // auth_type=reauthenticate
  
        cfConfig.addConnectionFactory(ffactory);
  
        // Linkedin
        LinkedInConnectionFactory lfactory = new LinkedInConnectionFactory(//
                env.getProperty("linkedin.consumer.key"), //
                env.getProperty("linkedin.consumer.secret"));
  
        lfactory.setScope(env.getProperty("linkedin.scope"));
  
        cfConfig.addConnectionFactory(lfactory);
  
        // Google
        GoogleConnectionFactory gfactory = new GoogleConnectionFactory(//
                env.getProperty("google.client.id"), //
                env.getProperty("google.client.secret"));
  
        gfactory.setScope(env.getProperty("google.scope"));
  
        cfConfig.addConnectionFactory(gfactory);
    }
  
    // The UserIdSource determines the userID of the user.
    @Override
    public UserIdSource getUserIdSource() {
        return new AuthenticationNameUserIdSource();
    }
    
    // USERCONNECTION.
    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
  
        // org.springframework.social.security.SocialAuthenticationServiceRegistry
    	MongoUsersConnectionRepository usersConnectionRepository = new MongoUsersConnectionRepository(connectionService,
                connectionFactoryLocator,
  
                Encryptors.noOpText());
  
        if (autoSignUp) {
            // After logging in to social networking.
            // Automatically creates corresponding APP_USER if it does not exist.
//        	ConnectionSignUpImpl connectionSignUp = new ConnectionSignUpImpl(userRepository, passwordEncoder);
            usersConnectionRepository.setConnectionSignUp(connectionSignUpImpl);
        } else {
            // After logging in to social networking.
            // If the corresponding APP_USER record is not found.
            // Navigate to registration page.
            usersConnectionRepository.setConnectionSignUp(null);
        }
        return usersConnectionRepository;
    }
  
    // This bean manages the connection flow between the account provider
    // and the example application.
    @Bean
    public ConnectController connectController(ConnectionFactoryLocator connectionFactoryLocator, //
            ConnectionRepository connectionRepository) {
        return new ConnectController(connectionFactoryLocator, connectionRepository);
    }
    
    @Bean
    public TextEncryptor getMongoConnectionService() {
    	
    	return Encryptors.noOpText();
    }
    
    @Bean
    public SignInAdapter authSignInAdapter() {
        return (userId, connection, request) -> {
            authenticate(connection);
            return null;
        };
    }
    
    public void authenticate(Connection<?> connection) {
        UserProfile userProfile = connection.fetchUserProfile();
        String username = userProfile.getUsername();
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, null, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
//        log.info("User {} {} connected.", userProfile.getFirstName(), userProfile.getLastName());
    }
    
//    @Bean
//    public MongoDbFactory mongoDbFactory() throws Exception {
//        MongoClient mongoClient = new MongoClient("localhost", 27017);
//        return new SimpleMongoDbFactory(mongoClient, "technicalkeeda");
//    }
 
//    @Bean
//    public MongoTemplate mongoTemplate() throws Exception {
//        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
//        return mongoTemplate;
//    }
  
}
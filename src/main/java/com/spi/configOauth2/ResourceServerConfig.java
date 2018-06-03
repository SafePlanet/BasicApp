package com.spi.configOauth2;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.social.security.SpringSocialConfigurer;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	private static final String RESOURCE_ID = "resource_id";
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.resourceId(RESOURCE_ID).stateless(false);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
//        http.
//                anonymous().disable()
//                .authorizeRequests()
//                .antMatchers("/user/**").access("hasRole('ADMIN')")
//                .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
        
//        http
//        .formLogin().disable() // disable form authentication
//        .anonymous().disable() // disable anonymous user
//        .httpBasic().and()
//        // restricting access to authenticated users
//        .authorizeRequests().anyRequest().authenticated();
        
        
     // Pages do not require login
        http.authorizeRequests().antMatchers("/", "/signup", "/login", "/logout").permitAll();
 
        http.authorizeRequests().antMatchers("/userInfo").access("hasRole('ROLE_USER')");
 
        // For ADMIN only.
        http.authorizeRequests().antMatchers("/admin").access("hasRole('ROLE_ADMIN')");
 
        // When the user has logged in as XX.
        // But access a page that requires role YY,
        // AccessDeniedException will be thrown.
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
 
        // Form Login config
        http.authorizeRequests().and().formLogin()//
                // Submit URL of login page.
                .loginProcessingUrl("/j_spring_security_check") // Submit URL
                .loginPage("/login")//
                .defaultSuccessUrl("/userInfo")//
                .failureUrl("/login?error=true")//
                .usernameParameter("username")//
                .passwordParameter("password");
 
        // Logout Config
//        http.authorizeRequests().and().logout().logoutUrl("/logout").logoutSuccessUrl("/");
 
        // Spring Social Config.
        http.apply(new SpringSocialConfigurer()).signupUrl("/signup");
        
        
	}

}
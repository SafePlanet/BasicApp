
package com.spi.dm;

import java.util.Arrays;
import java.util.Collection;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.security.SocialUserDetails;


@Document
@Configuration
public class User implements UserDetails, SocialUserDetails{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7131770305496579495L;
	
	@Id
	private String id;
	@NotBlank(message="{external.user.fisrtname.required}")
	private String firstName;
	@NotBlank(message="{external.user.lastname.required}")
	private String lastName;
	
	@Indexed(unique=true)
	@Email(message= "{external.user.email.invalid}")
	@NotBlank(message="{external.user.email.required}")
	private String emailAddress;
	
	@NotBlank(message = "{external.user.username.required}")
	private String username;
		
	@NotBlank(message = "{external.user.password.required}")
	@Size(min=8, max=30, message = "{external.user.password.length}")
	private String password;
	
	private int passwordAttempts;
	private boolean isVerified;
	private String role;
	
	@NotBlank(message = "{external.user.mobile.required}")
	private String mobile;
	
	private Address address;
	private String userImage;
	private String source;
	
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public boolean isVerified() {
		return isVerified;
	}

	public void setVerified(boolean isVerified) {
		this.isVerified = isVerified;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getUserImage() {
		return userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	public int getPasswordAttempts() {
		return passwordAttempts;
	}

	public void setPasswordAttempts(int passwordAttempts) {
		this.passwordAttempts = passwordAttempts;
	}

	public void incrementPasswordAttempt() {
		this.passwordAttempts++;
	}
	
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + ", emailAddress=" + emailAddress + ", role=" + role + 
				", mobile=" + mobile + ", userName=" + username + "]"; 
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_"+role));
	}

	@Override
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String getUserId() {
		
		return username;
	}
	

}

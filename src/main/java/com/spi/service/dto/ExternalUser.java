/* Copyright (C) SafePlanet Innovations, LLP - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by SafePlanet Innovations <spinnovations.india@gmail.com>, October 2015
 */
package com.spi.service.dto;

import java.security.Principal;

import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import com.spi.exception.ValidationException;

/**
 *
 * @author:
 */
@XmlRootElement
public class ExternalUser implements Principal {

	private String id;

	private boolean app;

	public boolean isApp() {
		return app;
	}

	public void setApp(boolean app) {
		this.app = app;
	}

	@Length(max = 50)
	private String firstName;
	@Length(max = 50)
	private String lastName;

	@Email(message = "{email.invalid}")
	private String emailAddress;

	private Long pkId;

	private boolean isVerified;

	private String role;

	private long userId;
	private String mobile;
	private String houseNo;
	private String address;
	private String city;
	private String state;
	private String pinCode;

	private String userImage;

	private long routeId;

//	private List<SocialProfile> socialProfiles = new ArrayList<SocialProfile>();

	public ExternalUser() {
	}

	public ExternalUser(String userId) {
		this.id = userId;
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

	public String getId() {
		return id;
	}

	public boolean isVerified() {
		return isVerified;
	}

	public String getName() {
		return emailAddress;
	}

	public String getRole() {
		return role;
	}

	public Long getPkId() {
		return pkId;
	}

	public void setPkId(Long pkId) {
		this.pkId = pkId;
	}

	public String getMobile() {
		try {

			// Long.parseLong(this.mobile);
			return this.mobile;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ValidationException("Invalid Mobile Number.", "mobile");
		}
	}

	public void setMobile(String mobile) {
		// this.mobile = mobile;
		try {

			// Long.parseLong(this.mobile);
			this.mobile = mobile;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ValidationException("Invalid Mobile Number.", "mobile");
		}
	}

	public String getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pin) {
		this.pinCode = pin;
	}

	public long getRouteId() {
		return routeId;
	}

	public void setRouteId(long routeId) {
		this.routeId = routeId;
	}

	public String getUserImage() {
		return userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

}

package com.spi.dm;

import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Length;

/**
 *
 * @version 1.0
 * @author:
 * 
 */
@XmlRootElement
public class PasswordRequest {

	@Length(min = 8, max = 30, message="{password.length}")
	private String password;

	public PasswordRequest() {
	}

	public PasswordRequest(final String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}

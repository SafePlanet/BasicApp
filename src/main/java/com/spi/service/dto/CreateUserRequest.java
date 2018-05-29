
package com.spi.service.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * @author:
 */
@XmlRootElement
public class CreateUserRequest {

	@NotNull
	@Valid
	private User user;

	@NotNull
	@Valid
	private PasswordRequest password;

	private AddressVM address;

	public CreateUserRequest() {
	}

	public CreateUserRequest(final User user,
			final PasswordRequest password) {
		this.user = user;
		this.password = password;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public PasswordRequest getPassword() {
		return password;
	}

	public void setPassword(PasswordRequest password) {
		this.password = password;
	}

	/**
	 * @return the address
	 */
	public AddressVM getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(AddressVM address) {
		this.address = address;
	}

}

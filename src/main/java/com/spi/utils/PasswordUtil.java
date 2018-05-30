package com.spi.utils;

import java.security.MessageDigest;

import org.springframework.stereotype.Component;


@Component
public class PasswordUtil {

	/**
	 * Add additional salt to password hashing
	 */
	private static final String HASH_SALT = "01928374-kajs-orit-yetr-lqaoskdnchfz";
	private static final int HASH_ITERATIONS = 1000;
	/**
	 * Hash the password using salt values See
	 * https://www.owasp.org/index.php/Hashing_Java
	 *
	 * @param passwordToHash
	 * @return hashed password
	 */
	public String hashPassword(String passwordToHash, String uuuid) throws Exception {
		return hashToken(passwordToHash, uuuid + HASH_SALT);
	}

	private String hashToken(String token, String salt) throws Exception {
		return HashUtil.byteToBase64(getHash(HASH_ITERATIONS, token, salt.getBytes()));
	}

	public byte[] getHash(int numberOfIterations, String password, byte[] salt) throws Exception {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		digest.reset();
		digest.update(salt);
		byte[] input = digest.digest(password.toString().getBytes("UTF-8"));
		for (int i = 0; i < numberOfIterations; i++) {
			digest.reset();
			input = digest.digest(input);
		}
		return input;
	}

}

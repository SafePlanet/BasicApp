package com.spi.utils;

import java.security.SecureRandom;
import java.util.Date;

import org.springframework.security.core.token.SecureRandomFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtil {
	
	public static void main(String[] args) {
		SecureRandom random =  new SecureRandom();
		BCryptPasswordEncoder bCryptPasswordEncoder = null;
		bCryptPasswordEncoder = new BCryptPasswordEncoder(11, random);
		String hashedPassword = bCryptPasswordEncoder.encode("3JSk1KYPM9nAx-ji1");
		System.out.print("hashedPassword = " + hashedPassword);
		System.out.println(" " + bCryptPasswordEncoder.matches("Password", hashedPassword));
		
	}
	
	public static void mainseed() {
		SecureRandom random =  new SecureRandom();
		for (int i = 0; i <10; i++) {
			Date firstTime = new Date();
			byte[] c = random.generateSeed(1);
			Date secondTime = new Date();
			Long timediff = secondTime.getTime() - firstTime.getTime();
			System.out.println(i + ". Time = " + timediff + " " +c.toString());
			
		}
		
	}
	

}

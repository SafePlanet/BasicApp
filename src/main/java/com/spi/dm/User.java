package com.spi.dm;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
	
	private String name;
	private String studentName;
	private String emailAddress;
	private boolean isVerified;
	private String mobileNumber;
	private String routeName;
	private int isEnable;
	private String address;
	private String city;
	private String state;
	private long stateId;

}

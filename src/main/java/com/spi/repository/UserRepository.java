package com.spi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.spi.dm.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

	public User findByEmailAddress(String emailAddress);
	
	public User findByUsername(String userName);

}

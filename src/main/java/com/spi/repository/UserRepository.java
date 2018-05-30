package com.spi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.spi.service.dto.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

	public User findByEmailAddress(String emailAddress);

}

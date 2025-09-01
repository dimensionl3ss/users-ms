package com.adarshcare.users.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.adarshcare.users.model.User;

// Extend with MongoRepository
public interface UserRepository extends MongoRepository<User, String> {
	Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}

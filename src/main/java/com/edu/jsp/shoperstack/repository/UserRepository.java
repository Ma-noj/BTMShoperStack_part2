package com.edu.jsp.shoperstack.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edu.jsp.shoperstack.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	// To Find the User details based on the Email and Password
	Optional<User> findByUserEmailAndUserPassword(String userEmail, String userPassword);

	// To Find the User details based on the Email
	Optional<User> findByUserEmail(String userEmail);

}

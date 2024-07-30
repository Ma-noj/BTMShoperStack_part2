package com.edu.jsp.shoperstack.dao;

import java.util.List;
import java.util.Optional;

import com.edu.jsp.shoperstack.entity.User;

public interface UserDao {

	User saveUser(User user);

	Optional<User> findById(Integer userId);

	List<User> findAll();

	void deleteById(Integer userId);

	Boolean isPresent(Integer userId);
}

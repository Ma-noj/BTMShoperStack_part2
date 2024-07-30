package com.edu.jsp.shoperstack.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edu.jsp.shoperstack.dao.UserDao;
import com.edu.jsp.shoperstack.entity.User;
import com.edu.jsp.shoperstack.repository.UserRepository;

@Repository
public class UserDaoImpl implements UserDao {
	@Autowired
	private UserRepository useRepository;

	@Override
	public User saveUser(User user) {
		return useRepository.save(user);
	}

	@Override
	public Optional<User> findById(Integer userId) {
		return useRepository.findById(userId);
	}

	@Override
	public List<User> findAll() {
		return useRepository.findAll();
	}

	@Override
	public void deleteById(Integer userId) {
		useRepository.deleteById(userId);
	}

	@Override
	public Boolean isPresent(Integer userId) {
		return useRepository.existsById(userId);
	}

}

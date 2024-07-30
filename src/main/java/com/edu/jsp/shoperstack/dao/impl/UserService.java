package com.edu.jsp.shoperstack.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.edu.jsp.shoperstack.dao.UserDao;
import com.edu.jsp.shoperstack.entity.User;
import com.edu.jsp.shoperstack.exception.UserNotFoundException;
import com.edu.jsp.shoperstack.util.ResponseStructure;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;

	public ResponseEntity<ResponseStructure<User>> saveUser(User user) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		structure.setData(userDao.saveUser(user));
		structure.setMessage("Created");
		return new ResponseEntity<>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<User>> findById(Integer userId) {
		Optional<User> optional = userDao.findById(userId);
		if (optional.isPresent()) {
			ResponseStructure<User> structure = new ResponseStructure<>();
			structure.setData(optional.get());
			structure.setMessage("Found");
			return new ResponseEntity<>(structure, HttpStatus.OK);
		}
		throw new UserNotFoundException("User wqith the Given Id = " + userId + " Not Found");
	}

	public ResponseEntity<ResponseStructure<List<User>>> findAll() {
		ResponseStructure<List<User>> structure = new ResponseStructure<>();
		structure.setData(userDao.findAll());
		structure.setMessage("Found");
		return new ResponseEntity<>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<String>> deleteById(Integer userId) {
		if (userDao.isPresent(userId)) {
			userDao.deleteById(userId);
			ResponseStructure<String> structure = new ResponseStructure<>();
			structure.setMessage("Deleted");
			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NO_CONTENT);
		}
		throw new UserNotFoundException("User wqith the Given Id = " + userId + " Not Found");
	}
}

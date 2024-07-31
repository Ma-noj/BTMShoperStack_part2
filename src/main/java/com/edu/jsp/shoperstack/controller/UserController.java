package com.edu.jsp.shoperstack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.jsp.shoperstack.entity.User;
import com.edu.jsp.shoperstack.service.UserService;
import com.edu.jsp.shoperstack.util.ResponseStructure;

@RestController
@RequestMapping("/api/version/users")
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping("/save")
	public ResponseEntity<ResponseStructure<User>> saveUser(@RequestBody User user) {
		return userService.saveUser(user);
	}
}

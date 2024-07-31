package com.edu.jsp.shoperstack.configuration.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.edu.jsp.shoperstack.entity.User;
import com.edu.jsp.shoperstack.exception.UserNotFoundException;
import com.edu.jsp.shoperstack.repository.UserRepository;

@Configuration
public class ApplicationUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> optional = repository.findByUserEmail(username);
		if (optional.isPresent()) {
			User user = optional.get();
			return org.springframework.security.core.userdetails.User.builder().username(user.getUserEmail())
					.password(user.getUserPassword()).roles(user.getRole()).build();
		}
		throw new UserNotFoundException("Invalid Login Info");
	}

}

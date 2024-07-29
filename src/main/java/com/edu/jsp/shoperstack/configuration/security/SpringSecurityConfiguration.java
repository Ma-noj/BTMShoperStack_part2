package com.edu.jsp.shoperstack.configuration.security;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {
	@SuppressWarnings("removal")
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

		// to Authenticate all the Request

		httpSecurity.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());

		// If request is not Authenticate provide some page

		httpSecurity.httpBasic(withDefaults());

		// Enable the CSRF

		httpSecurity.csrf().disable();

		return httpSecurity.build();
	}
}

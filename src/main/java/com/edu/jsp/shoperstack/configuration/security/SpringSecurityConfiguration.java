package com.edu.jsp.shoperstack.configuration.security;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration {
	@Autowired
	private ApplicationUserDetailsService userDetailsService;

	private static final String[] PUBLIC_URL = { "/api/version/users/save", "/swagger-ui/index.html#/" };
	private static final String[] ADMIN_URL = { "/api/version/carts/findAll" };

	private static final String[] MERCHANT_URL = { "/api/version/products/update", "/api/version/products/save",
			"/api/version/products/updatePrice", "/api/version/products/delete" };
	private static final String[] CUSTOMER_URL = { "/api/version/carts/save", "/api/version/carts/addProduct",
			"/api/version/carts/findById", "/api/version/carts/delete" };

	private static final String[] MERCHANT_CUSTOMER_URL = { "/api/version/products/findById/{productId}",
			"/api/version/products/findAll" };

	@SuppressWarnings("removal")
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

		// to Authenticate all the Request

		httpSecurity.authorizeHttpRequests(auth -> {
			auth.requestMatchers(PUBLIC_URL).permitAll();
			auth.requestMatchers(ADMIN_URL).hasRole("ADMIN");
			auth.requestMatchers(MERCHANT_URL).hasRole("MERCHANT");
			auth.requestMatchers(CUSTOMER_URL).hasRole("CUSTOMER");
			auth.requestMatchers(MERCHANT_CUSTOMER_URL).hasAnyRole("MERCHANT", "CUSTOMER");

			auth.anyRequest().authenticated();
		});

		// If request is not Authenticate provide some page

		httpSecurity.httpBasic(withDefaults());

		// Enable the CSRF

		httpSecurity.csrf().disable();

		return httpSecurity.build();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}

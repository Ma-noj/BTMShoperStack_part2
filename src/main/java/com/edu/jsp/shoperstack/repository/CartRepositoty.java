package com.edu.jsp.shoperstack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edu.jsp.shoperstack.entity.Cart;

public interface CartRepositoty extends JpaRepository<Cart, Integer> {

}

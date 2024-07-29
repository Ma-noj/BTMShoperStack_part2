package com.edu.jsp.shoperstack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.edu.jsp.shoperstack.entity.Product;

import jakarta.transaction.Transactional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	@Transactional
	@Modifying
	@Query(value = "UPDATE Product p SET p.productPrice=?2 "
			+ "WHERE " + "p.productId = ?1")
	void updateProductPrice(int productId, double productPrice);

}

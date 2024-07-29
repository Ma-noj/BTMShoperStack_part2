package com.edu.jsp.shoperstack.dao;

import java.util.List;
import java.util.Optional;

import com.edu.jsp.shoperstack.entity.Product;

public interface ProductDao {
	Product saveProduct(Product product);

	Optional<Product> findById(int productId);

	List<Product> findAll();

	void deleteProduct(int productId);

	boolean isPresent(int productId);

	void updateProductPrice(int productId, double productPrice);
}

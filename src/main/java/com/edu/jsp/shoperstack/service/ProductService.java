package com.edu.jsp.shoperstack.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.edu.jsp.shoperstack.dao.ProductDao;
import com.edu.jsp.shoperstack.entity.Product;
import com.edu.jsp.shoperstack.exception.ProductNotFoundException;
import com.edu.jsp.shoperstack.util.ResponseStructure;

@Service
public class ProductService {
	@Autowired
	private ProductDao productDao;

	public ResponseEntity<ResponseStructure<Product>> saveProduct(Product product) {
		Product savedPrduct = productDao.saveProduct(product);
		ResponseStructure<Product> structure = new ResponseStructure<Product>();
		structure.setMessage("Created");
		structure.setData(savedPrduct);

		return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Product>> findById(int productId) {
		Optional<Product> optionalProduct = productDao.findById(productId);
		if (optionalProduct.isPresent()) {
			ResponseStructure<Product> structure = new ResponseStructure<Product>();
			structure.setMessage("Found");
			structure.setData(optionalProduct.get());
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.OK);
		}
		throw new ProductNotFoundException("Product Withe Given id = " + productId + " Not Found");
	}

	public ResponseEntity<ResponseStructure<List<Product>>> findAll() {
		ResponseStructure<List<Product>> structure = new ResponseStructure<List<Product>>();
		structure.setData(productDao.findAll());
		structure.setMessage("Found");
		return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<String>> 
	delete(int productId) {
		if (productDao.isPresent(productId)) {
			productDao.deleteProduct(productId);

			ResponseStructure<String> structure = new 
					ResponseStructure<String>();
			structure.setMessage("Product Deleted");

			return new ResponseEntity<ResponseStructure<String>>
			(structure, HttpStatus.NO_CONTENT);
		}

		throw new ProductNotFoundException("Product Not Found");
	}

	public ResponseEntity<ResponseStructure<Product>> updateProduct
	(int productId, Product product) {
		if (productDao.isPresent(productId)) {
			product.setProductId(productId);
			Product updatedProduct = productDao.saveProduct(product);
			ResponseStructure<Product> structure = new 
					ResponseStructure<Product>();
			structure.setData(updatedProduct);
			structure.setMessage("Updated");
			
			return new ResponseEntity<ResponseStructure<Product>>
			(structure,HttpStatus.OK);
		}
		throw new ProductNotFoundException("Product Not FOund");
	}

	public ResponseEntity<ResponseStructure<String>> updateProductprice
	(int productId, double productPrice) {
		if (productDao.isPresent(productId)) {
			productDao.updateProductPrice(productId,productPrice);
			
			ResponseStructure<String> structure = new 
					ResponseStructure<String>();
			structure.setMessage("Product Updated");

			return new ResponseEntity<ResponseStructure<String>>
			(structure, HttpStatus.OK);
		}
		throw new ProductNotFoundException("Product Not FOund");
	}
}

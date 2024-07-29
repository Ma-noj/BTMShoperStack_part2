package com.edu.jsp.shoperstack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edu.jsp.shoperstack.entity.Product;
import com.edu.jsp.shoperstack.service.ProductService;
import com.edu.jsp.shoperstack.util.ResponseStructure;

@RestController
@RequestMapping("/api/version/products")
public class ProdcutController {
	@Autowired
	private ProductService service;

	@PostMapping("/save")
	public ResponseEntity<ResponseStructure<Product>> saveProduct(@RequestBody Product product) {
		return service.saveProduct(product);
	}

	@GetMapping("/findById/{productId}")
	public ResponseEntity<ResponseStructure<Product>> findById(@PathVariable int productId) {
		return service.findById(productId);
	}

	@GetMapping("/findAll")
	public ResponseEntity<ResponseStructure<List<Product>>> findAll() {
		return service.findAll();
	}

	@DeleteMapping("/delete")
	public ResponseEntity<ResponseStructure<String>> delete(@RequestParam int productId) {
		return service.delete(productId);
	}

	@PutMapping("/update")
	public ResponseEntity<ResponseStructure<Product>> updateProduct(@RequestParam int productId,
			@RequestBody Product product) {
		return service.updateProduct(productId, product);
	}

	@PatchMapping("/updatePrice")
	public ResponseEntity<ResponseStructure<String>> updateProductPrice
	(@RequestParam int productId,@RequestParam double productPrice) {
		return service.updateProductprice(productId, productPrice);
	}

}

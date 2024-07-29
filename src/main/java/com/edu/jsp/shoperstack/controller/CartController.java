package com.edu.jsp.shoperstack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edu.jsp.shoperstack.entity.Cart;
import com.edu.jsp.shoperstack.service.CartService;
import com.edu.jsp.shoperstack.util.ResponseStructure;

@RestController
@RequestMapping("/api/version/carts")
public class CartController {
	@Autowired
	private CartService service;

	@PostMapping("/save")
	public ResponseEntity<ResponseStructure<Cart>> saveCart(@RequestBody Cart cart,
			@RequestParam(required = false, name = "productId") int productId) {
		return service.saveCart(cart, productId);
	}

	@GetMapping("/findAll")
	public ResponseEntity<ResponseStructure<List<Cart>>> findAll() {
		return service.findAll();
	}

	@GetMapping("/findById")
	public ResponseEntity<ResponseStructure<Cart>> findById(@RequestParam int cartId) {
		return service.findById(cartId);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<ResponseStructure<String>> delete(int cartId) {
		return service.delete(cartId);
	}

	@PostMapping("/addProduct")
	public ResponseEntity<ResponseStructure<Cart>> addProduct(@RequestParam int cartId,
			@RequestBody List<Integer> productIds) {
		return service.addProduct(cartId, productIds);
	}
	
	
}

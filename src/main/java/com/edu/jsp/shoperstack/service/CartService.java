package com.edu.jsp.shoperstack.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.edu.jsp.shoperstack.dao.CartDao;
import com.edu.jsp.shoperstack.dao.ProductDao;
import com.edu.jsp.shoperstack.entity.Cart;
import com.edu.jsp.shoperstack.entity.Product;
import com.edu.jsp.shoperstack.exception.CartNotFoundException;
import com.edu.jsp.shoperstack.exception.ProductNotFoundException;
import com.edu.jsp.shoperstack.util.ResponseStructure;

@Service
public class CartService {
	@Autowired
	private CartDao cartDao;
	@Autowired
	private ProductDao productDao;

	public ResponseEntity<ResponseStructure<Cart>> saveCart(Cart cart, int productId) {

		Optional<Product> optional = productDao.findById(productId);
		if (optional.isPresent()) {

			List<Product> listOfProducts = cart.getProducts();

			if (listOfProducts == null) {
				listOfProducts = new ArrayList<Product>();
			}

			listOfProducts.add(optional.get());

			cart.setProducts(listOfProducts);

		}

		cart = calculatePriceOfCart(cart);
		Cart savedCart = cartDao.saveCart(cart);

		ResponseStructure<Cart> structure = new ResponseStructure<Cart>();
		structure.setMessage("Created");
		structure.setData(savedCart);
		return new ResponseEntity<ResponseStructure<Cart>>(structure, HttpStatus.CREATED);
	}

	public Cart calculatePriceOfCart(Cart cart) {
		// Write a login to set the Number of Product and Total price
		if (cart.getProducts() != null) {

			// Calculate the Number of Product
			List<Product> listOfProducts = cart.getProducts();
			int numberOfProducts = listOfProducts.size();
			cart.setNumberOfPrducts(numberOfProducts);

			// Calculate the Total Price
			double totalPrice = 0.0;
			for (Product product : listOfProducts) {
				product.setCart(cart);
				totalPrice += product.getProductPrice();
			}
			cart.setTotalPrice(totalPrice);
		}
		return cart;
	}

	public ResponseEntity<ResponseStructure<List<Cart>>> findAll() {
		ResponseStructure<List<Cart>> structure = new ResponseStructure<>();
		structure.setMessage("Found");
		structure.setData(cartDao.findAll());
		return new ResponseEntity<ResponseStructure<List<Cart>>>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Cart>> findById(int cartId) {
		Optional<Cart> optional = cartDao.findById(cartId);
		if (optional.isPresent()) {
			ResponseStructure<Cart> structure = new ResponseStructure<>();
			structure.setMessage("Found");
			structure.setData(optional.get());
			return new ResponseEntity<ResponseStructure<Cart>>(structure, HttpStatus.OK);
		}
		throw new CartNotFoundException("Cart with the Given Id Not Found");
	}

	public ResponseEntity<ResponseStructure<String>> delete(int cartId) {
		if (cartDao.isPresent(cartId)) {
			cartDao.delete(cartId);
			ResponseStructure<String> structure = new ResponseStructure<>();
			structure.setMessage("Cart DELETED !!!");
			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NO_CONTENT);
		}
		throw new CartNotFoundException("Cart with the Given Id Not Found");
	}

	public ResponseEntity<ResponseStructure<Cart>> addProduct(int cartId, List<Integer> productIds) {
		Optional<Cart> optional = cartDao.findById(cartId);

		if (optional.isPresent()) {
			Cart cart = optional.get();

			if (cart.getProducts() == null) {
				cart.setProducts(new ArrayList<Product>());
			}

			for (Integer productId : productIds) {
				Optional<Product> optionalProduct = productDao.findById(productId);
				if (optionalProduct.isPresent()) {
					cart.getProducts().add(optionalProduct.get());
				} else {
					throw new ProductNotFoundException("Prodcut With the Given Id = " + productId + " is Not Present");
				}
			}

			cart = calculatePriceOfCart(cart);
			
			Cart modifiedCart = cartDao.saveCart(cart);

			ResponseStructure<Cart> structure = new ResponseStructure<>();
			structure.setMessage("Modified");
			structure.setData(modifiedCart);
			return new ResponseEntity<ResponseStructure<Cart>>(structure, HttpStatus.OK);

		}

		throw new CartNotFoundException("Cart with the Given Id Not Found");
	}
}

package com.edu.jsp.shoperstack.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "cart_info")
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cartId;
	private int numberOfPrducts;
	private double totalPrice;
	@CreationTimestamp
	@Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime creationDateTime;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cart")
	private List<Product> products;

}

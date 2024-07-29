package com.edu.jsp.shoperstack.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
@AllArgsConstructor
public class CartNotFoundException extends RuntimeException {
	private String message;
}

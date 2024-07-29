package com.edu.jsp.shoperstack.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseStructure<T> {
	private String message;
	private T data;
}

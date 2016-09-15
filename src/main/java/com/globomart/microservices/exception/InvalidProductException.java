package com.globomart.microservices.exception;

import com.globomart.microservices.resources.Product;

public class InvalidProductException extends Exception {

	private static final long serialVersionUID = 5236466576890963046L;

	private Product product;

	public InvalidProductException() {
		super();
	}

	public InvalidProductException(String message, Product product) {
		super(message);
		this.product = product;
	}

	public InvalidProductException(String message, Product product, Throwable cause) {
		super(message, cause);
		this.product = product;
	}

	@Override
	public String getMessage() {
		return super.getMessage() + "for Product" + product;
	}

	@Override
	public String toString() {
		return super.toString();
	}

}

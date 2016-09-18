package com.globomart.microservices.exception;

import com.globomart.microservices.object.Product;

public class ProductException extends Exception {

	private static final long serialVersionUID = 5236466576890963046L;

	private Product product;

	public ProductException(String message) {
		super(message);
	}

	public ProductException(String message, Product product) {
		super(message);
		this.product = product;
	}

	public ProductException(String message, Product product, Throwable cause) {
		super(message, cause);
		this.product = product;
	}

	@Override
	public String getMessage() {
		return super.getMessage() + ":" + product;
	}

	@Override
	public String toString() {
		return super.toString();
	}

}

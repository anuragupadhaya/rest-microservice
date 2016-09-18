package com.globomart.microservices.object;

public enum ProductType {
	GROCERY("grocery"), MEDICINE("medicine"), ELECTRONICS("electronics"), CLOTHING("clothing"), BOOKS("books");

	private final String name;

	private ProductType(String name) {
		this.name = name;
	}

	public String getType(String name) {
		return name;
	}
}

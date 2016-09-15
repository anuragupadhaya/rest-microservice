package com.globomart.microservices.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.globomart.microservices.exception.InvalidProductException;
import com.globomart.microservices.resources.Product;
import com.globomart.microservices.resources.ProductType;

public class ProductCatalogueService {
	private final static Logger logger = Logger.getLogger(ProductCatalogueService.class);

	private Set<Product> productCatalogue;

	private Map<ProductType, Set<Product>> mapProductType;

	public ProductCatalogueService(Set<Product> productCatalogue, Map<ProductType, Set<Product>> mapProductType) {
		this.productCatalogue = new HashSet<Product>();
		this.mapProductType = new HashMap<ProductType, Set<Product>>();
	}

	public void addProduct(Product product) {

		try {
			if (product == null) {
				throw new InvalidProductException("Invalid product", product);
			} else {
				productCatalogue.add(product);
			}
		} catch (InvalidProductException e) {
			logger.error(e.getMessage());
		}
	}

	public List<Product> getProducts(ProductType type) {
		
		

	}
}

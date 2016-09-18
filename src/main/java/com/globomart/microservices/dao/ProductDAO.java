package com.globomart.microservices.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.globomart.microservices.object.Product;
import com.globomart.microservices.object.ProductType;

public class ProductDAO {
	private Set<Product> productCatalogue;

	private Map<ProductType, List<Product>> mapProductType;

	public ProductDAO(Set<Product> productCatalogue, Map<ProductType, List<Product>> mapProductType) {
		this.productCatalogue = productCatalogue;
		this.mapProductType = mapProductType;
	}

	private void regenerateMapProductType() {
		mapProductType = new HashMap<ProductType, List<Product>>();
		List<Product> productList;
		for (Product p : productCatalogue) {
			if (!mapProductType.containsKey(p.getType())) {
				productList = new ArrayList<Product>();
				productList.add(p);
				mapProductType.put(p.getType(), productList);
			} else {
				productList = mapProductType.get(p.getType());
				productList.add(p);
				mapProductType.put(p.getType(), productList);
			}
		}

	}

	public void addProduct(Product product) {
		productCatalogue.add(product);
		regenerateMapProductType();
	}

	public List<Product> getProducts(ProductType type) {
		List<Product> productList = new ArrayList<Product>();

		for (ProductType productType : mapProductType.keySet()) {
			if (productType.equals(type)) {
				for (Product p : mapProductType.get(productType))
					productList.add(p);
			}
		}
		return productList;
	}

	public void removeProduct(Product p) {
		productCatalogue.remove(p);
		regenerateMapProductType();
	}

}

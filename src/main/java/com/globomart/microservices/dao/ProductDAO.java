package com.globomart.microservices.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.globomart.microservices.object.Product;
import com.globomart.microservices.object.ProductType;

public class ProductDAO {
	private Set<Product> productCatalogue;

	private Map<ProductType, Product> mapProductType;

	public ProductDAO(Set<Product> productCatalogue, Map<ProductType, Product> mapProductType) {
		this.productCatalogue = productCatalogue;
		this.mapProductType = mapProductType;
		regenerateMapProductType();
	}

	private void regenerateMapProductType() {
		for (Product p : productCatalogue) {
			mapProductType.put(p.getType(), p);
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
				productList.add(mapProductType.get(productType));
			}
		}
		return productList;
	}

	public void removeProduct(Product p) {
		productCatalogue.remove(p);
	}

}

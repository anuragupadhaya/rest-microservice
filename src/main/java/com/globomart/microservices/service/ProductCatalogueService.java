package com.globomart.microservices.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.globomart.microservices.dao.ProductDAO;
import com.globomart.microservices.exception.ProductException;
import com.globomart.microservices.object.Product;
import com.globomart.microservices.object.ProductType;

@Path("/microservice")
public class ProductCatalogueService {
	private final static Logger logger = Logger.getLogger(ProductCatalogueService.class);

	private Set<Product> productCatalogue = new HashSet<Product>();

	private Map<ProductType, Product> mapProductType = new HashMap<ProductType, Product>();

	private ProductDAO dao = new ProductDAO(productCatalogue, mapProductType);
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addProduct(Product product) {

		try {
			if (product == null || productCatalogue.contains(product)) {
				throw new ProductException("Invalid product", product);
			} else {
				dao.addProduct(product);
			}
		} catch (ProductException e) {
			logger.error(e.getMessage());
		}
		return Response.status(201).entity("Product added").build();
	}

	public List<Product> getProducts(ProductType type) {
		List<Product> productList = new ArrayList<Product>();
		try {
			productList = dao.getProducts(type);
			if (productList == null) {
				// fix the exception constructor here
				throw new ProductException();
			}

		} catch (ProductException e) {
			logger.warn(e.getMessage());
		}
		return productList;
	}

	public void removeProduct(String id) {
		try {
			for (Product p : productCatalogue) {
				if (p.getId() == id) {
					dao.removeProduct(p);
					return;
				}
			}
			// fix the exception constructor here
			throw new ProductException();
		} catch (ProductException e) {
			e.printStackTrace();
		}

	}
}

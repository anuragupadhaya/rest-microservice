package com.globomart.microservices.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.globomart.microservices.dao.ProductDAO;
import com.globomart.microservices.exception.ProductException;
import com.globomart.microservices.object.Product;
import com.globomart.microservices.object.ProductType;
import com.sun.jersey.spi.resource.Singleton;

@Path("/product")
@Singleton
public class ProductCatalogueService {
	private final static Logger logger = Logger.getLogger(ProductCatalogueService.class);

	private Set<Product> productCatalogue;

	private ProductDAO dao;

	public ProductCatalogueService() {
		this.productCatalogue = new HashSet<Product>();
		this.dao = new ProductDAO(this.productCatalogue, new HashMap<ProductType, List<Product>>());
	}

	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addProduct(Product input) {
		Response response = null;
		Product product = null;
		try {
			if (input.getId() == null || input.getName() == null || input.getType() == null || input.getPrice() == null
					|| productCatalogue.contains(product)) {
				throw new ProductException("Invalid product", product);
			} else {
				product = new Product(input.getId(), input.getName(), input.getType(), input.getPrice());
				dao.addProduct(product);
				response = Response.status(Response.Status.CREATED).entity(product).build();
			}
		} catch (ProductException e) {
			response = Response.status(Response.Status.BAD_REQUEST).build();
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			response = Response.status(Response.Status.BAD_REQUEST).build();
		}
		return response;
	}

	@GET
	@Path("/search/{type}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getProducts(@PathParam("type") String type) {
		ProductType productType = ProductType.valueOf(type.toUpperCase());
		List<Product> productList = new ArrayList<Product>();
		try {
			productList = dao.getProducts(productType);
			if (productList == null) {
				throw new ProductException("Invalid Product Type:" + type);
			}
		} catch (ProductException e) {
			e.printStackTrace();
		}
		return productList;
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProductByID(@PathParam("id") String id) {
		Response response = null;
		Product product = null;
		try {
			if (id == null) {
				throw new ProductException("Invalid Product ID:" + id);
			}
			for (Product p : productCatalogue) {
				if (p.getId().equals(id)) {
					product = p;
					response = Response.status(Response.Status.OK).entity(product).build();
					break;
				}
			}
			if (product == null) {
				throw new ProductException("Invalid Product ID:" + id);
			}
		} catch (ProductException e) {
			response = Response.status(Response.Status.NOT_FOUND).build();
			e.printStackTrace();
		}
		return response;
	}

	@POST
	@Path("/remove/{id}")
	public Response removeProduct(@PathParam("id") String id) {
		Response response = null;
		try {
			if (id == null) {
				throw new ProductException("Invalid Product ID:" + id);
			}

			response = Response.status(Response.Status.NOT_FOUND).build();

			for (Product p : productCatalogue) {
				if (p.getId().equals(id)) {
					dao.removeProduct(p);
					response = Response.status(Response.Status.OK).build();
					break;
				}
			}
		} catch (ProductException e) {
			response = Response.status(Response.Status.NOT_FOUND).build();
			e.printStackTrace();
		}
		return response;

	}
}

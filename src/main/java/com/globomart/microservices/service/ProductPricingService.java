package com.globomart.microservices.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.codehaus.jettison.json.JSONException;

import com.globomart.microservices.exception.ProductException;
import com.globomart.microservices.object.Product;
import com.sun.jersey.api.core.ResourceContext;

@Path("/price")
public class ProductPricingService {

	@Context
	private ResourceContext rc;

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProductPrice(@PathParam("id") String id) throws JSONException {

		ProductCatalogueService catalogueService = rc.getResource(ProductCatalogueService.class);
		Response catalogueResponse = catalogueService.getProductByID(id);
		Response response = null;
		Product product = null;
		try {
			if (catalogueResponse.getStatus() == (Response.Status.OK.getStatusCode())) {
				product = (Product) catalogueResponse.getEntity();
				ObjectMapper objectMapper = new ObjectMapper();
				ObjectNode price = objectMapper.createObjectNode();
				price.put("price", product.getPrice());
				response = Response.status(Response.Status.OK).entity(price).build();
			} else {

				throw new ProductException("Invalid Product:" + product);
			}
		} catch (ProductException e) {
			response = Response.status(Response.Status.NOT_FOUND).build();
			e.printStackTrace();
		}

		return response;
	}

}

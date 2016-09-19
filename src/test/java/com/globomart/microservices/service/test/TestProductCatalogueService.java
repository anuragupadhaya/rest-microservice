package com.globomart.microservices.service.test;

import javax.ws.rs.core.Response;

import com.globomart.microservices.client.ProductCatalogueClient;

import junit.framework.TestCase;

public class TestProductCatalogueService extends TestCase {

	public final void testAddProduct() {
		String json = "{\"id\":\"2\",\"name\":\"Samsung Note 7\", \"type\":\"ELECTRONICS\", \"price\":\"78000\"}";

		ProductCatalogueClient.invokeAddProduct(json);

		assertEquals(Response.Status.CREATED, ProductCatalogueClient.response.getResponseStatus());

	}

	public final void testGetProducts() {
		ProductCatalogueClient.invokeGetProductByProductID("2");

		assertEquals(Response.Status.OK, ProductCatalogueClient.response.getResponseStatus());
	}

	public final void testGetProductByID() {
		fail("Not yet implemented"); // TODO
	}

	public final void testRemoveProduct() {
		fail("Not yet implemented"); // TODO
	}

}

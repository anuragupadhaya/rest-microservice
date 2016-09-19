package com.globomart.microservices.service.test;

import javax.ws.rs.core.Response;

import com.globomart.microservices.client.ProductPricingClient;

import junit.framework.TestCase;

public class TestProductPricingService extends TestCase {

	public final void testInvokeGetPriceByProductID() {
		ProductPricingClient.invokeGetPriceByProductID("1");
		assertEquals(Response.Status.OK, ProductPricingClient.response.getResponseStatus());
	}

}

package com.globomart.microservices.client;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class ProductPricingClient {
	public static ClientResponse response;
	static String url = "http://localhost:8080/microservices/globomart/price/";
	static Client client;
	static WebResource webResource;

	public static void invokeGetPriceByProductID(String id) {
		try {
			client = Client.create();
			webResource = client.resource(url + id);
			response = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);

			if (response.getStatus() != Response.Status.OK.getStatusCode()) {
				throw new RuntimeException("Failed:" + response.getStatus());
			}

			String output = response.getEntity(String.class);

			System.out.println("Output:" + output);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

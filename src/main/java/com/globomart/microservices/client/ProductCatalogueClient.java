package com.globomart.microservices.client;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class ProductCatalogueClient {

	public static ClientResponse response;
	static String url = "http://localhost:8080/microservices/globomart/product/";
	static Client client;
	static WebResource webResource;

	public static void invokeRemoveProduct(String id) {
		try {
			client = Client.create();
			webResource = client.resource(url + "remove/" + id);
			response = webResource.post(ClientResponse.class);

			if (response.getStatus() != Response.Status.OK.getStatusCode()) {
				throw new RuntimeException("Failed:" + response.getStatus());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void invokeSearchProductByType(String type) {
		try {
			client = Client.create();
			webResource = client.resource(url + "search/" + type);
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

	public static void invokeAddProduct(String json) {
		try {
			client = Client.create();
			webResource = client.resource(url + "add");
			response = webResource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, json);

			if (response.getStatus() != Response.Status.CREATED.getStatusCode()) {
				throw new RuntimeException("Failed:" + response.getStatus());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void invokeGetProductByProductID(String id) {
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

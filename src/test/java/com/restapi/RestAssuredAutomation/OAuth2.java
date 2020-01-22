package com.restapi.RestAssuredAutomation;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class OAuth2 {
	
	
	@Test
	public void test1() {
		
		Response response =RestAssured.given()
		.auth()
		.oauth2("a7eea803c95085798798f9b7f8975e3179841c1d")
		.post("http://coop.apps.symfonycasts.com/api/486/chickens-feed");
		
		System.out.println("Response code: "+response.getStatusCode());
		System.out.println("Respose body :"+response.getBody().asString());
		System.out.println("Content-type:"+response.contentType());
	}

}

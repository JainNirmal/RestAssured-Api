package com.restapi.RestAssuredAutomation;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveAuthProvider;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AuthenticationDemo {
	
	
	@Test
	public void basitAuthenticationAPI() {
	
	RestAssured.baseURI="http://restapi.demoqa.com/authentication/CheckForAuthentication";
	PreemptiveBasicAuthScheme  authScheme=new PreemptiveBasicAuthScheme();
	authScheme.setPassword("TestPassword");
	authScheme.setUserName("ToolsQA");
	
	RestAssured.authentication=authScheme;
	
	RequestSpecification httpRequest = RestAssured.given();
	
	
	Response response =httpRequest.request(Method.GET,"/");

	System.out.println("Response code :"+response.getStatusCode());
	
	
	String responseBody = response.getBody().asString();
	
	System.out.println("Response Body is :"+responseBody);
	
	
	
	}

}

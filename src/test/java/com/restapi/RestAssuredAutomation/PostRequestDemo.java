package com.restapi.RestAssuredAutomation;


import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostRequestDemo {
	
	@Test
	public void registrationSucessFull()  {
	
	RestAssured.baseURI="http://restapi.demoqa.com/customer";
	RequestSpecification  request =RestAssured.given();
	org.json.simple.JSONObject jsonObject = new org.json.simple.JSONObject();
	
    jsonObject.put("FirstName", "Vihaan");
    jsonObject.put("LastName", "Chandan");
    jsonObject.put("UserName", "vihan");
    jsonObject.put("Password", "Password.10");
    jsonObject.put("Email", "vihaan.tester@gmail.com");
    
    request.header("Content-Type","application/json");

    request.body(jsonObject.toJSONString());
    Response response = request.post("/register");
    
    int getStatusCode =response.getStatusCode();
    System.out.println("Get status code .. " +getStatusCode);
    
    Assert.assertEquals(getStatusCode, 201,"Status code does not matched....");
    
    String getSucessCode =response.jsonPath().getString("SuccessCode");
    System.out.println("Sucess code is :"+getSucessCode);
    Assert.assertEquals(getSucessCode,"OPERATION_SUCCESS","Correct Success code was returned");
    System.out.println("Sucess message is ::"+response.jsonPath().get("Message"));

	}

}

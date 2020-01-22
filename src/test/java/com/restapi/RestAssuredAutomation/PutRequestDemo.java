package com.restapi.RestAssuredAutomation;


import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PutRequestDemo {
	
	
	@Test
	public void putRequestUpdated()  {
		int empId=15410;
		int put_status_code=200;
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		RequestSpecification request = RestAssured.given();
		
		JSONObject requestParams = new JSONObject();
		requestParams.put("name", "Zion"); 
		requestParams.put("age", 23);
		requestParams.put("salary", 12000);
		
		
		request.header("Content-Type","application/json");		
		request.body(requestParams.toJSONString());
		Response response = request.put("/update/"+empId);		
		System.out.println("Get body :"+ response.getBody().asString());		
		System.out.println("Get Status code of PUT request :"+response.getStatusCode());
		Assert.assertEquals(response.getStatusCode(), 200,"Response status code is incorrect...");
		
		
		Response responsedelete  = request.delete("/delete/"+empId);	
		System.out.println("Delete status code :"+responsedelete.getStatusCode());
		
	}

}

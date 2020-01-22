package com.restapi.RestAssuredAutomation;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class GetRequestDemo {

	@Test
	public void statusVerificationCode() {

		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		RequestSpecification httpRequestSpecification = RestAssured.given();
		Response response = httpRequestSpecification.request(Method.GET, "/Indore");
		String responseBody = response.getBody().asString();

		int sucessResponseCode = 200;
		int errorResponsecode = 400;

		String statusLine = "HTTP/1.1 200 OK";

		// Get the Response body ...
		System.out.println("Response body :" + responseBody);

		System.out.println("******************* Validation of response status *************************");

		/*
		 * Get the Response status which contains the following --- a. Responce status
		 * b. Error Status code c. Response status line
		 */

		// a. Response status -
		int statusCode = response.getStatusCode();
		System.out.println("Response code for the URL is :" + statusCode);
		Assert.assertEquals(statusCode, sucessResponseCode);

		// b. Get Error status code
		Response response2 = httpRequestSpecification.request(Method.GET, "/1233123");
		int errorStatusCode = response2.getStatusCode();
		System.out.println("Response Error code for the URL is :" + errorStatusCode);
		Assert.assertEquals(errorStatusCode, errorResponsecode, "Response Error code 400 displayed.");

		// c. Response status line
		String resposeStatusLine = response.getStatusLine().toString();
		
		System.out.println("Response Status Line : " + resposeStatusLine);
		Assert.assertEquals(resposeStatusLine, statusLine, "Response status line is incorrect");

		System.out.println("******************* Validation of response header *************************");

		/*
		 * Get the Response header which contains the following - a. content Type b.
		 * server c. content-encoding
		 */

		// content Type
		String contentType = response.header("Content-Type");
		System.out.println("Content Type value is ::" + contentType);
		// Server name
		String serverName = response.header("Server");
		System.out.println("Server name  is ::" + serverName);
		// content Type
		String contentEncoding = response.header("Content-Encoding");
		System.out.println("Content encoding text is  ::" + contentEncoding);
		// Server name with getHeader methods
		System.out.println("Content-Type with getHeader Method is  ::" + response.getHeader("Content-Type"));
		// Get All headers ..
		Headers allHeaders = response.getHeaders();
		System.out.println("Total headers count :" + response.getHeaders().size());
		for (Header header : allHeaders) {
			System.out.println("*****************");
			System.out.println("Key ::" + header.getName() + "Values ::" + header.getValue());
		}

		System.out.println("******************* Validation of response body *************************");
		Response responseDelhi = httpRequestSpecification.request(Method.GET, "/delhi");
		ResponseBody responsebody = responseDelhi.getBody();
		String bodyAsString = responsebody.asString();
		System.out.println("Response body for Delhi city as below -  ");
		System.out.println(bodyAsString);
		Assert.assertEquals(bodyAsString.toLowerCase().contains("delhi"), true, " Delhi city is found in response");

		System.out
				.println("******************* Validation of response body using JSON  Path *************************");
		Response responseMumbai = httpRequestSpecification.get("/Mumbai");
		ResponseBody resbodyMum = responseMumbai.getBody();

		System.out.println("Response for Mumbai city is - ");
		System.out.println(resbodyMum.asString());

		// Get the JSON path object instance
		JsonPath jsonpathEvaluator = responseMumbai.jsonPath();
		String city = jsonpathEvaluator.get("City");
		System.out.println("City name is :" + city);
		Assert.assertEquals(city, "Mumbai", "Incorrect city in JSON path");
		System.out.println("Temp of " + city + " is  ::" + jsonpathEvaluator.get("Temperature"));
	}
}

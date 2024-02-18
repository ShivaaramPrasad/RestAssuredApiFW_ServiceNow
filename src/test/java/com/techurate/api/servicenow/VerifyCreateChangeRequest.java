package com.techurate.api.servicenow;

import static com.techurate.api.config.ConfigManage.getConfig;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.testng.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.techurate.api.services.PreAndPostTest;
import com.techurate.api.utilites.enumdata.StatusCode;
import com.techurate.api.utilites.payload.ChangeRequest;

import io.restassured.response.Response;

public class VerifyCreateChangeRequest extends PreAndPostTest{
	//String profileIdValue="P700";

	@BeforeTest
	public  void initate(){
		testcaseName = "VerifyCreateChangeRequest";
		testDescription = "Verify Create Change Request";
		authors = "Shivaaram";
		category = "API";

	}

	@Test(priority=1)
	public void VerifyCreateChangeRequestBasics() {
		ChangeRequest payload = ChangeRequest.
				builder().short_description("Created from Restassured").description("Addtional infiormation").build();

		Map<String, String> allHeaders = new HashMap<>();
		allHeaders.put("Content-Type", "application/json");
		Response response = postWithJsonAsBody(payload, "change_request");
		verifyResponseIsNotEmptyString(response);
		verifyContentWithKey(response, "result.number", "CHG");
		verifyResponseCode(response, StatusCode.CREATED.code);
		verifyResponseTime(response,getConfig().timeout());
		//	assertEquals(response.jsonPath().getList("name"), hasItems("prasad"));
		//assertEquals(response.jsonPath().getList("name"), hasSize(500));
		// List<String> expectedEmail =Arrays.asList("test@test.com","shivaa@test.com");
		//assertEquals(response.jsonPath().getList("email"), contains(expectedEmail.toArray(new String[0])));
		//assertEquals(response.then().body("data[0].name", is("Shivaa")),"Shivaa");
		//System.out.println(response.body().asString());
		sysId = response.jsonPath().get("result.sys_id");   // this can be used only when the response is  in array
		System.out.println("Sys Id: "+sysId);
	  
	} 

	
	@Test(priority=2,enabled=false)
	public void VerifyCreateChangeRequestUsingQueryParam() {
		ChangeRequest payload = ChangeRequest.
				builder().short_description("Created from Restassured").description("Addtional infiormation").build();

		Map<String, String> allHeaders = new HashMap<>();
		allHeaders.put("Content-Type", "application/json");
		Response response =	postWithJsonAsBodyQueryParam(payload, "sysparm_fields", "sys_id, priority, number,short_description ,description", "change_request");
		verifyResponseIsNotEmptyString(response);
		verifyContentWithKey(response, "result.number", "CHG");
		verifyResponseCode(response, StatusCode.CREATED.code);
		verifyResponseTime(response,getConfig().timeout());
		//	assertEquals(response.jsonPath().getList("name"), hasItems("prasad"));
		//assertEquals(response.jsonPath().getList("name"), hasSize(500));
		// List<String> expectedEmail =Arrays.asList("test@test.com","shivaa@test.com");
		//assertEquals(response.jsonPath().getList("email"), contains(expectedEmail.toArray(new String[0])));
		//assertEquals(response.then().body("data[0].name", is("Shivaa")),"Shivaa");
	//	System.out.println(response.body().asString());

	} 
	
	
	@Test(priority=3,enabled=false)
	public void VerifyCreateChangeRequestUsingQueryParams() {
		ChangeRequest payload = ChangeRequest.
				builder().short_description("Created from Restassured").description("Addtional infiormation").build();

		Map<String, String> allHeaders = new HashMap<>();
		allHeaders.put("Content-Type", "application/json");
		
		Map <String, String> allQueryParameters =new HashMap<String, String>();
		allQueryParameters.put("category", "software");
		allQueryParameters.put("sysparm_fields", "number,sys_id,category");

		Response response =	postWithJsonAsBodyQueryParams(payload, allQueryParameters, "change_request");
		verifyResponseIsNotEmptyString(response);
		verifyContentWithKey(response, "result.number", "CHG");
		verifyResponseCode(response, StatusCode.CREATED.code);
		verifyResponseTime(response,getConfig().timeout());
		assertEquals(response.jsonPath().getList("name"), hasItems("prasad"));
		assertEquals(response.jsonPath().getList("name"), hasSize(500));
		List<String> expectedEmail =Arrays.asList("test@test.com","shivaa@test.com");
		assertEquals(response.jsonPath().getList("email"), contains(expectedEmail.toArray(new String[0])));
		assertEquals(response.then().body("data[0].name", is("Shivaa")),"Shivaa");
		System.out.println(response.body().asString());

	} 
}

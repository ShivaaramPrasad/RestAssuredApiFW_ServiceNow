package com.techurate.api.servicenow;

import static com.techurate.api.config.ConfigManage.getConfig;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.techurate.api.services.PreAndPostTest;
import com.techurate.api.utilites.enumdata.StatusCode;

import io.restassured.response.Response;

public class VerifyGetChangeRequest extends PreAndPostTest{
	//String profileIdValue="P700";

	@BeforeTest
	public  void initate(){
		testcaseName = "VerifyGetChangeRequest";
		testDescription = "Verify Get Change Request";
		authors = "Shivaaram";
		category = "API";

	}

	@Test(priority=1,dependsOnMethods="com.techurate.api.servicenow.VerifyCreateChangeRequest.VerifyCreateChangeRequestBasics")
	public void VerifyGetChangeRequestBasic() {
		
		Response response = get("change_request/"+sysId);
		verifyResponseIsNotEmptyString(response);
		verifyResponseCode(response, StatusCode.SUCCESS.code);
		verifyResponseTime(response,getConfig().timeout());
		verifyContentWithKey(response, "result.number", "CHG");
		sysId = response.jsonPath().get("result.sys_id");   // this can be used only when the response is  in array
		System.out.println("Sys Id: "+sysId);
	} 
	
	
	@Test(priority=2,enabled=false)
	public void VerifyCreateChangeRequestUsingQueryParam() {

		Map<String, String> allHeaders = new HashMap<>();
		allHeaders.put("Content-Type", "application/json");
		Response response =	getWithQueryParam("sysparm_fields", "sys_id, priority, number,short_description ,description", "change_request");
		verifyResponseIsNotEmptyString(response);
		verifyContentWithKey(response, "result.number[0]", "CHG");
		verifyResponseCode(response, StatusCode.SUCCESS.code);
		verifyResponseTime(response,getConfig().timeout());
		//	assertEquals(response.jsonPath().getList("name"), hasItems("prasad"));
		//assertEquals(response.jsonPath().getList("name"), hasSize(500));
		// List<String> expectedEmail =Arrays.asList("test@test.com","shivaa@test.com");
		//assertEquals(response.jsonPath().getList("email"), contains(expectedEmail.toArray(new String[0])));
		//assertEquals(response.then().body("data[0].name", is("Shivaa")),"Shivaa");
		System.out.println(response.body().asString());

	} 
	
	
	@Test(priority=3,enabled=false)
	public void VerifyCreateChangeRequestUsingQueryParams() {
		
		Map<String, String> allHeaders = new HashMap<>();
		allHeaders.put("Content-Type", "application/json");
		
		Map <String, String> allQueryParameters =new HashMap<String, String>();
		allQueryParameters.put("category", "software");
		allQueryParameters.put("sysparm_fields", "number,sys_id,category");

		Response response =	getWithQueryParams(allQueryParameters, "change_request");
		verifyResponseIsNotEmptyString(response);
		verifyContentWithKey(response, "result.number[0]", "CHG");
		verifyResponseCode(response, StatusCode.SUCCESS.code);
		verifyResponseTime(response,getConfig().timeout());
		//	assertEquals(response.jsonPath().getList("name"), hasItems("prasad"));
		//assertEquals(response.jsonPath().getList("name"), hasSize(500));
		// List<String> expectedEmail =Arrays.asList("test@test.com","shivaa@test.com");
		//assertEquals(response.jsonPath().getList("email"), contains(expectedEmail.toArray(new String[0])));
		//assertEquals(response.then().body("data[0].name", is("Shivaa")),"Shivaa");
		System.out.println(response.body().asString());

	} 

}

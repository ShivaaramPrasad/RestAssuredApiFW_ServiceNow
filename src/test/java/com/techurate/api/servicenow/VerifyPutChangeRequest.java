package com.techurate.api.servicenow;

import static com.techurate.api.config.ConfigManage.getConfig;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.techurate.api.services.PreAndPostTest;
import com.techurate.api.utilites.enumdata.StatusCode;
import com.techurate.api.utilites.payload.ChangeRequest;

import io.restassured.response.Response;

public class VerifyPutChangeRequest extends PreAndPostTest{
	//String profileIdValue="P700";

	@BeforeTest
	public  void initate(){
		testcaseName = "VerifyPutChangeRequest";
		testDescription = "Verify Put Change Request";
		authors = "Shivaaram";
		category = "API";
	}

	@Test(priority=1,dependsOnMethods="com.techurate.api.servicenow.VerifyGetChangeRequest.VerifyGetChangeRequestBasic")
	public void VerifyPutChangeRequestBasic() {

		ChangeRequest payload = ChangeRequest.
				builder().short_description("Update from Restassured").description("Updat infiormation").build();

		Map<String, String> allHeaders = new HashMap<>();
		allHeaders.put("Content-Type", "application/json");
		//verifyResponseCode(response, StatusCode.SUCCESS.code);
		Response response = putWithJsonAsBody(payload, "change_request/"+sysId);
		verifyResponseIsNotEmptyString(response);
		verifyContentWithKey(response, "result.number", "CHG");
		verifyResponseTime(response,getConfig().timeout());

	} 

	@Test(priority=1,enabled=false )
	public void VerifyPutChangeRequestQueryParam() {

		ChangeRequest payload = ChangeRequest.
				builder().short_description("Update from Restassured").description("Updat infiormation").build();

		Map<String, String> allHeaders = new HashMap<>();
		allHeaders.put("Content-Type", "application/json");
		Response response = putWithJsonAsBodyQueryParam(payload, "sysparm_fields", "sys_id, priority, number,short_description ,description", "change_request/739837a29729b110d6573ff11153afe2");
		verifyResponseIsNotEmptyString(response);
		verifyResponseCode(response, StatusCode.SUCCESS.code);
		verifyContentWithKey(response, "result.number", "CHG");
		verifyResponseTime(response,getConfig().timeout());
		System.out.println(response.statusCode());

	} 

	@Test(priority=1, enabled=false)
	public void VerifyPutChangeRequestQueryParams() {

		ChangeRequest payload = ChangeRequest.
				builder().short_description("Update from Restassured").description("Updat infiormation").build();

		Map <String, String> allQueryParameters =new HashMap<String, String>();
		allQueryParameters.put("category", "software");
		allQueryParameters.put("sysparm_fields", "number,sys_id,category");

		Map<String, String> allHeaders = new HashMap<>();
		allHeaders.put("Content-Type", "application/json");
		Response response = putWithJsonAsBodyQueryParams(payload, allQueryParameters, "change_request/"+sysId);
		verifyResponseIsNotEmptyString(response);
		verifyResponseCode(response, StatusCode.SUCCESS.code);
		verifyContentWithKey(response, "result.number", "CHG");
		verifyResponseTime(response,getConfig().timeout());
		System.out.println(response.statusCode());

	} 

}

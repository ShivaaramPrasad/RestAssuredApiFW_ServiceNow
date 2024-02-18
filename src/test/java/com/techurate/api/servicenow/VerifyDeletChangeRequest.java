package com.techurate.api.servicenow;

import static com.techurate.api.config.ConfigManage.getConfig;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.techurate.api.services.PreAndPostTest;
import com.techurate.api.utilites.enumdata.StatusCode;

import io.restassured.response.Response;

public class VerifyDeletChangeRequest extends PreAndPostTest{
	//String profileIdValue="P700";

	@BeforeTest
	public  void initate(){
		testcaseName = "VerifyDeletChangeRequest";
		testDescription = "Verify Delet Change Request";
		authors = "Shivaaram";
		category = "API";
	}

	@Test(priority=1,dependsOnMethods="com.techurate.api.servicenow.VerifyPutChangeRequest.VerifyPutChangeRequestBasic" )
	public void VerifyDeletChangeRequestBasic() {

		Map<String, String> allHeaders = new HashMap<>();
		allHeaders.put("Content-Type", "application/json");
		Response response = delete("change_request/"+sysId);
		verifyResponseIsNotEmptyString(response);
		verifyResponseCode(response, StatusCode.NO_CONTENT.code);
		verifyResponseTime(response,getConfig().timeout());
	} 

}

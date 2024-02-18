package com.techurate.api.services;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONObject;

import com.techurate.api.utilites.ExtentReportManager;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestLogSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;

public class BaseRESTAssured extends ExtentReportManager{

	public static  RequestSpecification setLogs() {

		RequestLogSpecification log = RestAssured
				.given()
				.log();
		return log.all().contentType(getContentType());
	}

	public  Response post() {

		return setLogs()
				.post();
	}

	public  Response post(String URL) {

		QueryableRequestSpecification queryableRequestSpecification = SpecificationQuerier.query(setLogs());
		logInfoDetails("Request url is "+queryableRequestSpecification.getBaseUri()+"/"+URL);
		logInfoDetails("Request method is "+queryableRequestSpecification.getMethod());
		logInfoDetails("Request Headers are "); 
		logHeaders(queryableRequestSpecification.getHeaders().asList());
		
		Response postRequest=  setLogs()
				.when().post(URL);
		return postRequest;

	}


	public static Response postWithBodyAsFile(File bodyFile) {

		return setLogs()
				.body(bodyFile)
				.post();
	}
	
	public  Response postWithtQueryParams(Map<String, String> queryParamsValue, String URL) {
		QueryableRequestSpecification queryableRequestSpecification = SpecificationQuerier.query(setLogs());
		logInfoDetails("Request url is "+queryableRequestSpecification.getBaseUri()+"/"+URL);
		logInfoDetails("Request method is "+queryableRequestSpecification.getMethod());
		logInfoDetails("Request Headers are "); 
		logHeaders(queryableRequestSpecification.getHeaders().asList());
		logInfoDetails("Request body is "); 

		Response getRequest=  setLogs().queryParams(queryParamsValue)
				.when().get(URL);
		return getRequest;
	}
	
	public static Response postWithBodyAsFileAndUrl(File bodyFile, String URL) {
		QueryableRequestSpecification queryableRequestSpecification = SpecificationQuerier.query(setLogs());
		logInfoDetails("Request url is "+queryableRequestSpecification.getBaseUri()+"/"+URL);
		logInfoDetails("Request method is "+queryableRequestSpecification.getMethod());
		logInfoDetails("Request Headers are "); 
		logHeaders(queryableRequestSpecification.getHeaders().asList());
		logInfoDetails("Request body is "); 
		logHeaders(queryableRequestSpecification.getBody());

		return setLogs()
				.body(bodyFile)
				.post(URL);
	}

	public static Response postWithHeaderAndForm(Map<String, String> headers,
			Object jsonObject, String URL) {

		QueryableRequestSpecification queryableRequestSpecification = SpecificationQuerier.query(setLogs());
		logInfoDetails("Request url is "+queryableRequestSpecification.getBaseUri()+"/"+URL);
		logInfoDetails("Request method is "+queryableRequestSpecification.getMethod());
		logInfoDetails("Request Headers are "); 
		logHeaders(queryableRequestSpecification.getHeaders().asList());
		logInfoDetails("Request body is "); 
		//logHeaders(queryableRequestSpecification.getBody());

		return setLogs()
				.headers(headers)
				.body(jsonObject)
				.post(URL);
	}

	public static Response postWithJsonAsBody(Object jsonObject, String URL) {

		QueryableRequestSpecification queryableRequestSpecification = SpecificationQuerier.query(setLogs());
		logInfoDetails("Request url is "+queryableRequestSpecification.getBaseUri()+"/"+URL);
		logInfoDetails("Request method is "+queryableRequestSpecification.getMethod());
		logInfoDetails("Request Headers are "); 
		logHeaders(queryableRequestSpecification.getHeaders().asList());
		logInfoDetails("Request body is "); 

		Response postRequest=  setLogs()
				.when().body(jsonObject).post(URL);
		
	  // logJson(queryableRequestSpecification.getBody());

		return postRequest;
	}

	public  Response postWithHeaderAndFormParam(Headers headers, String paraName,String file,String URL) {
		return	setLogs()
				.headers(headers)
				.formParam(paraName, file)
				.post(URL);
	}
	
	public static Response postWithJsonAsBodyQueryParam(Object jsonObject, String queryParamkey, String queryParamValue, String URL) {

		QueryableRequestSpecification queryableRequestSpecification = SpecificationQuerier.query(setLogs());
		logInfoDetails("Request url is "+queryableRequestSpecification.getBaseUri()+"/"+URL);
		logInfoDetails("Request method is "+queryableRequestSpecification.getMethod());
		logInfoDetails("Request Headers are "); 
		logHeaders(queryableRequestSpecification.getHeaders().asList());
		logInfoDetails("Request body is "); 

		Response postRequest=  setLogs().queryParam(queryParamkey,queryParamValue)
				.when().body(jsonObject).post(URL);
		
	  // logJson(queryableRequestSpecification.getBody());

		return postRequest;
	}

	public static Response postWithJsonAsBodyQueryParams(Object jsonObject, Map<String, String> queryParamsValue, String URL) {

		QueryableRequestSpecification queryableRequestSpecification = SpecificationQuerier.query(setLogs());
		logInfoDetails("Request url is "+queryableRequestSpecification.getBaseUri()+"/"+URL);
		logInfoDetails("Request method is "+queryableRequestSpecification.getMethod());
		logInfoDetails("Request Headers are "); 
		logHeaders(queryableRequestSpecification.getHeaders().asList());
		logInfoDetails("Request body is "); 

		Response postRequest=  setLogs().queryParams(queryParamsValue)
				.when().body(jsonObject).post(URL);
		
	  // logJson(queryableRequestSpecification.getBody());

		return postRequest;
	}

	public static Response postWithHeaderAndJsonBody(Map<String, String> headers,
			String jsonObject, String URL) {
		
		QueryableRequestSpecification queryableRequestSpecification = SpecificationQuerier.query(setLogs());
		logInfoDetails("Request url is "+queryableRequestSpecification.getBaseUri()+"/"+URL);
		logInfoDetails("Request method is "+queryableRequestSpecification.getMethod());
		logInfoDetails("Request Headers are "); 
		logHeaders(queryableRequestSpecification.getHeaders().asList());
		logInfoDetails("Request body is "); 

		return setLogs()
				.when()
				.headers(headers)
				.body(jsonObject)
				.post(URL);
	}


	public static Response postWithHeaderParam(Map<String, String> headers, String URL) {
		
		QueryableRequestSpecification queryableRequestSpecification = SpecificationQuerier.query(setLogs());
		logInfoDetails("Request url is "+queryableRequestSpecification.getBaseUri()+"/"+URL);
		logInfoDetails("Request method is "+queryableRequestSpecification.getMethod());
		logInfoDetails("Request Headers are "); 
		logHeaders(queryableRequestSpecification.getHeaders().asList());
		logInfoDetails("Request body is "); 

		return setLogs()
				.when()
				.headers(headers)
				.post(URL);
	}


	public static Response postWithHeaderAndPathParam(Map<String, String> headers,Map<String, String> pathParms, String URL) {

		QueryableRequestSpecification queryableRequestSpecification = SpecificationQuerier.query(setLogs());
		logInfoDetails("Request url is "+queryableRequestSpecification.getBaseUri()+"/"+URL);
		logInfoDetails("Request method is "+queryableRequestSpecification.getMethod());
		logInfoDetails("Request Headers are "); 
		logHeaders(queryableRequestSpecification.getHeaders().asList());
		logInfoDetails("Request body is "); 

		return setLogs()
				.when()
				.headers(headers)
				.pathParams(pathParms)
				.post(URL);
	}

	public static Response postWithHeaderAndPathParam(Map<String, String> pathParms, String URL) {
		
		QueryableRequestSpecification queryableRequestSpecification = SpecificationQuerier.query(setLogs());
		logInfoDetails("Request url is "+queryableRequestSpecification.getBaseUri()+"/"+URL);
		logInfoDetails("Request method is "+queryableRequestSpecification.getMethod());
		logInfoDetails("Request Headers are "); 
		logHeaders(queryableRequestSpecification.getHeaders().asList());
		logInfoDetails("Request body is "); 

		return setLogs()
				.when()
				.pathParams(pathParms)
				.post(URL);
	}



	public static Response putWithHeaderAndBodyParam(Map<String, String> headers,
			JSONObject jsonObject, String URL) {

		return RestAssured.given().headers(headers).contentType(getContentType()).request()
				.body(jsonObject).when().put(URL);
	}

	public static Response putWithBodyParam(File file, String URL) {

		return RestAssured.given().contentType(getContentType()).request()
				.body(file).when().put(URL);
	}

	public static Response putWithJsonAsBody(Object jsonObject, String URL) {

		QueryableRequestSpecification queryableRequestSpecification = SpecificationQuerier.query(setLogs());
		logInfoDetails("Request url is "+queryableRequestSpecification.getBaseUri()+"/"+URL);
		logInfoDetails("Request method is "+queryableRequestSpecification.getMethod());
		logInfoDetails("Request Headers are "); 
		logHeaders(queryableRequestSpecification.getHeaders().asList());
		logInfoDetails("Request body is "); 
	//	logHeaders(queryableRequestSpecification.getBody());

		Response putRequest=  setLogs()
				.when().body(jsonObject).put(URL);
		return putRequest;
	}
	
	public static Response putWithJsonAsBodyQueryParam(Object jsonObject, String queryParamkey,String queryParamValue, String URL) {

		QueryableRequestSpecification queryableRequestSpecification = SpecificationQuerier.query(setLogs());
		logInfoDetails("Request url is "+queryableRequestSpecification.getBaseUri()+"/"+URL);
		logInfoDetails("Request method is "+queryableRequestSpecification.getMethod());
		logInfoDetails("Request Headers are "); 
		logHeaders(queryableRequestSpecification.getHeaders().asList());
		logInfoDetails("Request body is "); 
	//	logHeaders(queryableRequestSpecification.getBody());

		Response putRequest=  setLogs().queryParam(queryParamkey,queryParamValue)
				.when().body(jsonObject).put(URL);
		return putRequest;
	}
	
	public static Response putWithJsonAsBodyQueryParams(Object jsonObject, Map<String, String> queryParamsValue, String URL) {

		QueryableRequestSpecification queryableRequestSpecification = SpecificationQuerier.query(setLogs());
		logInfoDetails("Request url is "+queryableRequestSpecification.getBaseUri()+"/"+URL);
		logInfoDetails("Request method is "+queryableRequestSpecification.getMethod());
		logInfoDetails("Request Headers are "); 
		logHeaders(queryableRequestSpecification.getHeaders().asList());
		logInfoDetails("Request body is "); 
		//logHeaders(queryableRequestSpecification.getBody());

		Response putRequest=  setLogs().queryParams(queryParamsValue)
				.when().body(jsonObject).put(URL);
		return putRequest;
	}
	
	public  Response get(String URL) {
		QueryableRequestSpecification queryableRequestSpecification = SpecificationQuerier.query(setLogs());
		logInfoDetails("Request url is "+queryableRequestSpecification.getBaseUri()+"/"+URL);
		logInfoDetails("Request method is "+queryableRequestSpecification.getMethod());
		logInfoDetails("Request Headers are "); 
		logHeaders(queryableRequestSpecification.getHeaders().asList());
		Response getRequest=  setLogs()
				.when().get(URL);
		return getRequest;
	}
	
	public  Response getWithQueryParams(Map<String, String> allHeaders, String URL) {
		QueryableRequestSpecification queryableRequestSpecification = SpecificationQuerier.query(setLogs());
		logInfoDetails("Request url is "+queryableRequestSpecification.getBaseUri()+"/"+URL);
		logInfoDetails("Request method is "+queryableRequestSpecification.getMethod());
		logInfoDetails("Request Headers are "); 
		logHeaders(queryableRequestSpecification.getHeaders().asList());
		logInfoDetails("Request body is "); 

		Response getRequest=  setLogs().queryParams(allHeaders)
				.when().get(URL);
		return getRequest;
	}
	
	public  Response getWithQueryParam(String queryParamkey,String queryParamValue, String URL) {
		QueryableRequestSpecification queryableRequestSpecification = SpecificationQuerier.query(setLogs());
		logInfoDetails("Request url is "+queryableRequestSpecification.getBaseUri()+"/"+URL);
		logInfoDetails("Request method is "+queryableRequestSpecification.getMethod());
		logInfoDetails("Request Headers are "); 
		logHeaders(queryableRequestSpecification.getHeaders().asList());
		logInfoDetails("Request body is "); 

		Response getRequest=  setLogs().queryParam(queryParamkey,queryParamValue)
				.when().get(URL);
		return getRequest;
	}

	public  Response get() {
		return setLogs()
				.get();
	}

	public  Response getWithHeader(Map<String, String> headers, String URL) {
		QueryableRequestSpecification queryableRequestSpecification = SpecificationQuerier.query(setLogs());
		logInfoDetails("Request url is "+queryableRequestSpecification.getBaseUri()+"/"+URL);
		logInfoDetails("Request method is "+queryableRequestSpecification.getMethod());
		logInfoDetails("Request Headers are "); 
		logHeaders(queryableRequestSpecification.getHeaders().asList());
		logInfoDetails("Request body is "); 

		return setLogs()
				.when()
				.headers(headers)
				.get(URL);
	}

	
	public static Response delete(String URL) {
		
		QueryableRequestSpecification queryableRequestSpecification = SpecificationQuerier.query(setLogs());
		logInfoDetails("Request url is "+queryableRequestSpecification.getBaseUri()+"/"+URL);
		logInfoDetails("Request Headers are "); logHeaders(queryableRequestSpecification.getHeaders().asList());
		logInfoDetails("Request method is "+queryableRequestSpecification.getMethod());
		Response deleteRequest=  setLogs()
				.when().delete(URL);
		return deleteRequest;
	}

	public static Response deleteWithHeaderAndPathParam(Map<String, String> headers,
			JSONObject jsonObject, String URL) {

		return setLogs()
				.when()
				.headers(headers)
				.body(jsonObject)
				.delete(URL);
	}


	public static Response deleteWithHeaderAndPathParamWithoutRequestBody(
			Map<String, String> headers, String URL) throws Exception {
		return setLogs()
				.when()
				.headers(headers)
				.delete(URL);
	}
	
	public static ValidatableResponse enableResponseLog(Response response) {
		return response.then().log().all();
	}

	private static ContentType getContentType() {
		return ContentType.JSON;
	}

	public static void verifyContentType(Response response, String type) {
		if(response.getContentType().toLowerCase().contains(type.toLowerCase())) {
			reportLog("The Content type "+type+" matches the expected content type", "PASS");
		}else {
			reportLog("The Content type "+type+" does not match the expected content type "+response.getContentType(), "FAIL");	
		}
	}


	public static void verifyResponseCode(Response response, int code) {
		if(response.statusCode() == code) {
			logInfoDetails("Response status is "+response.getStatusCode());
			reportLog("The status code "+code+" matches the expected code", "PASS");

		}else {
			logInfoDetails("Response status is "+response.getStatusCode());
			reportLog("The status code "+response.statusCode()+" does not match the expected code "+code, "FAIL");	
		}
	}

	public static void verifyResponseIsNotEmptyString(Response response) {
		if(response.body() != null) {
			logInfoDetails("Response headers is ");
			logHeaders(response.getHeaders().asList());
			//logInfoDetails("Response body is ");
			reportLog("Response Body is available", "PASS");
			logJson(response.getBody().prettyPrint());
			logXml(response.getBody().prettyPrint());

		}else {
			logInfoDetails("Response headers is ");
			logHeaders(response.getHeaders().asList());
			//logInfoDetails("Response body is ");
			reportLog("Response Body is not available", "FAIL");
			logJson(response.getBody().prettyPrint());
			logXml(response.getBody().prettyPrint());
			
		}
	}
	public static void verifyResponseTime(Response response, long time) {
		if(response.timeIn(TimeUnit.SECONDS) <= time) {
			reportLog("The time taken "+response.time()/1000 +"sec with in the expected time", "PASS");
		}else {
			reportLog("The time taken "+response.time()/1000 +"sec is greater than expected SLA time "+time+"sec","FAIL");		
		}
	}


	public  void verifyContentWithKey(Response response, String key, String expVal) {
		
		if(response.getContentType().contains("json")) {
			//String responseString = response.prettyPrint();
			JsonPath jsonPath = response.jsonPath();
			String actValue = jsonPath.get(key);
			if(actValue.contains(expVal)) {
				reportLog("The JSON response has value "+expVal+" as expected. ", "PASS");

			}else {
				
				reportLog("The JSON response :"+actValue+" does not have the value "+expVal+" as expected. ", "FAIL");	

			}
		}
	}
	public  void verifyResponseXmlContent(Response response, String key, String expVal) {
		if(response.getContentType().contains("xml")) {
			//String responseString = response.prettyPrint();
			XmlPath xmlPath = response.xmlPath();
			String actValue = xmlPath.get(key);
			if(actValue.contains(expVal)) {
				reportLog("The XMl response has value "+expVal+" as expected. ", "PASS");

			}else {
				
				reportLog("The XMl response :"+actValue+" does not have the value "+expVal+" as expected. ", "FAIL");	

			}
		}
	}
	
	public  void verifyResponseTextContent(Response response, String expVal) {
		if(response.getContentType().contains("text/plain")) {
			//String responseString = response.prettyPrint();
			String responseString = response.getBody().asString();
			if(responseString.contains(expVal)) {
				reportLog("The text response has value "+expVal+" as expected. ", "PASS");
			}else {
				reportLog("The text response :"+responseString+" does not have the value "+expVal+" as expected. ", "FAIL");	
			}
		}
	}
	
	

	public  void verifyContentsWithKey(Response response, String key, String expVal) {
		if(response.getContentType().contains("json")) {
			//String responseString = response.prettyPrint();
			JsonPath jsonPath = response.jsonPath();
			List<String> actValue = jsonPath.getList(key);
			if(actValue.get(0).contains(expVal)) {
				reportLog("The JSON response has "+key+" and value "+expVal+" as expected. ", "PASS");
				//	reportLog("The JSON response "+responseString, "PASS");
			}else {
				reportLog("The JSON response :"+actValue+" does not have the value "+expVal+" as expected. ", "FAIL");	
				
				//reportLog("The JSON response "+responseString, "FAIL");
			}
		}
	}


	public static List<String> getContentsWithKey(Response response, String key) {
		if(response.getContentType().contains("json")) {
			JsonPath jsonPath = response.jsonPath();
			return jsonPath.getList(key);			
		}else {
			return null;
		}
	}

	public static String getContentWithKey(Response response, String key) {
		if(response.getContentType().contains("json")) {
			JsonPath jsonPath = response.jsonPath();
			return (String) jsonPath.get(key);			
		}else {
			return null;
		}
	}


	// newly added

	public  void verifyContentWithKey(Response response, String key, boolean expVal) {
		if(response.getContentType().contains("json")) {
			JsonPath jsonPath = response.jsonPath();

			String responseString = response.prettyPrint();
			boolean actValue = jsonPath.get(key);
			if(actValue==expVal) {
				reportLog("The JSON response "+key+" has value "+actValue+" as expected. ", "PASS");
				reportLog("The JSON response "+responseString, "PASS");
			}else {
				reportLog("The JSON response "+key+" has value "+actValue+" not as expected. ", "FAIL");	
				
				reportLog("The JSON response "+responseString, "FAIL");
			}
		}
	}

	public  void verifyContentWithKey(Response response, String key, int expVal) {
		if(response.getContentType().contains("json")) {
			String responseString = response.prettyPrint();
			JsonPath jsonPath = response.jsonPath();
			int actValue = jsonPath.get(key);
			if(actValue>=expVal) {
				reportLog("The JSON response has "+key+" value "+actValue+" as expected. ", "PASS");
				reportLog("The JSON response "+responseString, "PASS");
			}else {
				reportLog("The JSON response has "+key+" as: "+actValue+" not as expected. ", "FAIL");
				
				reportLog("The JSON response "+responseString, "FAIL");
			}
		}
	}

	public  void verifyContentsWithKeyNotNull(Response response, String key, Object expVal) {
		if(response.getContentType().contains("json")) {
			String responseString = response.prettyPrint();
			JsonPath jsonPath = response.jsonPath();
			List<Object> actValue = jsonPath.getList(key);
			if(actValue.get(0)!=expVal) {
				reportLog("The JSON response has "+key+" value not "+expVal+" as expected. ", "PASS");
				reportLog("The JSON response "+responseString, "PASS");

			}else {
				reportLog("The JSON response :"+actValue+" as not expected. ", "FAIL");
				reportLog("The JSON response "+responseString, "FAIL");
			}
		}
	}

	

	public void sleep(long seconds) {

		try {
			Thread.sleep(seconds);
		} catch (InterruptedException e) {
		} catch (Exception e) {
		} 
	}

	private static void  printRequestLogInReport(RequestSpecification requestSpecification) {

		QueryableRequestSpecification queryableRequestSpecification = SpecificationQuerier.query(requestSpecification);
		logInfoDetails("Request url is "+queryableRequestSpecification.getBaseUri());
		logInfoDetails("Request method is "+queryableRequestSpecification.getMethod());
		logInfoDetails("Request Headers are ");
		logHeaders(queryableRequestSpecification.getHeaders().asList());
		logInfoDetails("Request body is ");
		logJson(queryableRequestSpecification.getBody());

	}

	private static void  printResponseLogInReport(Response response) {
		logInfoDetails("Response status is "+response.getStatusCode());
		logInfoDetails("Response headers is ");
		logHeaders(response.getHeaders().asList());
		logInfoDetails("Response body is ");
		logJson(response.getBody().prettyPrint());

	}
	
	public static int getVauleFromArrayByDiffKeyVal(JSONArray dataArray, String key1 , String value, String key2) {
        for (int i = 0; i < dataArray.length(); i++) {
            JSONObject userObject = dataArray.getJSONObject(i);
            if (userObject.getString(key1).equals(value)) {
                return userObject.getInt(key2);
            }
        }
        return -1; 
    }

	
    public static int getUserIdByEmailId(JSONArray dataArray, String email) {
        for (int i = 0; i < dataArray.length(); i++) {
            JSONObject userObject = dataArray.getJSONObject(i);
            if (userObject.getString("emailId").equals(email)) {
                return userObject.getInt("userId");
            }
        }
        return -1; // Return -1 if the email is not found
    }



}

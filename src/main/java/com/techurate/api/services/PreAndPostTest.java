package com.techurate.api.services;

import static com.techurate.api.config.ConfigManage.getConfig;

import java.io.File;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import com.techurate.api.utilites.DataInputProvider;

import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PreAndPostTest extends BaseRESTAssured{
    public static String token;
    public static String profileIdValue;
    public static String payeeId;
	protected static RequestSpecification request;
	protected static Response response;
	protected  static EncoderConfig encoderConfig = new EncoderConfig();
	public String dataFileName, dataFileType;
	public static String sysId;
    @BeforeSuite
	public  static RequestSpecification setUp() {
		RestAssured.baseURI = "https://"+getConfig().server()+"/"+getConfig().resource();
		RestAssured.authentication=RestAssured.basic("admin", "y/$oUb45eFLI");
		return request = RestAssured.given().log().all();		
	}
        
    
    @DataProvider(name="fetchData")
	public  Object[][] getData(){
		if(dataFileType.equalsIgnoreCase("Excel"))
			return DataInputProvider.getSheet(dataFileName);	
		else if(dataFileType.equalsIgnoreCase("JSON")){
			Object[][] data = new Object[1][1];
			data[0][0] = new File("./data/"+dataFileName+"."+dataFileType);
			System.out.println(data[0][0]);
			return data;
		}else {
			return null;
		}
			
	}
    
    @AfterMethod
	public void afterMethod() {
		
	}
    
	}
	


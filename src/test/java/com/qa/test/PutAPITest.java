package com.qa.test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.data.Users1;

public class PutAPITest extends TestBase {
	

	TestBase testbase;
	String serviceurl;
	String putcallurl;
	String url;
	RestClient restClient;
	CloseableHttpResponse closeablehttpresponse;
	
	@BeforeMethod

	public void setup() throws ClientProtocolException, IOException {

		testbase = new TestBase();
		serviceurl = prop.getProperty("URL");
		putcallurl = prop.getProperty("Putcallurl");

		// https://reqres.in//api/users

		url = serviceurl + putcallurl;
	}
	@Test
	
	public void putAPITest() throws JsonGenerationException, JsonMappingException, IOException {
		
		restClient=new RestClient();
		
	   HashMap<String, String> hashMap= new HashMap<String, String>();
  	   hashMap.put("Content-Type", "application/json");
  	//add like below for whatever header request you have
 	  // headerMap.put("username", "sumit");
 	  // headerMap.put("password", "test@123");
 	   //headerMap.put("auth Token", "12345");
  	   
  	   //Jackson API: 
  	   
  	   ObjectMapper mapper= new ObjectMapper();
  	   Users1 users1= new Users1("morpheus", "zion resident");// expected users object 
  	   
  	   //Object to Json file conversion:
  	   mapper.writeValue(new File("D:\\RestAPI\\src\\main\\java\\com\\qa\\data\\users1.json"), users1);
  	   
  	   //java object to Json in String :
  	   
  	String users1JsonString= mapper.writeValueAsString(users1);
  	
  	System.out.println(users1JsonString);
  	
  	closeablehttpresponse= restClient.put(url, users1JsonString, hashMap);// call the API
  	
  	//Validate Response from API:
  	
  	//1.  Status code:
  	
  	int statuscode= closeablehttpresponse.getStatusLine().getStatusCode();
  	
  	Assert.assertEquals(statuscode, testbase.RESPONSE_STATUS_CODE_200);
  	
  	//2. Json String:
  	
  	String responsestring= EntityUtils.toString(closeablehttpresponse.getEntity(), "UTF-8");
  	
  	JSONObject responsejson= new JSONObject(responsestring);
  	
  	System.out.println("The response from API is--->"+ responsejson);
  	
  	//JSOn to Java object:
  	Users1 user1Resobj= mapper.readValue(responsestring, Users1.class);// actual users object 
  	
  	System.out.println(user1Resobj);
  	
  	Assert.assertTrue(users1.getName().equals(user1Resobj.getName()));
  	
  	Assert.assertTrue(users1.getJob().equals(user1Resobj.getJob()));
  	
    System.out.println(user1Resobj.getUpdatedAt());
  
		
	}

	

}

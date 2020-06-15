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
import com.qa.data.Users;

public class PostAPITest extends TestBase {
	

	TestBase testbase;
	String serviceurl;
	String apiurl;
	String url;
	RestClient restClient;
	CloseableHttpResponse closeablehttpresponse;
	
	@BeforeMethod

	public void setup() throws ClientProtocolException, IOException {

		testbase = new TestBase();
		serviceurl = prop.getProperty("URL");
		apiurl = prop.getProperty("ServiceURL");

		// https://reqres.in//api/users

		url = serviceurl + apiurl;
	}
	@Test
	
	public void postAPITest() throws JsonGenerationException, JsonMappingException, IOException {
		
		restClient=new RestClient();
		
	   HashMap<String, String> hashMap= new HashMap<String, String>();
  	   hashMap.put("Content-Type", "application/json");
  	//add like below for whatever header request you have
 	  // headerMap.put("username", "sumit");
 	  // headerMap.put("password", "test@123");
 	   //headerMap.put("auth Token", "12345");
  	   
  	   //Jackson API: 
  	   
  	   ObjectMapper mapper= new ObjectMapper();
  	   Users users= new Users("morpheus", "leader");// expected users object 
  	   
  	   //Object to Json file conversion:
  	   mapper.writeValue(new File("D:\\RestAPI\\src\\main\\java\\com\\qa\\data\\users.json"), users);
  	   
  	   //java object to Json in String :
  	   
  	String usersJsonString= mapper.writeValueAsString(users);
  	
  	System.out.println(usersJsonString);
  	
  	closeablehttpresponse= restClient.post(url, usersJsonString, hashMap);// call the API
  	
  	//Validate Response from API:
  	
  	//1.  Status code:
  	
  	int statuscode= closeablehttpresponse.getStatusLine().getStatusCode();
  	
  	Assert.assertEquals(statuscode, testbase.RESPONSE_STATUS_CODE_201);
  	
  	//2. Json String:
  	
  	String responsestring= EntityUtils.toString(closeablehttpresponse.getEntity(), "UTF-8");
  	
  	JSONObject responsejson= new JSONObject(responsestring);
  	
  	System.out.println("The response from API is--->"+ responsejson);
  	
  	//JSOn to Java object:
  	Users userResobj= mapper.readValue(responsestring, Users.class);// actual users object 
  	
  	System.out.println(userResobj);
  	
  	Assert.assertTrue(users.getName().equals(userResobj.getName()));
  	
  	Assert.assertTrue(users.getJob().equals(userResobj.getJob()));
  	
  	System.out.println(userResobj.getId());
  	
  	System.out.println(userResobj.getCreatedAt());
 
		
	}

	

}

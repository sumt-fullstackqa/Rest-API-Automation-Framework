package com.qa.test;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;

public class DeleteAPITest extends TestBase {
	
	
	TestBase testbase;
	String serviceurl;
	String deletecallurl;
	String url;
	RestClient restClient;
	CloseableHttpResponse closeablehttpresponse;

	@BeforeMethod

	public void setup() throws ClientProtocolException, IOException {

		testbase = new TestBase();
		serviceurl = prop.getProperty("URL");
		deletecallurl = prop.getProperty("Deletecallurl");

		// https://reqres.in//api/users

		url = serviceurl + deletecallurl;
	}
	@Test
	
	public void deleteAPITestwithHeaders() throws ClientProtocolException, IOException {
 	   restClient = new RestClient();
 	   
 	   HashMap<String, String> hashMap= new HashMap<String, String>();
 	   hashMap.put("Content-Type", "application/json");
 	   //add like below for whatever header request you have
 	  // headerMap.put("username", "sumit");
 	  // headerMap.put("password", "test@123");
 	   //headerMap.put("auth Token", "12345");
 
		closeablehttpresponse= restClient.delete(url, hashMap);
		
		//  Status code
				int Statuscode = closeablehttpresponse.getStatusLine().getStatusCode();// to get the status code
				System.out.println("Status code----------->" + Statuscode);
				
				Assert.assertEquals(Statuscode, testbase.RESPONSE_STATUS_CODE_204);
				

   				// 3. All Headers

   				Header[] headersarray = closeablehttpresponse.getAllHeaders();// to get all response headers

   				HashMap<String, String> allHeaders = new HashMap<String, String>();

   				for (Header header : headersarray) {
   					allHeaders.put(header.getName(), header.getValue());
   				}

   				System.out.println("Headers Array------>" + allHeaders);
	}


}

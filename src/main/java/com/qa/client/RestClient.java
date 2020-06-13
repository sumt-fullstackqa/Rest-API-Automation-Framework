package com.qa.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {

	// 1. Get Method without Headers:

	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url);// this is http get request
		CloseableHttpResponse closeablehttpresponse = httpClient.execute(httpget); // hit the GET url
		
		return closeablehttpresponse;	
}
	//2. Get Method with Headers:
	
	public CloseableHttpResponse get(String url, HashMap<String, String> headermap) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url);// this is http get request
		
		for(Map.Entry<String,String> entry : headermap.entrySet()) {
			
			httpget.addHeader(entry.getKey(), entry.getValue());
		}
		
		CloseableHttpResponse closeablehttpresponse = httpClient.execute(httpget); // hit the GET url
		
		return closeablehttpresponse;	
}
	
}

package pageclass ;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import baseclass.baseclass;

public class pageclass extends baseclass {
	
	public	CloseableHttpClient httpClient;
	public HttpGet httpget;
	public HttpPost httppost;
	
	//CloseableHttpResponse closeablehttpresponse = httpClient.execute(httpget);
	JSONObject responseJson;
	

	//get method
	public int getStatusCode() throws ClientProtocolException, IOException  {
		
		httpClient = HttpClients.createDefault();
		 httpget = new HttpGet(prop.getProperty("url"));
		
		CloseableHttpResponse closeablehttpresponse =  httpClient.execute(httpget);
		int statusCode = closeablehttpresponse.getStatusLine().getStatusCode();
		return statusCode;
	}	
	
	//json string
	public JSONObject getResponse() throws ParseException, IOException 
	{
		httpClient = HttpClients.createDefault();
		 httpget = new HttpGet(prop.getProperty("url"));
		CloseableHttpResponse closeablehttpresponse =  httpClient.execute(httpget);
		String responseString = EntityUtils.toString(closeablehttpresponse.getEntity(),"UTF-8");
		 return responseJson = new JSONObject(responseString);
	}
	
	//all headers
	public void getHeaders() throws ClientProtocolException, IOException 
	
	{
		httpClient = HttpClients.createDefault();
		 httpget = new HttpGet(prop.getProperty("url"));
		CloseableHttpResponse closeablehttpresponse =  httpClient.execute(httpget);
		Header[] headersArray = closeablehttpresponse.getAllHeaders();
		HashMap<String, String> allHeaders = new HashMap<String, String>();
		for(Header header :headersArray )
		{
			allHeaders.put(header.getName(), header.getValue());
		}
		
	}
	public CloseableHttpResponse post(String JsonString, HashMap<String, String> headermap) throws ClientProtocolException, IOException
	{
		httpClient = HttpClients.createDefault();
		 httppost = new HttpPost(prop.getProperty("url2"));
		 httppost.setEntity(new StringEntity(JsonString));
		 for(Map.Entry<String,String> entry : headermap.entrySet()){
				httppost.addHeader(entry.getKey(), entry.getValue());
			}
		 CloseableHttpResponse closeablehttpresponse = httpClient.execute(httppost);
		return closeablehttpresponse;
		
	}
}

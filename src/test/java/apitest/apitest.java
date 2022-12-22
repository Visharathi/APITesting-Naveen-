package apitest;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import baseclass.baseclass;
import pageclass.User;
import pageclass.pageclass;
import utility.TestUtil;


public class apitest extends baseclass {
	pageclass p;
	baseclass b;
	JSONObject responseJson1;
	
	
	@BeforeMethod
	public void setUp() throws ClientProtocolException, IOException{
		 b = new baseclass();
		 p = new pageclass();
		b.initialization();
		
		
	}

	@Test(priority=4, enabled = false)
	public void statusCodeTest() throws ClientProtocolException, IOException{
		int statusCode = p.getStatusCode();
		Assert.assertEquals(statusCode, 200);
	}
	@Test(priority=2, enabled = false)
	public void ResponseTest() throws ParseException, IOException{
		responseJson1=p.getResponse();
		String count = TestUtil.getValueByJPath(responseJson1, "/count");
		Assert.assertEquals(Integer.parseInt(count), 1425);
	}
	
	
	@Test(priority=3, enabled = false)
	public void ResponseTest2() throws ParseException, IOException{
		responseJson1=p.getResponse();
		String API = TestUtil.getValueByJPath(responseJson1, "/entries[0]/API");
		String Category = TestUtil.getValueByJPath(responseJson1, "/entries[0]/Category");
		String Description = TestUtil.getValueByJPath(responseJson1, "/entries[0]/Description");
		Assert.assertEquals(Description, "Resource to help get pets adopted");

	}
	
	@Test(priority=1)
	public void ResponsePost() throws ParseException, IOException{
		HashMap<String, String> headermap = new HashMap<String, String>();
		headermap.put("Content-Type", "application/json");
		headermap.put("Connection", "keep-alive");
		
		ObjectMapper objectMapper = new ObjectMapper();
		User u = new User("eve.holt@reqres.in","pistol");
		
		objectMapper.writeValue(new File("C:\\Users\\Visha\\eclipse-workspace\\TestAPIDemo\\src\\main\\java\\data\\user.json"), u);
		String JsonString = objectMapper.writeValueAsString(u);
		//System.out.println(JsonString);
		
		CloseableHttpResponse closeablehttpresponse = p.post(JsonString, headermap);
String responseString = EntityUtils.toString(closeablehttpresponse.getEntity(), "UTF-8");
		
	JSONObject responseJson = new JSONObject(responseString);

	System.out.println("The response from API is:"+ responseJson);
		
		//json to java object:
		User u1 = objectMapper.readValue(responseString, User.class); //actual users object
		
		String id = TestUtil.getValueByJPath(responseJson, "/id");
		String token = TestUtil.getValueByJPath(responseJson, "/token");
		System.out.println("The id is:"+ id);
		System.out.println("The token is:"+ token);
		
		Assert.assertTrue(u1.getId().equals("4"));
		
		
	}

}

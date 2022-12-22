package baseclass;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;



public class baseclass {
	
	public static Properties prop;
	

	public void initialization() throws ClientProtocolException, IOException{
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream("C:\\Users\\Visha\\eclipse-workspace\\TestAPIDemo\\src\\main\\java\\data\\config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
					}

		 

}}

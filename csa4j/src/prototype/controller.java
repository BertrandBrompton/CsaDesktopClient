package prototype;
import java.io.InputStream;
import java.net.URI;
import java.util.Hashtable;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.SyncBasicHttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;


public class controller {
	private static final String DEFAULT_URL = "http://localhost:3000/";
	private static final String HOST = "http://localhost";
	private static final int PORT = 3000;
	HttpClient httpclient = null;

	/*
	 * This class supports using http requests to the CSA application.
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	public void setup(){
		httpclient = new DefaultHttpClient();

		try {
			HttpGet httpget = new HttpGet("http://localhost:3000/");

			System.out.println("executing request " + httpget.getURI());

			// Create a response handler
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			String responseBody = httpclient.execute(httpget, responseHandler);
			System.out.println("----------------------------------------");
			System.out.println(responseBody);
			System.out.println("----------------------------------------");

		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			// When HttpClient instance is no longer needed,
			// shut down the connection manager to ensure
			// immediate deallocation of all system resources
			//httpclient.getConnectionManager().shutdown();
		}
	}

	// GET
	public void read(String url){
		HttpClient httpclient = new DefaultHttpClient();
		try {
			HttpGet httpget = new HttpGet(url);

			System.out.println("executing request " + httpget.getURI());

			// Create a response handler
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			String responseBody = httpclient.execute(httpget, responseHandler);
			System.out.println("----------------------------------------");
			System.out.println(responseBody);
			System.out.println("----------------------------------------");
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			// When HttpClient instance is no longer needed,
			// shut down the connection manager to ensure
			// immediate deallocation of all system resources
			httpclient.getConnectionManager().shutdown();
		}		
	}

	//PUT with an existing URI
	public void update(){
		try {

		} catch (Exception e) {
			// TODO: handle exception
		}		
	}

	//DELETE
	public void delete(){
		try {

		} catch (Exception e) {
			// TODO: handle exception
		}		
	}

	public void uri(){

	}

	//GET
	public void readAuth(String username, String password, String url){
		DefaultHttpClient httpclient = new DefaultHttpClient();
		try {
			HttpGet httpget = new HttpGet(url);

			httpclient.getCredentialsProvider().setCredentials(
					new AuthScope("localhost", 3000),
					new UsernamePasswordCredentials(username, password));

			System.out.println("executing request " + httpget.getURI());

			// Create a response handler
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			String responseBody = httpclient.execute(httpget, responseHandler);
			System.out.println("----------------------------------------");
			System.out.println(responseBody);
			System.out.println("----------------------------------------");		
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception: readAuth.");
		}finally{
			// When HttpClient instance is no longer needed,
			// shut down the connection manager to ensure
			// immediate deallocation of all system resources
			httpclient.getConnectionManager().shutdown();
		}
	}
	
	// PUT with a new URI with authentication
	// POST to a base URI returning a newly created URI
	public void create(String username, String password, Hashtable<String, String> params){
		//"ffgfdfffegr@sdf.com", "first", "1985", "false", "1234567", "surN"
		URIBuilder urib = new URIBuilder();
		urib.setHost(HOST);
		urib.setPort(PORT);
		
		//urib.addParameter("email", a[0]);
		//urib.addParameter("firstname", a[1]);
		//urib.addParameter("age", a[2]);
		//urib.addParameter("jobs", a[3]);
		//urib.addParameter("phone", a[4]);
		//urib.addParameter("surname", a[5]);
		//System.out.println();
		//for (String t : a) {
		//	      System.out.println(t);
		//}
	}
	
	// PUT with a new URI with fucking authentication
	// POST to a base URI returning a newly created URI
	public void createWithAuthentication(String uri){ //path,parameters (maybe hashmap/hashtable ) instead of String

		//use the uribuilder o create the uri (instead the one passed as a parameter in this method, http://en.wikipedia.org/wiki/URI_scheme

		//URIBuilder ub = new URIBuilder();
		//ub.addParameter(param, value);

		DefaultHttpClient httpclient = new DefaultHttpClient();
		try {

			//HttpGet httpget = new HttpGet(uri);

			//remmber that in RESTful standard, POST is responsible for create action

			HttpPost httpPost = new HttpPost(uri);

			httpclient.getCredentialsProvider().setCredentials(
					new AuthScope("localhost", 3000),
					new UsernamePasswordCredentials("admin", "taliesin"));


			//System.out.println("executing request " + httpget.getURI());


			// Create a response handler
			//ResponseHandler<String> responseHandler = new BasicResponseHandler();
			HttpResponse responseBody = httpclient.execute(httpPost);
			System.out.println("----------------------------------------");

			String response = EntityUtils.toString(responseBody.getEntity());

			System.out.println(response);
			System.out.println("----------------------------------------");		

			JSONObject jsonResponse = new JSONObject(response);
			JSONArray possibleMailError = jsonResponse.optJSONArray("email");
			if( (possibleMailError != null) && (possibleMailError.getString(0).equals("has already been taken")) ) {
				System.out.println("User with this email already exists");
			}
			else {
				System.out.println(jsonResponse.toString(2));
				String surname = jsonResponse.optString("surname");
				if(surname != null) {
					//surname was there and is not null
				}
				int id = jsonResponse.optInt("id");
				if(surname != null) {
					//id was there and is not null
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception: "+e.getMessage());
		}finally{
			// When HttpClient instance is no longer needed,
			// shut down the connection manager to ensure
			// immediate deallocation of all system resources
			httpclient.getConnectionManager().shutdown();
		}
	}


}

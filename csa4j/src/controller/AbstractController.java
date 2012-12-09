package controller;

import java.net.URI;
import java.util.Enumeration;
import java.util.Hashtable;

import model.Auth;
import model.Config;
import model.Resource;

import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class AbstractController {
	private static final String HOST = Config.getHost();
	private static final int PORT = Config.getPort();
	
	/**
	 * Gets all of specific resource.
	 * @return If it returns null, it failed.
	 */
	public JSONObject indexJSON(Resource resource){
		DefaultHttpClient httpclient = new DefaultHttpClient();
		String uri = null;
		JSONObject jsonResponse = null;
		try{
			if(resource == Resource.BROADCAST){
				uri = "http://localhost:3000/broadcasts.json";
			}else if(resource == Resource.USER){
				uri = "http://localhost:3000/users.json";
			}else {
				System.out.println("Resource incorrect or not declared for the indexJSON method.");
				return null;
			}
			HttpGet httpGet = new HttpGet(uri);

			Auth.set_auth(httpclient);
			
			HttpResponse responseBody = httpclient.execute(httpGet);
			System.out.println("----------------------------------------");

			String response = EntityUtils.toString(responseBody.getEntity());

			System.out.println(response);
			System.out.println("----------------------------------------");		
			System.out.println();
			
			Object json = new JSONTokener(response).nextValue();
			if (json instanceof JSONObject){
				  //you have an object
					json = new JSONObject(response);
					System.out.println(json.toString());
			}else if (json instanceof JSONArray){
				  //you have an array
					json = new JSONArray(response);
					System.out.println(json.toString());
			}
		} catch (Exception e){
			System.out.println("Message" + e.getMessage());
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return jsonResponse;		
	}
	
	/**
	 * 
	 * @param params
	 * @return If it fails it returns a JSONObject containing null, else it will return an actual object.
	 */
	public JSONObject createJSON(Hashtable<String, String> params){
		// Algorithm
		// ---------------
		// Build Uri
		// Make httpclient
		// Authenticate
		// Execute http data
		// Handle fail to auth
		// Handle json response
		
		DefaultHttpClient httpclient = new DefaultHttpClient();
		JSONObject jsonResponse = null;
		try {
			// Build Uri
			URIBuilder urib = new URIBuilder();
			urib.setHost(HOST);
			urib.setPort(PORT);
			
			Enumeration<String> e = params.keys();
			
			while(e.hasMoreElements()){		// Maps input params to the uri parameters.
				String ele = e.nextElement();				
				urib.addParameter(ele, params.get(ele));
			    System.out.println(ele + " + " + params.get(ele));
			}
			URI uri = urib.build();
			System.out.println(uri.toString());
			System.out.println();
			HttpPost httpPost = new HttpPost(uri);

			Auth.set_auth(httpclient);

			// Create a response handler
			//ResponseHandler<String> responseHandler = new BasicResponseHandler();
			HttpResponse responseBody = httpclient.execute(httpPost);
			System.out.println("----------------------------------------");

			String response = EntityUtils.toString(responseBody.getEntity());

			System.out.println(response);
			System.out.println("----------------------------------------");		

			jsonResponse = new JSONObject(response);

			JSONArray possibleMailError = jsonResponse.optJSONArray("email");
			if( (possibleMailError != null) && (possibleMailError.getString(0).equals("has already been taken")) ) {
				System.out.println("User with this email already exists");
			} else {		
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
		return jsonResponse;		
	}
	/**
	 * Example uri for user: "http://localhost:3000/users/42.json"
	 * Example uri for broadcast: "http://localhost:3000/broadcasts/20.json"
	 * @param uri
	 */
	public JSONObject showJSON(String uri){
		DefaultHttpClient httpclient = new DefaultHttpClient();
		try{
			HttpGet httpGet = new HttpGet(uri);

			Auth.set_auth(httpclient);

			//System.out.println("executing request " + httpget.getURI());

			HttpResponse responseBody = httpclient.execute(httpGet);
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
			return jsonResponse;
		} catch (Exception e){
			System.out.println("Message" + e.getMessage());
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return null;
	}
	
	public void destroy(){
		
	}
	
	//JSONArray possibleMailError = jsonResponse.optJSONArray("email");
	
	//if( (possibleMailError != null) && (possibleMailError.getString(0).equals("has already been taken")) ) {
	//	System.out.println("User with this email already exists");
	//}
	//else {
	//	System.out.println(jsonResponse.toString(2));
	//	String surname = jsonResponse.optString("surname");
		//if(surname != null) {
		//	//surname was there and is not null
		//}
	//	int id = jsonResponse.optInt("id");
		//if(id != null) {
		///	//id was there and is not null
		//}
	//}
}

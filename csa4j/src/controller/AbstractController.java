package controller;

import java.io.IOException;
import java.net.URI;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

import model.Auth;
import model.Config;
import model.Resource;
import model.response.Response;
import model.response.UserResponse;
import model.uri.UriMaker;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class AbstractController {
	private static final String HOST = Config.getHost();
	private static final int PORT = Config.getPort();
	
	/**
	 * Gets all of specific resource type - such as all Users, or all Broadcasts, depending on the resource type requested. 
	 * @return If it returns null, it failed.
	 */
	public JSONArray indexJSON(Resource resource){
		DefaultHttpClient httpclient = new DefaultHttpClient();
		String uri = null;
		JSONObject jsonResponse = null;
		Object json = null;
		try{
			if(resource == Resource.BROADCAST){
				uri = "http://localhost:3000/broadcasts.json"; //TODO: breaks DRY since it should come from config.
			}else if(resource == Resource.USER){
				uri = "http://localhost:3000/users.json"; //TODO: breaks DRY since it should come from config.
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
			
			json = new JSONTokener(response).nextValue();
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
		return (JSONArray) json;		
	}
	
	/**
	 * Create
	 * @param params
	 * @return If it fails it returns a JSONObject containing null, else it will return an actual object.
	 * Example: http://localhost:3000/users.json?user[grad_year]=1985&user[phone]=1234567&user[email]=roflmaozzz@productions.com&user[surname]=surN&user[firstname]=first&user[user_detail_attributes][login]=firstsurN&user[user_detail_attributes][password]=firstsurN
	 */
	public Response createJSON(Hashtable<String, String> params, Resource resource){
		// Algorithm
		// ---------------
		// Build Uri
		// Make httpclient
		// Authenticate
		// Execute http data
		// Handle fail to auth
		// Handle json response
		
		DefaultHttpClient httpclient = new DefaultHttpClient();
		Object json = null;
		Response res = new UserResponse();
		try {
			// Build Uri
			LinkedList<NameValuePair> ll = new LinkedList<NameValuePair>();			
			if(resource == Resource.USER){
				Enumeration<String> e = params.keys();
				while(e.hasMoreElements()){		
					String key = e.nextElement();
					if(key == "id"){
						ll.add(new BasicNameValuePair("id", params.get(key)));
					} else {
						String s = "user["+key+"]";
						ll.add(new BasicNameValuePair(s, params.get(key)));
					}			
				}				
			} else if (resource == Resource.BROADCAST){
				Enumeration<String> e = params.keys();
				while(e.hasMoreElements()){		
					String key = e.nextElement();
					if(key == "id"){
						ll.add(new BasicNameValuePair("id", params.get(key)));
					} else {
						String s = "broadcast["+key+"]";
						ll.add(new BasicNameValuePair(s, params.get(key)));
					}			
				}				
			} else {
				System.out.println("AbstractController Error: Nonexistant Resource.");
				return null;
			}

			UriMaker um = new UriMaker();
			URI uri = null;
			if(resource == Resource.USER){
				uri = um.simple_make(Resource.USER.get(), ll);
			}else if (resource == Resource.BROADCAST){
				uri = um.simple_make(Resource.BROADCAST.get(), ll);
			}else{
				System.out.println("Message: Unidentified resource by AbstractController");
			}
			
			HttpPost httpPost = new HttpPost(uri);
			System.out.println(httpPost.getURI());
			
			Auth.set_auth(httpclient);

			// Create a response handler

			HttpResponse responseBody = httpclient.execute(httpPost);
			System.out.println("----------------------------------------");

			String response = EntityUtils.toString(responseBody.getEntity());

			System.out.println(response);
			System.out.println("----------------------------------------");		
			//TODO: Fix this.
			//jsonResponse = new JSONObject(response);

			json = new JSONTokener(response).nextValue();
			if (json instanceof JSONObject){
				  //you have an object
					json = new JSONObject(response);
					System.out.println(json.toString());
			}else if (json instanceof JSONArray){
				  //you have an array
					json = new JSONArray(response);
					System.out.println(json.toString());
			}			
		} catch (JSONException | IOException e) { // JDK7.0 style catch using OR operator.
			
			System.out.println("Exception: "+e.getMessage());
		}finally{
			// When HttpClient instance is no longer needed,
			// shut down the connection manager to ensure
			// immediate deallocation of all system resources
			httpclient.getConnectionManager().shutdown();
		}
		return res;		
	}
	
	/**
	 * Example uri for user: "http://localhost:3000/users/42.json"
	 * Example uri for broadcast: "http://localhost:3000/broadcasts/20.json"
	 * @param uri
	 */
	public JSONObject showJSON(String uri){
		DefaultHttpClient httpclient = new DefaultHttpClient();
		JSONObject jsonResponse = null;
		try{
			HttpGet httpGet = new HttpGet(uri);

			Auth.set_auth(httpclient);

			//System.out.println("executing request " + httpget.getURI());

			HttpResponse responseBody = httpclient.execute(httpGet);
			System.out.println("----------------------------------------");

			String response = EntityUtils.toString(responseBody.getEntity());

			System.out.println(response);
			System.out.println("----------------------------------------");		

			jsonResponse = new JSONObject(response);			
		} catch (Exception e){
			System.out.println("Message" + e.getMessage());
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return jsonResponse;
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
	
	//	URIBuilder urib = new URIBuilder();
	//	urib.setHost(HOST);
	//	urib.setPort(PORT);
	//	
	//	Enumeration<String> e = params.keys();
	//	
	//	while(e.hasMoreElements()){		// Maps input params to the uri parameters.
	//		String ele = e.nextElement();				
	//		urib.addParameter(ele, params.get(ele));
	//	    System.out.println(ele + " + " + params.get(ele));
	//	}
	//	URI uri = urib.build();
	//	System.out.println(uri.toString());
	//	System.out.println();
}

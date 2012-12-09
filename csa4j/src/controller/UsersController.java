package controller;
import java.net.URI;
import java.util.Enumeration;
import java.util.Hashtable;

import model.Resource;

import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;


public class UsersController extends AbstractController{
	
	
	public UsersController(){
		
	}
	public void search(){
		JSONObject response = super.indexJSON(Resource.USER);
		//JSONArray array = response.names();
		//TODO: Parse it.
	}
	
	/**
	 * 
	 */
	public void index(){
		JSONObject response = super.indexJSON(Resource.USER);
		//TODO: Parse it.
	}
	
	/**
	 * Creates a new user. In RESTful standard, POST is responsible for create action.
	 * @param params
	 */
	public void create(Hashtable<String, String> params){
		JSONObject response = super.createJSON(params);
		//TODO: Parse it.
	}
	
	/**
	 * Creates a new user. Named as such because keyword new not allowed.
	 */
	public void newUser(){
		
	}
	public void edit(){
		
	}
	
	/**
	 * Shows a user's details. All this method does is parse it.
	 * Example Uri: http://localhost:3000/users/42.json
	 */
	public void show(String uri){
		JSONObject response = super.showJSON(uri);
		//TODO: Parse it.
	}
	/**
	 * Updates a user's details. Uses Puts for RESTful.
	 */
	public void update(String uri){
		JSONObject response = this.updateJSON(uri);
	}

	public void destroy(){
		
	}
	private JSONObject updateJSON(String uri) {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		JSONObject jsonResponse = null;
		try{
			HttpPut httpPuts = new HttpPut(uri);

			httpclient.getCredentialsProvider().setCredentials(
					new AuthScope("localhost", 3000),
					new UsernamePasswordCredentials("admin", "taliesin"));

			//System.out.println("executing request " + httpget.getURI());

			HttpResponse responseBody = httpclient.execute(httpPuts);
			System.out.println("----------------------------------------");

			String response = EntityUtils.toString(responseBody.getEntity());

			System.out.println(response);
			System.out.println("----------------------------------------");		

			jsonResponse = new JSONObject(response);
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
		} catch (Exception e){
			System.out.println("Message" + e.getMessage());
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return jsonResponse;	
	}
	
}

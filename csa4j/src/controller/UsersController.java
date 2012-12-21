package controller;
import java.net.URI;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;

import model.Auth;
import model.Resource;
import model.response.Response;
import model.response.UserResponse;
import model.uri.UriMaker;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Controller that is responsible for all User domain operations.
 * @author Bertrand
 *
 */
public class UsersController extends AbstractController{
	public void login(String username, String password){
		Auth.set_username(username);
		Auth.set_password(password);
	}
	
	//http://localhost:3000/users/search.json?q=chris
	/**
	 * Encapsulates the search functionality. It wraps around the searchJSON method providing URI building. In RESTful standard, GET is responsible for search action.
	 * @param query The search term to be queried.
	 */
	public Response search(String query){
		//TODO: Parse it.
		UriMaker um = new UriMaker();
		LinkedList<NameValuePair> ll = new LinkedList<NameValuePair>();
		ll.add(new BasicNameValuePair("q", query));
		URI uri = um.simple_make("/users/search.json", ll);
		return this.searchJSON(uri);
	}
	
	/**
	 * Provides index CRUD operation functionality. In RESTful standard, GET is responsible for index action.
	 */
	public void index(){
		JSONArray response = super.indexJSON(Resource.USER);
		//TODO: Parse it.
	}
	
	/**
	 * Creates a new user. In RESTful standard, POST is responsible for create action.
	 * @param params
	 */
	public void create(Hashtable<String, String> params){
		Response response = super.createJSON(params, Resource.USER);
		//TODO: Parse it.
	}
		
	/**
	 * Shows a user's details. In RESTful standard, GET is responsible for show action.
	 * Example Uri: http://localhost:3000/users/42.json
	 */
	public void show(String uri){
		JSONObject response = super.showJSON(uri);
		//TODO: Parse it.
	}
	
	/**
	 * Updates a user's details. In RESTful standard, PUTS is responsible for update action.
	 */
	public void update(String uri){
		Response response = this.updateJSON(uri);
	}

	public void destroy(String uri){
		super.destroyJSON(uri);
	}
	
	private Response updateJSON(String uri) {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		JSONObject jsonResponse = null;
		Object json = null;
		Response res = new UserResponse();
		try{
			HttpPut httpPuts = new HttpPut(uri);

			Auth.set_auth(httpclient);

			//System.out.println("executing request " + httpget.getURI());

			HttpResponse responseBody = httpclient.execute(httpPuts);
//			System.out.println("----------------------------------------");

			String response = EntityUtils.toString(responseBody.getEntity());

//			System.out.println(response);
			System.out.println("----------------------------------------");		

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
			//JSONArray possibleMailError = jsonResponse.optJSONArray("email");
		} catch (Exception e){
			System.out.println("Message" + e.getMessage());
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return res;	
	}
	private Response searchJSON(URI uri) {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		JSONObject jsonResponse = null;
		Object json = null;
		Response res = new UserResponse();
		try{
			HttpGet httpGet = new HttpGet(uri);

			Auth.set_auth(httpclient);

			//System.out.println("executing request " + httpget.getURI());

			HttpResponse responseBody = httpclient.execute(httpGet);
//			System.out.println("----------------------------------------");

			String response = EntityUtils.toString(responseBody.getEntity());

//			System.out.println(response);
			System.out.println("----------------------------------------");		

	//		jsonResponse = new JSONObject(response);
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
			//JSONArray possibleMailError = jsonResponse.optJSONArray("email");

		} catch (Exception e){
			System.out.println("Message" + e.getMessage());
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return res;	
	}
}

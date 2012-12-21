package controller;

import java.util.Hashtable;

import model.Resource;
import model.response.Response;

import org.json.JSONArray;
import org.json.JSONObject;
/**
 * This class handles the broadcasts. It does the parsing and URI building, wrapping around an AbstractController method.
 * @author Bertrand
 *
 */
public class BroadcastController extends AbstractController{
	
	/**
	 * Gets all broadcasts - upto limit of pagination, which I believe to be defaulted to 8.
	 */
	public void index(){
		JSONArray response = super.indexJSON(Resource.BROADCAST);
		//TODO: Parse it.
	}
	
	/**
	 * Creates a broadcast resource using parameters passed in.
	 * @param params
	 */
	public void create(Hashtable<String, String> params){
		Response response = super.createJSON(params, Resource.BROADCAST);
		//TODO: Parse it.
	}
	
	/**
	 * Shows a broadcast resource at target uri location.
	 * @param uri
	 */
	public void show(String uri){
		JSONObject response = super.showJSON(uri);
	}
	
	/**
	 * Destroys a target broadcast resource at target uri location.
	 * @param uri
	 */
	public void destroy(String uri){
		super.destroyJSON(uri);
	}
}

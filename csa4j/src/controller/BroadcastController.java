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
	
	public void index(){
		JSONArray response = super.indexJSON(Resource.BROADCAST);
		//TODO: Parse it.
	}
	
	public void create(Hashtable<String, String> params){
		Response response = super.createJSON(params, Resource.BROADCAST);
		//TODO: Parse it.
	}
	
	/**
	 * This method creates a new broadcast. It does not rely on inheritence.
	 */
	public void newBroadcast(){
		//TODO: Make broadcast respond properly to JSON.
	}
	public void show(String uri){
		JSONObject response = super.showJSON(uri);
		//TODO: Parse it.
	}
	public void destroy(){
		
	}
}

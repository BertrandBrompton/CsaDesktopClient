package controller;

import java.util.Hashtable;

import model.Resource;

import org.json.JSONObject;
/**
 * This class handles the broadcasts. It does the parsing and URI building, wrapping around an AbstractController method.
 * @author Bertrand
 *
 */
public class BroadcastController extends AbstractController{
	
	private static final String RESOURCE = "broadcasts"; // For the index method.
	
	public void index(){
		JSONObject response = super.indexJSON(Resource.BROADCAST);
		//TODO: Parse it.
	}
	
	public void create(Hashtable<String, String> params){
		JSONObject response = super.createJSON(params);
		//TODO: Parse it.
	}
	
	/**
	 * This method creates a new broadcast. It does not rely on inheritence.
	 */
	public void newBroadcast(){
		
	}
	public void show(String uri){
		JSONObject response = super.showJSON(uri);
		//TODO: Parse it.
	}
	public void destroy(){
		
	}
}

package model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class JsonHandler {
	
	public void checkJson(String response){
		//http://stackoverflow.com/questions/6118708/determine-whether-json-is-a-jsonobject-or-jsonarray
		Object json;
		try {
			json = new JSONTokener(response).nextValue();
			if (json instanceof JSONObject){
				  //you have an object
					json = new JSONObject(response);
				}else if (json instanceof JSONArray){
				  //you have an array
					json = new JSONArray(response);
					System.out.println(json.toString());
				}
		} catch (JSONException e) {
			System.out.println("Message: " + e);
		}
	}
}

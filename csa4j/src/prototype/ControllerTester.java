package prototype;

import java.util.Hashtable;

import model.response.Response;

import controller.BroadcastController;
import controller.UsersController;

public class ControllerTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		 * Controller initialisation.
		 */
		UsersController usc = new UsersController();
		BroadcastController bsc = new BroadcastController();
		/*
		 * Mock Data.
		 */
		Hashtable<String, String> create_params = new Hashtable<String, String>();		
		//"ffgfdfffegr@sdf.com", "first", "1985", "false", "1234567", "surN"
		create_params.put("email", "wdwdawd@jurasicParkzz.com");
		create_params.put("firstname", "first");
		create_params.put("grad_year", "1985");
		//create_params.put("jobs", "false");
		create_params.put("phone", "1234567");
		create_params.put("surname", "surN");
		
		Hashtable<String, String> broadcast_params = new Hashtable<String, String>();
		broadcast_params.put("content", "dwdwdwd");
		/*
		 * Controller tests.
		 */
		/*
		 * WORKING:
		 */
		//usc.search("chris");
		//usc.index(); // PAGINATION AFFECTS RESPONSE. MAX 6 BY DEFAULT.
		//usc.create(create_params);
		//usc.show("http://localhost:3000/users/68.json");
		//usc.update("http://localhost:3000/users/68.json?user[firstname]=jim");
		//usc.destroy("http://localhost:3000/users/68.json");
		
		//bsc.index(); // PAGINATION AFFECTS RESPONSE. MAX 8 BY DEFAULT.
		//bsc.create(broadcast_params); // gives odd errors.
		//bsc.show("http://localhost:3000/broadcasts/36.json");
		bsc.destroy("http://localhost:3000/broadcasts/36.json");
		
	}

}

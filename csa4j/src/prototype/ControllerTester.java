package prototype;

import java.util.Hashtable;

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
		create_params.put("email", "lolobunny@productions.com");
		create_params.put("firstname", "first");
		create_params.put("grad_year", "1985");
		//create_params.put("jobs", "false");
		create_params.put("phone", "1234567");
		create_params.put("surname", "surN");
		
		Hashtable<String, String> broadcast_params = new Hashtable<String, String>();
		broadcast_params.put("content", "meepoo1");
		/*
		 * Controller tests.
		 */
		/*
		 * WORKING:
		 */
		
		//usc.show("http://localhost:3000/users/42.json");
		//bsc.show("http://localhost:3000/broadcasts/20.json");
		//usc.index();
		//bsc.create(broadcast_params);
		//usc.create(create_params);		
		//bsc.index();
		
		// WIERD BUG:
		

		/*
		 * NOT WORKING:
		 */
		// URI Not working!
		//usc.update("http://localhost:3000/users/64.json?");
	}

}

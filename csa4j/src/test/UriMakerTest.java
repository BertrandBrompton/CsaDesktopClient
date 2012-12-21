package test;

import static org.junit.Assert.*;

import java.net.URI;
import java.util.LinkedList;

import model.uri.UriMaker;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Before;
import org.junit.Test;

import controller.UsersController;

public class UriMakerTest {
	UriMaker um;
	LinkedList<NameValuePair> ll;
	URI uri;
	
	@Before
	public void setUp() throws Exception {
		um = new UriMaker();
		ll = new LinkedList<NameValuePair>();
		/*
		 * 		create_params.put("email", "lolobunny@productions.com");
		create_params.put("firstname", "first");
		create_params.put("grad_year", "1985");
		//create_params.put("jobs", "false");
		create_params.put("phone", "1234567");
		create_params.put("surname", "surN");
		 */
		//ll.add();
		ll.add(new BasicNameValuePair("email","lolobunny@productions.com"));
	}

	@Test
	public void test_simple_make() {
		uri = um.simple_make("/users.json", ll);
		
		UsersController usc = new UsersController();
		
		//usc.show("http://localhost:3000/users/42.json");
		//bsc.show("http://localhost:3000/broadcasts/20.json");
		//usc.index();
		usc.search("chris");
		//usc.create(create_params);		
		//bsc.index();
		//usc.update("http://localhost:3000/users/64.json?user[firstname]=jim");
		// WIERD BUG:
		//bsc.create(broadcast_params); // gives odd errors.
		
		
		System.out.println(uri.toString());
		//assertEquals()
		
	}

}

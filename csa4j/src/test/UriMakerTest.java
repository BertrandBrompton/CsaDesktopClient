package test;

import static org.junit.Assert.*;

import java.net.URI;
import java.util.LinkedList;

import model.uri.UriMaker;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Before;
import org.junit.Test;

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
		System.out.println(uri.toString());
		//assertEquals()
		
	}

}

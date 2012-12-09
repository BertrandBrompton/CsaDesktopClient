package model;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.DefaultHttpClient;

public enum Auth {
	INSTANCE;
	
	private String username;
	private String password;
	
	public static String get_username(){
		return INSTANCE.username;
	}
	public static String get_password(){
		return INSTANCE.password;
	}
	public static void set_username(String user){
		INSTANCE.username = user;
	}
	public static void set_password(String passw){
		INSTANCE.password = passw;
	}
	
	/**
	 * Possible to use for DRY. It breaks security principles though since it is hard coded. 
	 * Usage: Auth.set_auth(httpclient);
	 * @param httpclient
	 */
	public static void set_auth(DefaultHttpClient httpclient){
		httpclient.getCredentialsProvider().setCredentials(
				new AuthScope("localhost", 3000),
				new UsernamePasswordCredentials("admin", "taliesin"));
	}
	
}

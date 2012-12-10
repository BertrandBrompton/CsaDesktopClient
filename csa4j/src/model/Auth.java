package model;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.DefaultHttpClient;
/**
 * Singleton Enum providing global authentication state for the CSA Desktop application.
 * @author Bertrand
 *
 */
public enum Auth {
	INSTANCE;
	
	private String username = "admin";
	private String password = "taliesin";
	
	/**
	 * Gets the username.
	 * @return
	 */
	public static String get_username(){
		return INSTANCE.username;
	}
	
	/**
	 * Gets the password.
	 * @return
	 */
	public static String get_password(){
		return INSTANCE.password;
	}
	
	/**
	 * Sets the username.
	 * @param user
	 */
	public static void set_username(String user){
		INSTANCE.username = user;
	}
	
	/**
	 * Sets the password.
	 * @param passw
	 */
	public static void set_password(String passw){
		INSTANCE.password = passw;
	}
	
	/**
	 * Utility function to encapsulate approach for httpclient authentication.  
	 * Usage: Auth.set_auth(httpclient);
	 * @param httpclient
	 */
	public static void set_auth(DefaultHttpClient httpclient){
		/*
		 * By default this section of code uses the admin credentials. If the user logs in, then they will use those credentials instead. 
		 */
		httpclient.getCredentialsProvider().setCredentials(
				new AuthScope(Config.getHost(), 3000),
				new UsernamePasswordCredentials(get_username(), get_password()));
	}
	
}

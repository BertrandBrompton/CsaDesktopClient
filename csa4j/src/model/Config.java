package model;
/**
 * Provides global configuration state information.
 * Is a singleton enum.
 * @author Bertrand
 *
 */
public enum Config {
	INSTANCE;
	
	private static final String HOST = "localhost";
	private static final int PORT = 3000;
	private static final String SCHEME = "http";
	
	/**
	 * Gets the host path.
	 * @return
	 */
	public static String getHost() {
		return INSTANCE.HOST;
	}
	
	/**
	 * Gets the port number.
	 * @return
	 */
	public static int getPort() {
		return INSTANCE.PORT;
	}
	
	/**
	 * Gets the scheme - such as http.
	 * @return
	 */
	public static String getScheme(){
		return INSTANCE.SCHEME;
	}
}

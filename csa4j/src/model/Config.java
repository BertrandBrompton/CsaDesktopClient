package model;

public enum Config {
	INSTANCE;
	
	private static final String HOST = "localhost";
	private static final int PORT = 3000;
	private static final String SCHEME = "http";
	
	public static String getHost() {
		return INSTANCE.HOST;
	}
	
	public static int getPort() {
		return INSTANCE.PORT;
	}
	
	public static String getScheme(){
		return INSTANCE.SCHEME;
	}
}

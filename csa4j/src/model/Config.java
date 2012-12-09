package model;

public enum Config {
	INSTANCE;
	
	private static final String HOST = "http://localhost";
	private static final int PORT = 3000;
	
	public static String getHost() {
		return INSTANCE.HOST;
	}
	
	public static int getPort() {
		return INSTANCE.PORT;
	}
}

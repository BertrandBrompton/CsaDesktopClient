package model;
/**
 * Enum representing a resource type.
 * @author Bertrand
 *
 */
public enum Resource {
	/**
	 * The two resource types are intrinsically linked to two basic locations. Though this may change.
	 */
	USER ("/users.json"), 
	BROADCAST ("/broadcasts.json");
	
	private final String path;

	Resource(String path) {
		this.path = path;
		
	}
	
	/**
	 * Gets the path tied to a certain resource as a String.
	 * @return
	 */
	public String get(){
		return path;
	}

}

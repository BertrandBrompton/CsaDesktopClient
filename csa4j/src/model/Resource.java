package model;

public enum Resource {
	USER ("/users.json", "/users/search.json"), 
	BROADCAST ("/broadcasts.json");
	
	private final String path;
	private final String alternate_path;
	
	Resource(String path, String alternate_path){
		this.path = path;
		this.alternate_path = alternate_path;
	}
	Resource(String path) {
		this.path = path;
		this.alternate_path = null;
	}
	public String get(){
		return path;
	}
	public String get_alt(){
		if(alternate_path != null){
			return alternate_path;
		}
		return alternate_path;
	}
}

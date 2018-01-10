package del.res.models;

public class Picture {
	private String fullUrl;
	private String name;
	
	public Picture() {}
	
	public void setUrl(String fullUrl) {
		this.fullUrl = fullUrl;
		
		//Removing Images/
		this.name= fullUrl.substring(7, fullUrl.length());
	}
	public String getFullUrl() {
		return fullUrl;
	}
	public String getName() {
		return name;
	}
	
	
}

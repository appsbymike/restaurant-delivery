package del.res.models;

public class PastReview {
	private String username;
	private String reviewText;
	private String id;
	
	public PastReview(String name, String text){
		this.username = name;
		this.reviewText = text;
	}
	
	public PastReview(String name, String text, String id){
		this.username = name;
		this.reviewText = text;
		this.id=id;
	}

	public String getUsername() {
		return username;
	}

	public String getReviewText() {
		return reviewText;
	}

	public String getId() {
		return id;
	}
	
}

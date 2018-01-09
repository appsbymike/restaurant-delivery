package del.res.bo;

import java.util.ArrayList;
import del.res.dao.*;
import del.res.models.Item;
import del.res.models.PastReview;

public class ReviewsBO {
	
	public int submitReview(String userID, String itemID, String text) {
		ReviewsDAO reviewsDAO = new ReviewsDAO();
		return reviewsDAO.submitReview(userID, itemID, text);
	}

	public Item getItemInfo(String itemID) {
		ReviewsDAO reviewsDAO = new ReviewsDAO();
		return reviewsDAO.getItemInfo(itemID);
	}

	public ArrayList<PastReview> getPastReviews(String itemID) {
		ReviewsDAO reviewsDAO = new ReviewsDAO();
		return reviewsDAO.getPastReviews(itemID);
	}

	public ArrayList<PastReview> getReviewsByUser(String userID) {
		ReviewsDAO reviewsDAO = new ReviewsDAO();
		return reviewsDAO.getReviewsByUser(userID);
	}

	public boolean deleteReview(String reviewID) {
		ReviewsDAO reviewsDAO = new ReviewsDAO();
		return reviewsDAO.deleteReview(reviewID);
	}
	
	public void deleteUserReviews(String userID) {
		ReviewsDAO reviewsDAO = new ReviewsDAO();
		reviewsDAO.deleteUserReviews(userID);
	}
}
package del.res.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import del.res.models.Item;
import del.res.models.PastReview;

public class ReviewsDAO extends DAO{
	
	//Submitting a review
	public int submitReview(String userID, String itemID, String text) {
		this.open();
		String sql = "INSERT INTO TP_REVIEWS (USER_ID, ITEM_ID, REVIEW_TEXT) "
				+ "VALUES (?,?,?)";
		int key = 0;
		try {
			PreparedStatement ps = conn.prepareStatement(sql,new String[] {"REVIEW_ID"});
			ps.setInt(1, Integer.parseInt(userID));
			ps.setInt(2, Integer.parseInt(itemID));
			ps.setString(3, text);
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()) {
				key = rs.getInt(1);
			}
			this.close();
			return key;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.close();
			return 0;
		}
	}
	
	public  Item getItemInfo(String itemID) {
		this.open();
		Item item = null;
		ResultSet rs = null;
		String sql = "SELECT ITEM_PICTURE, ITEM_NAME, ITEM_DESC, TO_CHAR(ITEM_PRICE,'$9,999.99'), ITEM_ID "
				+ "FROM TP_ITEMS "
				+ "WHERE ITEM_ID=?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(itemID));
			rs = ps.executeQuery();
			rs.next();
			item = new Item();
			item.setImageSrc(rs.getString(1));
			item.setItemName(rs.getString(2));
			item.setItemDesc(rs.getString(3));
			item.setItemPrice(rs.getString(4));
			item.setItemID(rs.getString(5));
			this.close();
			return item;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.close();
			return null;
		}
		
	}
	
	public  ArrayList<PastReview> getPastReviews(String itemID) {
		this.open();
		ResultSet rs = null;
		ArrayList<PastReview> reviews = null;
		String sql = "SELECT USER_FIRSTNAME, REVIEW_TEXT "
				+ "FROM TP_REVIEWS INNER JOIN TP_USERS ON TP_REVIEWS.USER_ID=TP_USERS.USER_ID "
				+ "WHERE ITEM_ID=? "
				+ "ORDER BY REVIEW_ID DESC";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(itemID));
			rs = ps.executeQuery();
			reviews = new ArrayList<PastReview> ();
			while(rs.next()) {
				PastReview pr = new PastReview(rs.getString(1),rs.getString(2));
				reviews.add(pr);
			}
			this.close();
			return reviews;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.close();
			return null;
		}
		
	}
	
	//Admin specific query
	public  ArrayList<PastReview> getReviewsByUser(String userID) {
		this.open();
		ResultSet rs = null;
		ArrayList<PastReview> reviews = null;
		String sql = "SELECT USER_FIRSTNAME, REVIEW_TEXT, REVIEW_ID "
				+ "FROM TP_REVIEWS INNER JOIN TP_USERS ON TP_REVIEWS.USER_ID=TP_USERS.USER_ID "
				+ "WHERE TP_REVIEWS.USER_ID=? "
				+ "ORDER BY REVIEW_ID DESC";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(userID));
			rs = ps.executeQuery();
			reviews = new ArrayList<PastReview> ();
			while(rs.next()) {
				PastReview pr = new PastReview(rs.getString(1),rs.getString(2),rs.getString(3));
				reviews.add(pr);
			}
			this.close();
			return reviews;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.close();
			return null;
		}
		
	}
	
	//Admin specific query
	public boolean deleteReview(String reviewID) {
		this.open();
		String sql = "DELETE FROM TP_REVIEWS WHERE REVIEW_ID=?";
		try {	
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(reviewID));
			int result = ps.executeUpdate();
			return (result != 0);
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//Test-specific query
	public void deleteUserReviews(String userID) {
		this.open();
		String sql = "DELETE FROM TP_REVIEWS WHERE USER_ID=?";
		try {	
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(userID));
			ps.executeQuery();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}

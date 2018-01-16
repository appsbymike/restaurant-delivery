package testng;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.hamcrest.collection.IsEmptyCollection;
import org.hamcrest.core.IsNot;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

import static org.hamcrest.beans.SamePropertyValuesAs.samePropertyValuesAs;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;

import del.res.bo.ReviewsBO;
import del.res.models.Item;
import del.res.models.PastReview;

public class ReviewsTest {
	String res = System.getProperty("user.dir") + "\\src\\main\\resources\\testng\\ReviewsTestRes\\";
	
	int createdReviewID;
	boolean createdReview;
	ReviewsBO reviewsBO;
	
	@BeforeMethod(alwaysRun=true)
	public void before() {
		reviewsBO = new ReviewsBO();
		createdReviewID = 0;
		createdReview = false;
	}
	//submitReview
	//Takes: (String) userID, itemID, text
	//Returns: (boolean) whether or not review was added
	@DataProvider(name="submitReview")
	public Iterator<Object[]> submitReviewData() throws IOException{
		ArrayList<Object[]> params = new ArrayList<Object[]>();
		File f = new File(res + "submitReview.csv");
		Reader in = new FileReader(f);
		Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);
		for(CSVRecord record : records) {
			Object[] paramSet = {
					record.get("description"),
					record.get("userID"),
					record.get("itemID"),
					record.get("text"),
					Boolean.parseBoolean(record.get("expected"))
			};
			params.add(paramSet);
		}
		return params.iterator();
	}
	@Test(dataProvider="submitReview", groups= {"functest", "usertest"})
	public void submitReviewTest(String desc, String userID, String itemID, String text, boolean expected) {
		createdReviewID = reviewsBO.submitReview(userID, itemID, text);
		boolean result = (createdReviewID != 0);
		createdReview = result;
		assertThat(result, equalTo(expected));
	}
	
	//getItemInfo
	//Takes: (String) itemID
	//Returns: <Item> (imageSrc, itemName, itemDesc, itemPrice, itemID)
	@DataProvider(name="getItemInfo")
	public Iterator<Object[]> getItemInfoData() throws IOException{
		ArrayList<Object[]> params = new ArrayList<Object[]>();
		File f = new File(res + "getItemInfo.csv");
		Reader in = new FileReader(f);
		Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);
		for(CSVRecord record : records) {
			Item expected = new Item();
			if(record.get("expected").equals("fail")) {
				expected = null;
			}
			else {
				expected.setImageSrc(record.get("image"));
				expected.setItemName(record.get("name"));
				expected.setItemDesc(record.get("desc"));
				expected.setItemPrice(record.get("price"));
				expected.setItemID(record.get("itemID"));
			}
			Object[] paramSet = {
					record.get("description"),
					record.get("itemID"),
					expected
			};
			params.add(paramSet);
		}
		return params.iterator();
	}
	
	@Test(dataProvider="getItemInfo", groups= {"functest", "usertest"})
	public void getItemInfoTest(String desc, String itemID, Item expected) {
		Item result = reviewsBO.getItemInfo(itemID);
		assertThat(result, samePropertyValuesAs(expected));
	}
	
	//getPastReviews
	//Takes: (String) itemID
	//Returns: (ArrayList<PastReview>) All reviews for a given item
	@DataProvider(name="getPastReviews")
	public Iterator<Object[]> getPastReviewsData() throws IOException{
		ArrayList<Object[]> params = new ArrayList<Object[]>();
		File f = new File(res + "getPastReviews.csv");
		Reader in = new FileReader(f);
		Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);
		for(CSVRecord record : records) {
			Object[] paramSet = {
					record.get("description"),
					record.get("itemID"),
					Boolean.parseBoolean(record.get("expected"))
			};
			params.add(paramSet);
		}
		return params.iterator();
	}
	
	@Test(dataProvider="getPastReviews", groups= {"functest", "usertest"})
	public void getPastReviewsTest(String desc, String itemID, boolean expected) {
		ArrayList<PastReview> result = reviewsBO.getPastReviews(itemID);
		if(!expected) {
			assertThat(result, IsEmptyCollection.emptyCollectionOf(PastReview.class));
		}
		else {
			assertThat(result, IsNot.not(IsEmptyCollection.emptyCollectionOf(PastReview.class)));
		}
	}
	
	//Admin Queries ===================================== //
	
	//getReviewsByUser
	//Takes: (String) userID
	//Returns: (ArrayList<PastReview>) All reviews by a given user
	@DataProvider(name="getReviewsByUser")
	public Iterator<Object[]> getReviewsByUserData() throws IOException{
		ArrayList<Object[]> params = new ArrayList<Object[]>();
		File f = new File(res + "getReviewsByUser.csv");
		Reader in = new FileReader(f);
		Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);
		for(CSVRecord record : records) {
			Object[] paramSet = {
					record.get("description"),
					record.get("userID"),
					Boolean.parseBoolean(record.get("expected"))
			};
			params.add(paramSet);
		}
		return params.iterator();
	}
	
	@Test(dataProvider="getReviewsByUser", groups= {"functest", "admintest"})
	public void getReviewsByUserTest(String desc, String userID, boolean expected) {
		ArrayList<PastReview> result = reviewsBO.getReviewsByUser(userID);
		if(!expected) {
			assertThat(result, IsEmptyCollection.emptyCollectionOf(PastReview.class));
		}
		else {
			assertThat(result, IsNot.not(IsEmptyCollection.emptyCollectionOf(PastReview.class)));
		}
	}
	
	@AfterMethod(alwaysRun=true)
	public void after() {
		if(createdReview) {
			reviewsBO.deleteReview(Integer.toString(createdReviewID));
		}
	}
}

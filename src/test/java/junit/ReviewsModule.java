package junit;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.hamcrest.core.IsEqual;
import org.junit.After;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import del.res.bo.ReviewsBO;
import del.res.models.PastReview;

@RunWith(Parameterized.class)
public class ReviewsModule {
	
	enum Type{GETITEM,GETREVIEW,SUBMITREVIEW};
	
	@Parameters(name = "CASE{index} - {1} | Expected: {5} | Parameters: {2},{3},{4}")
	public static Iterable<String[]> data() throws FileNotFoundException{
		ArrayList<String[]> parameters = new ArrayList<String[]>();
		File f = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\junit.test.data\\ReviewsModule");
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(f);
		while(sc.hasNextLine()) {
			parameters.add(sc.nextLine().split(","));
			
		}
		return parameters;
	}
	
	private Type type;
	private String itemID,userID,text;
	private boolean expected;
	
	public ReviewsModule(String type, String identifier, String itemID, String userID, String text, String expected) {
		this.type = (type.equals("GETITEM")) ? Type.GETITEM : (type.equals("GETREVIEW")) ? Type.GETREVIEW : Type.SUBMITREVIEW;
		this.itemID = itemID;
		this.userID = userID;
		this.text = text;
		this.expected = Boolean.parseBoolean(expected);
	}
	
	private ReviewsBO reviewsBO;
	public boolean createdReview;
	
	public boolean result;
	
	@Before
	public void before() {
		reviewsBO = new ReviewsBO();
		createdReview = false;
		result = false;
	}
	
	@Test
	public void GETITEM() {
		Assume.assumeTrue(type == Type.GETITEM);
		try {
			result = (reviewsBO.getItemInfo(itemID) != null) ? true : false;
			assertThat(result, IsEqual.equalTo(expected));
		}
		catch (Exception e) {
			if(expected) {
				e.printStackTrace();
				fail();
			}
		}
	}
	
	@Test
	public void GETREVIEW() {
		Assume.assumeTrue(type == Type.GETREVIEW);
		try {
			ArrayList<PastReview> reviews = reviewsBO.getPastReviews(itemID);
			result = (reviews.isEmpty() != true) ? true : false;
			assertThat(result, IsEqual.equalTo(expected));
		}
		catch (Exception e) {
			if(expected) {
				e.printStackTrace();
				fail();
			}
		}
	}
	
	@Test
	public void SUBMITREVIEW() {
		Assume.assumeTrue(type == Type.SUBMITREVIEW);
		try {
			result = reviewsBO.submitReview(userID, itemID, text);
			createdReview = result;
			assertThat(result, IsEqual.equalTo(expected));
		}
		catch (Exception e) {
			if(expected) {
				e.printStackTrace();
				fail();
			}
		}
	}
	
	@After
	public void after() {
		if(createdReview) {
			reviewsBO.deleteUserReviews(userID);		
		}		
	}
}

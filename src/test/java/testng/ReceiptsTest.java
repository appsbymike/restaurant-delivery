package testng;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.hamcrest.collection.IsEmptyCollection;
import org.hamcrest.core.IsNot;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import del.res.bo.ReceiptsBO;
import del.res.models.Item;
import del.res.models.ReceiptSummary;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.beans.SamePropertyValuesAs.samePropertyValuesAs;
import static org.hamcrest.Matchers.equalTo;

public class ReceiptsTest {
	String res = System.getProperty("user.dir") + "\\src\\main\\resources\\testng\\ReceiptsTestRes\\";
	
	ReceiptsBO receiptsBO;
	
	@BeforeMethod(alwaysRun=true)
	public void before() {
		receiptsBO = new ReceiptsBO();
	}
	
	//getReceiptSummary()
	//Takes: (int) orderID
	//Returns: <ReceiptSummary> Summary info for Order
	@DataProvider(name="getReceiptSummary")
	public Iterator<Object[]> getReceiptSummaryData() throws IOException{
		ArrayList<Object[]> params = new ArrayList<Object[]>();
		File f = new File(res + "getReceiptSummary.csv");
		Reader in = new FileReader(f);
		Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);
		for(CSVRecord record : records) {
			ReceiptSummary expected;
			if(record.get("expected").equals("fail")) {
				expected = null;
			}
			else {
				expected = new ReceiptSummary(
						Double.parseDouble(record.get("pretax")),
						Double.parseDouble(record.get("tax")),
						Integer.parseInt(record.get("itemCount")),
						record.get("name"),
						record.get("address")
						);
			}
			Object[] paramSet = {
					record.get("description"),
					Integer.parseInt(record.get("orderID")),
					expected
			};
			params.add(paramSet);
		}
		return params.iterator();
	}
	
	@Test(dataProvider="getReceiptSummary", groups= {"functest", "usertest"})
	public void getReceiptSummaryTest(String desc, int orderID, ReceiptSummary expected) {
		ReceiptSummary result = receiptsBO.getReceiptSummary(orderID);
		assertThat(result, samePropertyValuesAs(expected));
	}
	
	//validateUser()
	//Takes: (int) orderID, userID
	//Returns: (boolean) whether or not order was created by this user
	//Validation: true or false
	@DataProvider(name="validateUser")
	public Iterator<Object[]> validateUserData() throws IOException{
		ArrayList<Object[]> params = new ArrayList<Object[]>();
		File f = new File(res + "validateUser.csv");
		Reader in = new FileReader(f);
		Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);
		for(CSVRecord record : records) {
			Object[] paramSet = {
					record.get("description"),
					Integer.parseInt(record.get("orderID")),
					Integer.parseInt(record.get("userID")),
					Boolean.parseBoolean(record.get("expected"))
			};
			params.add(paramSet);
		}
		return params.iterator();
	}
	
	@Test(dataProvider="validateUser", groups= {"functest", "usertest"})
	public void validateUserTest(String description, int orderID, int userID, boolean expected) {
		boolean result = receiptsBO.validateUser(orderID, userID);
		assertThat(result,equalTo(expected));
	}
	
	//getReceiptItems()
	//Takes: (int) orderID
	//Returns: (ArrayList<Item>) all <Item>s in an order
	//Validation: Expected ArrayList<Item> containing imageSrc, itemPrice, and itemName
	@DataProvider(name="getReceiptItems")
	public Iterator<Object[]> getReceiptItemsData() throws IOException{
		ArrayList<Object[]> params = new ArrayList<Object[]>();
		File f = new File(res + "getReceiptItems.csv");
		Reader in = new FileReader(f);
		Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);
		for(CSVRecord record : records) {
			Object[] paramSet = {
					record.get("description"),
					Integer.parseInt(record.get("orderID")),
					Boolean.parseBoolean(record.get("expected"))
			};
			params.add(paramSet);
		}
		return params.iterator();
	}
	
	@Test(dataProvider="getReceiptItems", groups= {"functest", "usertest"})
	public void getReceiptItemsTest(String description, int orderID, boolean expected) {
		ArrayList<Item> result = receiptsBO.getReceiptItems(orderID);
		if(!expected) {
			assertThat(result, IsEmptyCollection.emptyCollectionOf(Item.class));
		}
		else {
			assertThat(result, IsNot.not(IsEmptyCollection.emptyCollectionOf(Item.class)));
		}
	}
}

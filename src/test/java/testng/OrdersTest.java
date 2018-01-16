package testng;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.fail;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.hamcrest.collection.IsEmptyCollection;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNot;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import del.res.bo.OrdersBO;
import del.res.models.*;

public class OrdersTest {
	String res = System.getProperty("user.dir") + "\\src\\main\\resources\\testng\\OrdersTestRes\\";
	
	OrdersBO ordersBO;
	boolean orderCreated;
	String createdOrderID;
	boolean orderItemCreated;
	int orderitemOrderID;
	int orderitemItemID;
	
	@BeforeMethod(alwaysRun=true)
	public void before() {
		ordersBO = new OrdersBO();
		orderCreated = false;
		createdOrderID = "";
		orderItemCreated = false;
		orderitemOrderID = 0;
		orderitemItemID = 0;
	}	
	
	//createOrder()
	//Takes: (String) user_id, store_id, cc_number, sec_number, zipcode, pretax_revenue, tax_revenue
	//Returns: (int) generated primary key
	//Note: save generated primary key for future use
	//Validation: Check that generated key is not 0
	@DataProvider(name="createOrder")
	public Iterator<Object[]> createOrderData() throws IOException{
		ArrayList<Object[]> params = new ArrayList<Object[]>();
		File f = new File(res + "createOrder.csv");
		Reader in = new FileReader(f);
		Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);
		for(CSVRecord record : records) {
			Object[] paramSet = {
					record.get("description"),
					record.get("user_id"),
					record.get("store_id"),
					record.get("cc_number"),
					record.get("sec_number"),
					record.get("zipcode"),
					record.get("pretax_revenue"),
					record.get("tax_revenue"),
					Boolean.parseBoolean(record.get("expected"))
			};
			params.add(paramSet);
		}
		return params.iterator();
	}
	
	@Test(dataProvider = "createOrder", groups= {"functest", "usertest"})
	public void createOrderTest(String description, String user_id, String store_id, String cc_number, String sec_number, String zipcode, String pretax_revenue, String tax_revenue, boolean expected) {
		int orderID = ordersBO.createOrder(user_id, store_id, cc_number, sec_number, zipcode, pretax_revenue, tax_revenue);
		boolean result = (orderID != 0);
		orderCreated = result;
		createdOrderID = Integer.toString(orderID);
		assertThat(result,IsEqual.equalTo(expected));
	}
	
	
	
	//addItemToOrder()
	//Takes: (int) order_id, item_id
	//Returns: (int) number of rows updated
	//Validation: check returned rows is not 0
	
	@DataProvider(name="addItemToOrder")
	public Iterator<Object[]> addItemToOrderData() throws IOException{
		ArrayList<Object[]> params = new ArrayList<Object[]>();
		File f = new File(res + "addItemToOrder.csv");
		Reader in = new FileReader(f);
		Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);
		for(CSVRecord record : records) {
			Object[] paramSet = {
					record.get("description"),
					Integer.parseInt(record.get("orderID")),
					Integer.parseInt(record.get("itemID")),
					Boolean.parseBoolean(record.get("expected"))
			};
			params.add(paramSet);
		}
		return params.iterator();
	}	
	
	@Test(dataProvider="addItemToOrder", groups= {"functest", "usertest"})
	public void addItemToOrderTest(String description, int orderID, int itemID, boolean expected) {
		boolean result;
		try {
			result = (ordersBO.addItemToOrder(orderID, itemID) != 0);
			orderitemOrderID = orderID;
			orderitemItemID = itemID;
			orderItemCreated = result;
			assertThat(result,IsEqual.equalTo(expected));
		} catch (Exception e) {
			if(expected) {
				fail();
			}
		}
	}
	
	//getOrdersByUser()
	//Takes: (int) userID
	//Returns: (ArrayList<PastOrder>) list of all <PastOrder>s a user has made
	@DataProvider(name="getOrdersByUser")
	public Iterator<Object[]> getOrdersByUserData() throws IOException{
		ArrayList<Object[]> params = new ArrayList<Object[]>();
		File f = new File(res + "getOrdersByUser.csv");
		Reader in = new FileReader(f);
		Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);
		for(CSVRecord record : records) {
			Object[] paramSet = {
					record.get("description"),
					Integer.parseInt(record.get("userID")),
					Boolean.parseBoolean(record.get("expected"))
			};
			params.add(paramSet);
		}
		return params.iterator();
	}
	
	@Test(dataProvider="getOrdersByUser", groups= {"functest", "usertest"})
	public void getOrdersByUserTest(String description, int userID, boolean expected) {
		try {
			System.out.println(expected);
			ArrayList<PastOrder> resultList = ordersBO.getOrdersByUser(userID);
			if(expected) {
				assertThat(resultList,IsNot.not(IsEmptyCollection.emptyCollectionOf(PastOrder.class)));
			}
			else {
				assertThat(resultList,IsEmptyCollection.emptyCollectionOf(PastOrder.class));
			}
		}
		catch (Exception e) {
			if(expected) {
				fail();
			}
		}
	}
	
	//Admin Queries ========================================================== //
	
	//getAllOrders()
	//Takes: nothing
	//Returns: (ArrayList<ReceiptSummary>) Summaries of all order made by all users
	
	@Test(groups= {"functest", "admintest"})
	public void getAllOrdersTest() {
		ArrayList<ReceiptSummary> result = ordersBO.getAllOrders();
		assertThat(result,IsNot.not(IsEmptyCollection.emptyCollectionOf(ReceiptSummary.class)));
	}
	
	@AfterMethod(alwaysRun=true)
	public void after() {
		if(orderCreated) {
			ordersBO.deleteOrder(createdOrderID);
		}
		if(orderItemCreated) {
			ordersBO.deleteOrderItem(orderitemOrderID, orderitemItemID);
		}
	}
}
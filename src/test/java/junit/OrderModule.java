package junit;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import del.res.bo.OrdersBO;

@RunWith(Parameterized.class)
public class OrderModule {
	
	@Parameters(name="CASE{index} - {0} | Expected: {4} | Parameters: {1},{2},{3}")
	public static Iterable<String[]> data() throws FileNotFoundException {
		File f = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\junit.test.data\\OrderModule");
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(f);
		ArrayList<String[]> parameters = new ArrayList<String[]>();
		while(sc.hasNextLine()) {
			parameters.add(sc.nextLine().split(","));
		}
		return parameters;
	}	
	
	//Values defined by user input
	private String identifier,cc_number,sec_number,zipcode;
	//Results
	private boolean expResults,createdOrder;
	
	public OrderModule(String identifier, String cc_number, String sec_number, String zipcode, String expResults) {
		this.identifier = identifier;
		this.cc_number = cc_number;
		this.sec_number = sec_number;
		this.zipcode = zipcode;
		this.expResults = Boolean.parseBoolean(expResults);
	}
	
	private OrdersBO ordersBO;
	int orderID;
	
	@Before
	public void before() {
		ordersBO = new OrdersBO();
		orderID = 0;
		createdOrder = false;
		
	}
	
	@Test
	public void test() {
		orderID = ordersBO.createOrder("1", "1", cc_number, sec_number, zipcode, "1.00", "1.00");
		createdOrder = (orderID != 0);
		assertEquals(identifier,expResults,createdOrder);
	}
	
	@After
	public void after() {
		if(createdOrder) {
			//Delete the order
			ordersBO.deleteOrder(Integer.toString(orderID));
		}
	}
	
}
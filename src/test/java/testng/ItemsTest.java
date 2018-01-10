package testng;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.hamcrest.Matchers;
import org.hamcrest.beans.HasPropertyWithValue;
import org.hamcrest.collection.IsIn;
import org.hamcrest.core.Every;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsInstanceOf;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.fail;

import del.res.bo.ItemsBO;
import del.res.models.Item;

public class ItemsTest {
	String res = System.getProperty("user.dir") + "\\src\\main\\resources\\testng\\ItemsTestRes\\";
	
	ItemsBO itemsBO;
	String thisItemID;
	Item oldItem;
	
	boolean createdItem;
	boolean updatedItem;
	
	@BeforeMethod
	public void before() {
		itemsBO = new ItemsBO();
		thisItemID = "";
		oldItem = null;
		createdItem = false;
		updatedItem = false;
	}
	
	
	//getAllActiveItems()
	//Takes: Nothing
	//Returns: (ArrayList<Item>) all items where ITEM_ISACTIVE = 1
	//Datastore: None
	//Validator: Count of items
	@Test
	public void getAllActiveItemsTest() {
		ArrayList<Item> result = itemsBO.getAllActiveItems();
		//Check that every item has a property of "itemIsActive" with a value of "1"
		assertThat(result,Every.everyItem(HasPropertyWithValue.hasProperty("itemIsActive", Is.is("1"))));
	}	
	
	//getItemsCount()
	//Takes: Nothing
	//Returns: (int) count of items
	//Datastore: None
	//Validator: Count of items
	@Test
	public void getItemsCountTest() {
		Integer result = itemsBO.getItemsCount();
		assertThat(result,Matchers.greaterThan(0));
	}
	
	
	//getCartItems()
	//Takes: (HashSet<Integer>) IDs of items in cart
	//Returns: (ArrayList<Item>) All <Item>s in cart
	//Datastore: Item IDs
	//Validator: Expected List of Item Names
	@DataProvider(name="getCartItems")
	public Iterator<Object[]> getCartItemsData() throws IOException{
		ArrayList<Object[]> params = new ArrayList<Object[]>();
		File f = new File(res + "getCartItems.csv");
		Reader in = new FileReader(f);
		Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);
		for(CSVRecord record : records) {
			HashSet<Integer> hs = new HashSet<Integer>();
			String[] IDs = record.get("cart").split(";");
			for(String id : IDs) {
				hs.add(Integer.parseInt(id));
			}
			ArrayList<String> expected;
			if(record.get("expected").equals("fail")) {
				expected = null;
			}
			else {
				expected = new ArrayList<String>(Arrays.asList(record.get("expected").split(";")));
			}
			System.out.println(expected);
			Object[] paramSet = {record.get("description"),hs,expected};
			params.add(paramSet);
		}
		
		return params.iterator();
	}
	
	@Test(dataProvider="getCartItems")
	public void getCartItemsTest(String description, HashSet<Integer> cart, ArrayList<String> expected) {
		try {
			ArrayList<Item> result = itemsBO.getCartItems(cart);
			assertThat(result,Every.everyItem(HasPropertyWithValue.hasProperty("itemName", IsIn.isIn(expected))));
		}
		catch (Exception e) {
			if(expected != null) {
				fail();
			}
		}
		
	}
	
	//getTotals()
	//Takes: (HashSet<Integer>) IDs of items in cart
	//Returns: (ArrayList<Double>) Totals of all items in cart
	//Datastore: Item IDs
	//Validator: Expected preTax
	
	@DataProvider(name="getTotals")
	public Iterator<Object[]> getTotalsData() throws IOException {
		ArrayList<Object[]> params = new ArrayList<Object[]>();
		File f = new File(res + "getTotals.csv");
		Reader in = new FileReader(f);
		Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);
		for(CSVRecord record : records) {
			HashSet<Integer> hs = new HashSet<Integer>();
			String[] IDs = record.get("cart").split(";");
			for(String id : IDs) {
				hs.add(Integer.parseInt(id));
			}
			Double expected = Double.parseDouble(record.get("expected"));
			
			Object[] paramSet = {record.get("description"), hs, expected};
			
			params.add(paramSet);
		}
		return params.iterator();
	}
	
	@Test(dataProvider="getTotals")
	public void getTotalsTest(String description, HashSet<Integer> cart, Double expected) {
		ArrayList<Double> result = itemsBO.getTotals(cart);
		assertThat(result.get(0),IsEqual.equalTo(expected));
		
	}
	
	//Admin Queries ========================================================== //
	
	//getAllItems()
	//Takes: Nothing
	//Returns: (ArrayList<Item>) All <Item>s that exist in the database
	//Datastore: None
	//Validator: Count of items
	@Test
	public void getAllItemsTest() {
		ArrayList<Item> result = itemsBO.getAllItems();
		assertThat(result,Every.everyItem(IsInstanceOf.instanceOf(Item.class)));
	}	
	
	//updateItem()
	//Takes: (<Item>) All new item information
	//Returns: (Boolean) Whether or not item was successfully updated
	//Datastore: Name, Desc, Price, Picture, IsActive, Category, ID
	//Validator: True or False
	@DataProvider(name="updateItem")
	public Iterator<Object[]> updateItemData() throws IOException{
		ArrayList<Object[]> params = new ArrayList<Object[]>();
		File f = new File(res + "updateItem.csv");
		Reader in = new FileReader(f);
		Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);
		for(CSVRecord record : records) {
			String[] properties = {
					record.get("name"),
					record.get("desc"),
					record.get("price"),
					record.get("image"),
					record.get("active"),
					record.get("category"),
					record.get("id")
					};
			ArrayList<String> props = new ArrayList<String>(Arrays.asList(properties));
			boolean expected = Boolean.parseBoolean(record.get("expected"));
			
			Object[] paramSet = {record.get("description"), props, expected};
			params.add(paramSet);
		}
		return params.iterator();
	}
	
	@Test(dataProvider="updateItem")
	public void updateItemTest(String description, ArrayList<String> props, boolean expected) {
		//Set up the old item
		oldItem = itemsBO.getItemByID(props.get(6));
		
		//Set up the new item
		Item item = new Item();
		item.setItemName(props.get(0));
		item.setItemDesc(props.get(1));
		item.setItemPrice(props.get(2));
		item.setImageSrc(props.get(3));
		item.setItemIsActive(props.get(4));
		item.setItemCategory(props.get(5));
		item.setItemID(props.get(6));
		
		try {
			boolean result = itemsBO.updateItem(item);
			updatedItem = result;
			
			assertThat(result,IsEqual.equalTo(expected));
		}
		catch (Exception e) {
			if(expected) {
				fail();
			}
		}
	}
	
	//addItem()
	//Takes: (<Item>) All new item information
	//Returns: (Boolean) Whether or not item was successfully added
	//Datastore: Name, Desc, Price, Picture, IsActive, Category
	//Validator: True or False
	@DataProvider(name="addItem")
	public Iterator<Object[]> addItemData() throws IOException{
		ArrayList<Object[]> params = new ArrayList<Object[]>();
		File f = new File(res + "addItem.csv");
		Reader in = new FileReader(f);
		Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);
		for(CSVRecord record : records) {
			String[] properties = {
					record.get("name"),
					record.get("desc"),
					record.get("price"),
					record.get("image"),
					record.get("active"),
					record.get("category")
					};
			ArrayList<String> props = new ArrayList<String>(Arrays.asList(properties));
			boolean expected = Boolean.parseBoolean(record.get("expected"));
			
			Object[] paramSet = {record.get("description"), props, expected};
			params.add(paramSet);
		}
		return params.iterator();
	}
	
	@Test(dataProvider="addItem")
	public void addItemTest(String description, ArrayList<String> props, boolean expected) {		
		//Set up the new item
		Item item = new Item();
		item.setItemName(props.get(0));
		item.setItemDesc(props.get(1));
		item.setItemPrice(props.get(2));
		item.setImageSrc(props.get(3));
		item.setItemIsActive(props.get(4));
		item.setItemCategory(props.get(5));
		
		try {
			int id = itemsBO.addItem(item);
			boolean result = (id != 0);
			createdItem = result;
			thisItemID = Integer.toString(id);
			
			assertThat(result,IsEqual.equalTo(expected));
		}
		catch (Exception e) {
			if(expected) {
				fail();
			}
		}
	}
	
	@AfterMethod
	public void After() {
		if(updatedItem) {
			System.out.println("Reverted to previous item. Item ID: " + oldItem.getItemID());
			itemsBO.updateItem(oldItem);
		}
		if(createdItem) {
			System.out.println("Deleted item with ID: " + thisItemID);
			itemsBO.removeItem(thisItemID);
		}
	}
}

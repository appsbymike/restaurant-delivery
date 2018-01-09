package testng;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import del.res.bo.UsersBO;
import del.res.models.User;

public class UsersTest {
	
	UsersBO usersBO;
	String thisUsername;
	User user;
	boolean createdUser;
	boolean updatedUser;
	
	@BeforeMethod
	public void before() {
		usersBO = new UsersBO();
		thisUsername = "";
		user = null;
		createdUser = false;
		updatedUser = false;
	}
	
	@DataProvider(name="registerQuery")
	public Iterator<Object[]> registerQueryData() throws IOException{
		ArrayList<Object[]> params = new ArrayList<Object[]>();
		File f = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\testng\\UsersTestRes\\registerQuery.csv");
		Reader in = new FileReader(f);
		Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);
		for(CSVRecord record : records) {
			Object[] paramSet = {
					record.get("description"),
					record.get("username"),
					Boolean.parseBoolean(record.get("expected"))
			};
			System.out.println(Arrays.toString(paramSet));
			params.add(paramSet);
		}
		return params.iterator();
	}
	
	@Test(dataProvider="registerQuery", groups="Registration")
	public void registerQueryTest(String description, String username, boolean expected) {
		boolean result = usersBO.registerQuery(username);
		assertThat(result, equalTo(expected));
	}
	

	@DataProvider(name="createUser")
	public Iterator<Object[]> createUserData() throws IOException{
		ArrayList<Object[]> params = new ArrayList<Object[]>();
		File f = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\testng\\UsersTestRes\\createUser.csv");
		Reader in = new FileReader(f);
		Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);
		for(CSVRecord record : records) {
			Object[] paramSet = {
					record.get("description"),
					record.get("username"),
					record.get("password"),
					record.get("firstname"),
					record.get("lastname"),
					record.get("gender"),
					record.get("address"),
					record.get("phone"),
					record.get("email"),
					Integer.parseInt(record.get("expected"))
			};
			System.out.println(Arrays.toString(paramSet));
			params.add(paramSet);
		}
		return params.iterator();
	}
	
	@Test(dataProvider="createUser", groups="Registration")
	public void createUserTest(String description, String username, String password, String firstname, String lastname, String gender, String address, String phone, String email, int expected) {
		int result = usersBO.createUser(username, password, firstname, lastname, gender, address, phone, email);
		thisUsername = username;
		createdUser = (result != 0);
		assertThat(result,equalTo(expected));
	}
	
	@DataProvider(name="loginQuery")
	public Iterator<Object[]> loginQueryData() throws IOException{
		ArrayList<Object[]> params = new ArrayList<Object[]>();
		File f = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\testng\\UsersTestRes\\loginQuery.csv");
		Reader in = new FileReader(f);
		Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);
		for(CSVRecord record : records) {
			Object[] paramSet = {
					record.get("description"),
					record.get("username"),
					record.get("password"),
					record.get("expected")
			};
			System.out.println(Arrays.toString(paramSet));
			params.add(paramSet);
		}
		return params.iterator();
	}
		
	@Test(dataProvider="loginQuery", groups="Login")
	public void loginQueryTest(String description, String username, String password, String expected) {
		String result = usersBO.loginQuery(username, password);
		expected = (expected.equals("null") ? null : expected);
		assertThat(result, equalTo(expected));
	}
	
	@DataProvider(name="getDetails")
	public Iterator<Object[]> getDetailsData() throws IOException{
		ArrayList<Object[]> params = new ArrayList<Object[]>();
		File f = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\testng\\UsersTestRes\\getDetails.csv");
		Reader in = new FileReader(f);
		Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);
		for(CSVRecord record : records) {
			Object[] paramSet = {
					record.get("description"),
					record.get("userID"),
					record.get("expected")
			};
			System.out.println(Arrays.toString(paramSet));
			params.add(paramSet);
		}
		return params.iterator();
	}
	
	@Test(dataProvider="getDetails", groups="UserInfo")
	public void getDetailsTest(String description,String userID,String expected) {
		try {
			String result = ((User) usersBO.getDetails(userID)).getFirstname();
			expected = (expected.equals("null") ? null : expected);
			assertThat(result, equalTo(expected));
		}
		catch (Exception e) {
			if(!expected.equals("null")) {
				e.printStackTrace();
				Assert.fail();
			}
		}
	}
	
	@DataProvider(name="updateUser")
	public Iterator<Object[]> updateUserData() throws IOException{
		ArrayList<Object[]> params = new ArrayList<Object[]>();
		File f = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\testng\\UsersTestRes\\updateUser.csv");
		Reader in = new FileReader(f);
		Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);
		for(CSVRecord record : records) {
			Object[] paramSet = {
					record.get("description"),
					record.get("password"),
					record.get("firstname"),
					record.get("lastname"),
					record.get("address"),
					record.get("phone"),
					record.get("email"),
					record.get("userID"),
					Integer.parseInt(record.get("expected"))
			};
			System.out.println(Arrays.toString(paramSet));
			params.add(paramSet);
		}
		return params.iterator();
	}
	
	@Test(dataProvider="updateUser", groups="UserInfo")
	public void updateUserTest(String description, String password, String firstname, String lastname, String address, String phone, String email, String userID, int expected) {
		try {
			updatedUser = false;
			user = usersBO.getDetails(userID);
			user.setId(userID);
			
			User newUser = new User();
				newUser.setFirstname(firstname);
				newUser.setLastname(lastname);
				newUser.setPassword(password);
				newUser.setAddress(address);
				newUser.setPhone(phone);
				newUser.setEmail(email);
				newUser.setId(userID);
				
			int result = usersBO.updateUser(newUser);
			updatedUser = (result != 0);
			
			assertThat(result,equalTo(expected));
		}
		catch (Exception e) {
			if(expected != 0) {
				e.printStackTrace();
				Assert.fail();
			}
		}
			
	}
	
	@AfterMethod
	public void after() {
		if(createdUser) {
			usersBO.deleteUser(thisUsername);
		}
		if(updatedUser) {
			usersBO.updateUser(user);
		}
	}
}

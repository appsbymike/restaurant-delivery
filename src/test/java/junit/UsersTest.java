/*
 * NOTE: This class does not contain junit for ANY Admin-Specific methods.
 * Tests will be categorized based on function.
 * A type enumeration will be used to distinguish the method
 * 
 * Note: CSV should be in the following format:
 * method,description,username,password,firstname,lastname,gender,address,phone,email,userID
 * 
 * Any non-existing fields should be left blank, keeping the commas
 */

package junit;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.hamcrest.core.IsEqual;
import org.junit.After;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import del.res.dao.UsersDAO;
import del.res.models.User;

@RunWith(Parameterized.class)
public class UsersTest {
	enum Method{registerQuery, createUser, loginQuery, getDetails, updateUser}
	
	@Parameters(name="CASE{0} | Method: {1} | Expected: {11} | Params: {2},{3},{4},{5},{6},{7},{8},{9},{10}")
	public static Iterable<String[]> data() throws IOException{
		ArrayList<String[]> params = new ArrayList<String[]>();
		File f = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\junit.test.data\\UsersTest.csv");
		Reader in = new FileReader(f);
		Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);
		for(CSVRecord record : records) {
			String[] paramSet = {
					record.get("method"),
					record.get("description"),
					record.get("username"),
					record.get("password"),
					record.get("firstname"),
					record.get("lastname"),
					record.get("gender"),
					record.get("address"),
					record.get("phone"),
					record.get("email"),
					record.get("userID"),
					record.get("expected")
			};
			System.out.println(Arrays.toString(paramSet));
			params.add(paramSet);
		}
		
		return params;
	}
	
	//Create the placeholders for our data
	String username,password,firstname,lastname,gender,address,phone,email,userID;
	String expected;
	Object result;
	Method method;
	//Initialize and populate those placeholders
	public UsersTest(String method, String description, String username, String password, String firstname, String lastname, String gender, String address, String phone, String email, String userID, String expected) {
		//Determine the method based on a series of if statements
		this.method = (method.equals("registerQuery") ? Method.registerQuery : 
					   method.equals("createUser") ? Method.createUser : 
					   method.equals("loginQuery") ? Method.loginQuery : 
					   method.equals("getDetails") ? Method.getDetails : 
					   method.equals("updateUser") ? Method.updateUser : null);
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.gender = gender;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.userID = userID;
		this.expected = expected;
	}
	
	UsersDAO usersDAO;
	User original;
	boolean updatedUser;
	
	@Before
	public void before() {
		usersDAO = new UsersDAO();
	}
	
	//registerQueryTest
	//Expected = true or false
	@Category(RegistrationModule.class)
	@Test
	public void registerQueryTest() {
		Assume.assumeTrue(method == Method.registerQuery);
		result = usersDAO.registerQuery(username);
		Assert.assertThat((Boolean) result, IsEqual.equalTo(Boolean.parseBoolean(expected)));
	}
	
	//createUserTest
	//Expected = 1 or 0
	@Category(RegistrationModule.class)
	@Test
	public void createUserTest() {
		Assume.assumeTrue(method == Method.createUser);
		result = usersDAO.createUser(username, password, firstname, lastname, gender, address, phone, email);
		Assert.assertThat((Integer) result, IsEqual.equalTo(Integer.parseInt(expected)));
	}
	
	//loginQueryTest
	@Category(LoginModule.class)
	@Test
	public void loginQueryTest() {
		Assume.assumeTrue(method == Method.loginQuery);
		result = usersDAO.loginQuery(username, password);
		//Set expected to null if it literally says "null"
		expected = (expected.equals("null") ? null : expected);
		Assert.assertThat((String) result, IsEqual.equalTo(expected));
	}
	
	//getDetailsTest
	@Category(UserInfoModule.class)
	@Test
	public void getDetailsTest() {
		Assume.assumeTrue(method == Method.getDetails);
		try {
			result = ((User) usersDAO.getDetails(userID)).getFirstname();
			expected = (expected.equals("null") ? null : expected);
			Assert.assertThat((String) result, IsEqual.equalTo(expected));
		}
		catch (Exception e) {
			if(!expected.equals("null")) {
				e.printStackTrace();
				Assert.fail();
			}
		}
		
	}
	
	//updateUserTest
	@Category(UserInfoModule.class)
	@Test
	public void updateUserTest() {
		Assume.assumeTrue(method == Method.updateUser);
		try {
			updatedUser = false;
			
			original = usersDAO.getDetails(userID);
			original.setId(userID);
			User newUser = new User();
				newUser.setFirstname(firstname);
				newUser.setLastname(lastname);
				newUser.setPassword(password);
				newUser.setAddress(address);
				newUser.setPhone(phone);
				newUser.setEmail(email);
				newUser.setId(userID);
				
			result = usersDAO.updateUser(newUser);
			updatedUser = ((Integer) result != 0);
			
			Assert.assertThat((Integer) result, IsEqual.equalTo(Integer.parseInt(expected)));	
		}
		catch (Exception e) {
			if(Integer.parseInt(expected) != 0) {
				e.printStackTrace();
				Assert.fail();
			}
		}
			
	}
	
	@Test
	public void invalid() {
		Assume.assumeTrue(method == null);
		Assert.fail("Method specified is invalid");
	}
	
	@After
	public void after() {
		if(updatedUser) {
			usersDAO.updateUser(original);
		}
	}
}

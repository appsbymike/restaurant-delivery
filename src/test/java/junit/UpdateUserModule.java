package junit;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.After;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import del.res.bo.UsersBO;
import del.res.models.User;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

@RunWith(Parameterized.class)
public class UpdateUserModule {
	enum Type{GETUSER, UPDATEUSER};
	
	@Parameters(name="CASE{index} | Params: {2},{3},{4},{5},{6},{7}")
	public static Iterable<String[]> data() throws FileNotFoundException {
		ArrayList<String[]> params = new ArrayList<String[]>();
		File f = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\junit.test.data\\UpdateUserModule");
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(f);
		while(sc.hasNextLine()) {
			params.add(sc.nextLine().split(","));
		}
		return params;
	}


	private String userID,password,firstname,lastname,address,phone,email;
	private boolean expected;
	private Type type;

	public UpdateUserModule(String type, String identifier, String userID, String password, String firstname, String lastname, String address, String phone, String email, String expected) {
		this.type = (type.equals("GETUSER")) ? Type.GETUSER : Type.UPDATEUSER;
		this.userID = userID;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.expected = Boolean.parseBoolean(expected);
	}
	
	UsersBO usersBO;
	User info;
	User oldInfo;
	boolean updated;
	
	@Before
	public void before() {
		usersBO = new UsersBO();
		oldInfo = null;
		info = null;
		updated = false;
	}
	
	@Test
	public void GETUSER() {
		Assume.assumeTrue(type == Type.GETUSER);
		try {
			User result = usersBO.getDetails(userID);
			assertThat(result.getAddress(),is(notNullValue()));
		}
		catch (Exception e) {
			if(expected) {
				e.printStackTrace();
				fail();
			}
		}
		
	}
	
	@Test
	public void UPDATEUSER() {
		Assume.assumeTrue(type == Type.UPDATEUSER);
		oldInfo = usersBO.getDetails(userID);
		oldInfo.setId(userID);
		info = new User(firstname,lastname,password,address,phone,email);
		info.setId(userID);
		boolean result = (usersBO.updateUser(info) != 0);
		updated = result;
		assertThat(result,equalTo(expected));
	}
	
	@After
	public void after() {
		if(updated) {
			usersBO.updateUser(oldInfo);
		}
	}
}

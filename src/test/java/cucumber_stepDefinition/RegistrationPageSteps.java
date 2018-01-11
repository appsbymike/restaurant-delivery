package cucumber_stepDefinition;

import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.core.IsEqual;
import org.openqa.selenium.By;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import del.res.bo.UsersBO;
import util.MyWebDriver;
import util.Strings;

public class RegistrationPageSteps {
	
	boolean createdUser;
	String newUsername;
	
	@Before
	public void before() {
		createdUser = false;
		newUsername = "";
	}
	
	@When("^User navigates to Registration Page$")
	public void user_navigates_to_Registration_Page() {
		MyWebDriver.driver.findElement(By.id("hdRegister")).click();
	}
	
	@When("^User forgets to enter credential and fills in (.*),(.*),(.*),(.*),(.*),(.*),(.*) and (.*)$")
	public void user_forgets_to_enter_credential_and_fills_in(String username, String password, String repassword, 
			String firstname, String lastname, String address, String phone, String email) {
		MyWebDriver.driver.findElement(By.id("username")).sendKeys(username);
		MyWebDriver.driver.findElement(By.id("password")).sendKeys(password);
		MyWebDriver.driver.findElement(By.id("repassword")).sendKeys(repassword);
		MyWebDriver.driver.findElement(By.id("firstname")).sendKeys(firstname);
		MyWebDriver.driver.findElement(By.id("lastname")).sendKeys(lastname);
		MyWebDriver.driver.findElement(By.id("address")).sendKeys(address);
		MyWebDriver.driver.findElement(By.id("phone")).sendKeys(phone);
		MyWebDriver.driver.findElement(By.id("email")).sendKeys(email);
		MyWebDriver.driver.findElement(By.id("register")).click();
	}
	
	@When("^User successfully registers$")
	public void user_successfully_registers() {
		newUsername = "testuser1234";
		MyWebDriver.driver.findElement(By.id("username")).sendKeys(newUsername);
		MyWebDriver.driver.findElement(By.id("password")).sendKeys("test");
		MyWebDriver.driver.findElement(By.id("repassword")).sendKeys("test");
		MyWebDriver.driver.findElement(By.id("firstname")).sendKeys("test");
		MyWebDriver.driver.findElement(By.id("lastname")).sendKeys("test");
		MyWebDriver.driver.findElement(By.id("address")).sendKeys("test");
		MyWebDriver.driver.findElement(By.id("phone")).sendKeys("1234567890");
		MyWebDriver.driver.findElement(By.id("email")).sendKeys("test@test.com");
		MyWebDriver.driver.findElement(By.id("register")).click();
		createdUser = true;
	}
	
	@Then("^User sees Missing Credentials Message for (.*)$")
	public void user_sees_Missing_Credentials_Message(String field) {
		String validator = "Please fill out this field.";
		String result = MyWebDriver.driver.findElement(By.id(field)).getAttribute("validationMessage");
		assertThat(result, IsEqual.equalTo(validator));
	}
	
	@Then("^User sees Registration Page Header$")
	public void user_Registration_Page_Header() {
		assertThat(MyWebDriver.driver.getTitle(), IsEqual.equalTo(Strings.RegistrationHeader));
	}
	
	@Then("^User is redirected to Login Page$")
	public void user_is_redirected_to_Login_Page() {
		assertThat(MyWebDriver.driver.getCurrentUrl(), IsEqual.equalTo(Strings.LoginPage));
	}
	
	@After
	public void after(Scenario scenario) throws InterruptedException {
		if(createdUser) {
			UsersBO usersBO = new UsersBO();
			usersBO.deleteUser(newUsername);
		}
	}
}

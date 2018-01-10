package cucumber_stepDefinition.RegistrationPage;

import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.core.IsEqual;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import del.res.bo.UsersBO;
import util.CreateWebDriver;
import util.Strings;

public class RegistrationPageSteps {
	
	WebDriver driver;
	boolean createdUser;
	String newUsername;
	
	@Before
	public void before() {
		createdUser = false;
		newUsername = "";
	}
	
	@Given("^User navigated to Registration Page$")
	public void user_navigates_to_Registration_Page() {
		CreateWebDriver createWebDriver = new CreateWebDriver();
		String URL = "http://localhost/RestaurantDelivery-MichaelGabriel-Maven/";
		driver = createWebDriver.createChromeDriver(URL);
		driver.findElement(By.id("hdRegister")).click();
	}
	
	@When("^User forgets to enter credential and fills in (.*),(.*),(.*),(.*),(.*),(.*),(.*) and (.*)$")
	public void user_forgets_to_enter_credential_and_fills_in(String username, String password, String repassword, 
			String firstname, String lastname, String address, String phone, String email) {
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("repassword")).sendKeys(repassword);
		driver.findElement(By.id("firstname")).sendKeys(firstname);
		driver.findElement(By.id("lastname")).sendKeys(lastname);
		driver.findElement(By.id("address")).sendKeys(address);
		driver.findElement(By.id("phone")).sendKeys(phone);
		driver.findElement(By.id("email")).sendKeys(email);
		driver.findElement(By.id("register")).click();
	}
	
	@When("^User successfully registers$")
	public void user_successfully_registers() {
		newUsername = "testuser1234";
		driver.findElement(By.id("username")).sendKeys(newUsername);
		driver.findElement(By.id("password")).sendKeys("test");
		driver.findElement(By.id("repassword")).sendKeys("test");
		driver.findElement(By.id("firstname")).sendKeys("test");
		driver.findElement(By.id("lastname")).sendKeys("test");
		driver.findElement(By.id("address")).sendKeys("test");
		driver.findElement(By.id("phone")).sendKeys("1234567890");
		driver.findElement(By.id("email")).sendKeys("test@test.com");
		driver.findElement(By.id("register")).click();
		createdUser = true;
	}
	
	@Then("^User sees Missing Credentials Message for (.*)$")
	public void user_sees_Missing_Credentials_Message(String field) {
		String validator = "Please fill out this field.";
		String result = driver.findElement(By.id(field)).getAttribute("validationMessage");
		assertThat(result, IsEqual.equalTo(validator));
	}
	
	@Then("^User sees Registration Page Header$")
	public void user_Registration_Page_Header() {
		assertThat(driver.getTitle(), IsEqual.equalTo(Strings.RegistrationHeader));
	}
	
	@Then("^User is redirected to Login Page$")
	public void user_is_redirected_to_Login_Page() {
		assertThat(driver.getCurrentUrl(), IsEqual.equalTo(Strings.LoginPage));
	}
	
	@After
	public void after(Scenario scenario) throws InterruptedException {
		//For some reason, Thread.sleep must be called before driver.quit()
		//In order to actually end the chromedriver process.
		//TODO: Find out how little time we can get away with
		if(scenario.isFailed()) {
			TakesScreenshot camera = (TakesScreenshot) driver;
            byte[] screenshot = camera.getScreenshotAs(OutputType.BYTES);
			scenario.embed(screenshot, "image/png");
		}
		Thread.sleep(200);
		driver.quit();
		if(createdUser) {
			UsersBO usersBO = new UsersBO();
			usersBO.deleteUser(newUsername);
		}
	}
}

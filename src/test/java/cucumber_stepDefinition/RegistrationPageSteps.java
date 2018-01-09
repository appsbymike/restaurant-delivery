package cucumber_stepDefinition;

import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.core.IsEqual;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import util.CreateWebDriver;

public class RegistrationPageSteps {
	
	WebDriver driver;
	
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
	
	@Then("^User sees Missing Credentials Message for (.*)$")
	public void user_sees_Missing_Credentials_Message(String field) {
		String validator = "Please fill out this field.";
		String result = driver.findElement(By.id(field)).getAttribute("validationMessage");
		assertThat(result, IsEqual.equalTo(validator));
	}
	
	@After
	public void after(Scenario scenario) throws InterruptedException {
		//For some reason, Thread.sleep must be called before driver.quit()
		//In order to actually end the chromedriver process.
		//TODO: Find out how little time we can get away with
		Thread.sleep(200);
		driver.quit();
	}
}

package cucumber_stepDefinition.LoginPage;

import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.core.IsEqual;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import util.CreateWebDriver;
import util.Strings;

public class LoginPageSteps {
	public static WebDriver driver;
	
	@Given("^User is on Home Page$")
	public void user_is_on_Home_Page() {
		CreateWebDriver createWebDriver = new CreateWebDriver();
		String URL = "http://localhost/RestaurantDelivery-MichaelGabriel-Maven/";
		driver = createWebDriver.createChromeDriver(URL);
	}
	
	@When("^User Navigates to Login Page$")
	public void user_navigates_to_Login_Page() {
		driver.findElement(By.id("hdLogin")).click();
	}
	
	@When("^User enters valid Username and Password$")
	public void user_enters_valid_Username_and_Password() {
		driver.findElement(By.id("username")).sendKeys("mike");;
		driver.findElement(By.id("password")).sendKeys("mike");;
		driver.findElement(By.id("login")).click();
	}
	
	@When("^User enters Valid Admin Credentials$")
	public void user_enters_Valid_Admin_Credentials() {
		driver.findElement(By.id("username")).sendKeys("admin");;
		driver.findElement(By.id("password")).sendKeys("admin");;
		driver.findElement(By.id("login")).click();
	}
	
	@Then("^User sees Login Page Header$")
	public void user_sees_Login_Page_Header() {
		assertThat(driver.getTitle(), IsEqual.equalTo(Strings.loginHeader));
	}
	
	@Then("^User is sent to Menu Page$")
	public void user_is_sent_to_Menu_Page() {
		assertThat(driver.getCurrentUrl(), IsEqual.equalTo(Strings.menuPage));
	}
	
	@Then("^User sees Invalid Credentials Message for (.*)$")
	public void user_sees_Invalid_Credentials_Message(String field) {
		String validator = "Please match the requested format.";
		String result = driver.findElement(By.id(field)).getAttribute("validationMessage");
		assertThat(result, IsEqual.equalTo(validator));
	}
	
	@Then("^User is sent to Admin Home Page$")
	public void user_is_sent_to_Admin_Home_Page() {
		assertThat(driver.getCurrentUrl(), IsEqual.equalTo(Strings.htmlAdminRoot));
	}
	
	@Then("^User sees text input field for Username$")
	public void user_sees_text_input_field_for_Username() {
		String type = driver.findElement(By.id("username")).getAttribute("type");
		assertThat(type, IsEqual.equalTo("text"));
	}
	
	@Then("^User sees masked input field for Password$")
	public void user_sees_masked_input_field_for_Password() {
		String type = driver.findElement(By.id("password")).getAttribute("type");
		assertThat(type, IsEqual.equalTo("password"));
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
	}
	
}

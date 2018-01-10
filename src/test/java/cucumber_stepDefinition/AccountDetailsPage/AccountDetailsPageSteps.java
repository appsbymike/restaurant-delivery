package cucumber_stepDefinition.AccountDetailsPage;

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import util.CreateWebDriver;
import util.Strings;

public class AccountDetailsPageSteps {
	WebDriver driver;
	@Given("^User is viewing their Account Details$")
	public void user_is_viewing_their_Account_Details() throws Throwable {
		CreateWebDriver createWebDriver = new CreateWebDriver();
		driver = createWebDriver.createChromeDriver(Strings.htmlRoot);
		driver.findElement(By.id("hdLogin")).click();
		driver.findElement(By.id("username")).sendKeys("mike");
		driver.findElement(By.id("password")).sendKeys("mike");
		driver.findElement(By.id("login")).click();
		driver.findElement(By.id("hdAccount")).click();
	}

	@Then("^User sees Logout and Past Orders links$")
	public void user_sees_Logout_and_Past_Orders_links() throws Throwable {
	    boolean result = driver.findElement(By.id("hdLogout")).isDisplayed() && driver.findElement(By.id("hdOrders")).isDisplayed();
	    MatcherAssert.assertThat(result, IsEqual.equalTo(true));
	}

	@When("^User enters data (.*),(.*),(.*),(.*),(.*),(.*),(.*)$")
	public void user_enters_data(String firstname, String lastname, String password, String repassword, String address, String phone, String email) throws Throwable {
	    WebElement txtFirstname = driver.findElement(By.name("firstname"));
	    txtFirstname.clear();
		txtFirstname.sendKeys(firstname);
	    WebElement txtLastname = driver.findElement(By.name("lastname"));
	    txtLastname.clear();
	    txtLastname.sendKeys(lastname);
	    WebElement txtPassword = driver.findElement(By.name("password"));
	    txtPassword.clear();
	    txtPassword.sendKeys(password);
	    WebElement txtRepassword = driver.findElement(By.name("repassword"));
	    txtRepassword.clear();
	    txtRepassword.sendKeys(repassword);
	    WebElement txtAddress = driver.findElement(By.name("address"));
	    txtAddress.clear();
	    txtAddress.sendKeys(address);
	    WebElement txtPhone = driver.findElement(By.name("phone"));
	    txtPhone.clear();
	    txtPhone.sendKeys(phone);
	    WebElement txtEmail = driver.findElement(By.name("email"));
	    txtEmail.clear();
	    txtEmail.sendKeys(email);
	    
	    driver.findElement(By.name("update")).click();
	}

	@Then("^User sees empty field message for (.*)$")
	public void user_sees_empty_field_message_for(String field) throws Throwable {
		String expected = "Please fill out this field.";
	    String result = driver.findElement(By.name(field)).getAttribute("validationMessage");
	    MatcherAssert.assertThat(result, IsEqual.equalTo(expected));
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
		driver.findElement(By.id("hdLogout")).click();
		Thread.sleep(200);
		driver.quit();
	}
}

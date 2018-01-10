package cucumber_stepDefinition.PaymentPage;

import org.hamcrest.MatcherAssert;
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

public class PaymentPageSteps {
	WebDriver driver;
	@Given("^User confirmed order$")
	public void user_confirmed_order() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		// Write code here that turns the phrase above into concrete actions
		CreateWebDriver createWebDriver = new CreateWebDriver();
		driver = createWebDriver.createChromeDriver(Strings.htmlRoot);
		driver.findElement(By.id("hdLogin")).click();
		driver.findElement(By.id("username")).sendKeys("mike");
		driver.findElement(By.id("password")).sendKeys("mike");
		driver.findElement(By.id("login")).click();
		driver.findElement(By.name("cart")).click();
		driver.switchTo().alert().accept();
		driver.findElement(By.name("process")).click();
		Thread.sleep(200);
		driver.findElement(By.name("bt")).click();
		driver.findElement(By.name("process")).click();
	}

	@When("^User enters (.*), (.*), (.*)$")
	public void user_enters(String card, String sec, String zip) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.findElement(By.name("ccnumber")).sendKeys(card);
		driver.findElement(By.name("seccode")).sendKeys(sec);
		driver.findElement(By.name("zipcode")).sendKeys(zip);
		driver.findElement(By.name("process")).click();
	}
	
	@Then("^User sees missing field message for (.*)$")
	public void user_sees_missing_field_message_for_ccnumber(String field) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
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

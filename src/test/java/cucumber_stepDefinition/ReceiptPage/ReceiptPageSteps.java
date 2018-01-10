package cucumber_stepDefinition.ReceiptPage;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import util.CreateWebDriver;
import util.Strings;

public class ReceiptPageSteps {
	WebDriver driver;
	@Given("^User navigated to their receipt$")
	public void user_navigated_to_their_receipt() throws Throwable {
		CreateWebDriver createWebDriver = new CreateWebDriver();
		driver = createWebDriver.createChromeDriver(Strings.htmlRoot);
		driver.findElement(By.id("hdLogin")).click();
		driver.findElement(By.id("username")).sendKeys("mike");
		driver.findElement(By.id("password")).sendKeys("mike");
		driver.findElement(By.id("login")).click();
		Thread.sleep(200);
		driver.get(Strings.ReceiptPage);
	}

	@Then("^User sees receipt information for each item$")
	public void user_sees_receipt_information_for_each_item() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		for(WebElement item : driver.findElements(By.id("item"))) {
			boolean foundInfo = item.findElement(By.id("item-img")).isDisplayed() && item.findElement(By.id("item-name")).isDisplayed() && item.findElement(By.id("item-price")).isDisplayed();
			if(!foundInfo) {
				Assert.fail();
			}
		}
	}

	@Then("^User sees Logout link$")
	public void user_sees_Logout_link() throws Throwable {
	    Assert.assertTrue(driver.findElement(By.id("hdLogout")).isDisplayed());
	}

	@Then("^User sees Menu link$")
	public void user_sees_Menu_link() throws Throwable {
	    Assert.assertTrue(driver.findElement(By.id("hdMenu")).isDisplayed());
	}

	@Then("^User sees Past Orders Link$")
	public void user_sees_Past_Orders_Link() throws Throwable {
	    Assert.assertTrue(driver.findElement(By.id("hdOrders")).isDisplayed());
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

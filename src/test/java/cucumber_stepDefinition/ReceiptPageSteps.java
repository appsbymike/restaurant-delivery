package cucumber_stepDefinition;

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
import util.MyWebDriver;
import util.Strings;

public class ReceiptPageSteps {
	@Given("^User navigates to their receipt$")
	public void user_navigated_to_their_receipt() throws Throwable {
		MyWebDriver.driver.get(Strings.ReceiptPage);
	}

	@Then("^User sees receipt information for each item$")
	public void user_sees_receipt_information_for_each_item() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		for(WebElement item : MyWebDriver.driver.findElements(By.id("item"))) {
			boolean foundInfo = item.findElement(By.id("item-img")).isDisplayed() && item.findElement(By.id("item-name")).isDisplayed() && item.findElement(By.id("item-price")).isDisplayed();
			if(!foundInfo) {
				Assert.fail();
			}
		}
	}

	@Then("^User sees Menu Button$")
	public void user_sees_Menu_link() throws Throwable {
	    Assert.assertTrue(MyWebDriver.driver.findElement(By.id("hdMenu")).isDisplayed());
	}

	@Then("^User sees Past Orders Button$")
	public void user_sees_Past_Orders_Link() throws Throwable {
	    Assert.assertTrue(MyWebDriver.driver.findElement(By.id("hdOrders")).isDisplayed());
	}
}

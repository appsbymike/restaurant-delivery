package cucumber_stepDefinition.OrderReviewPage;

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;


import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import util.CreateWebDriver;
import util.Strings;

public class OrderReviewPageSteps {
	WebDriver driver;
	String itemID;
	
	@Before
	public void before() {
		itemID = "";
	}
	
	@Given("^User has multiple items in their Cart$")
	public void user_has_multiple_items_in_their_Cart() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		CreateWebDriver createWebDriver = new CreateWebDriver();
		driver = createWebDriver.createChromeDriver(Strings.htmlRoot);
		driver.findElement(By.id("hdLogin")).click();
		driver.findElement(By.id("username")).sendKeys("mike");
		driver.findElement(By.id("password")).sendKeys("mike");
		driver.findElement(By.id("login")).click();
		int size = driver.findElements(By.name("cart")).size();
		for(int i=0; i<size; i++) {
			driver.findElements(By.name("cart")).get(i).click();
			driver.switchTo().alert().accept();
		}
		driver.findElement(By.name("process")).click();
	}

	@When("^User selects a location$")
	public void user_selects_a_location() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    driver.findElement(By.name("bt")).click();
	}

	@Then("^User sees the image, name and price of each item in their cart$")
	public void user_sees_the_image_name_and_price_of_each_item_in_their_cart() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    for(WebElement item : driver.findElements(By.id("cart-item"))) {
	    	
	    	if(!item.findElement(By.id("item-img")).isDisplayed() 
	    			|| !item.findElement(By.id("item-name")).isDisplayed()
	    			|| !item.findElement(By.id("item-name")).isDisplayed()) {
	    		Assert.fail();
	    	}
	    	
	    }
	}

	@Then("^User sees delete button for each item in their cart$")
	public void user_sees_delete_button_for_each_item_in_their_cart() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		for(WebElement item : driver.findElements(By.id("cart-item"))) {
	    	if(!item.findElement(By.id("item-remove")).isDisplayed()) {
	    		Assert.fail();}
	    }
	}

	@When("^User confirms their purchase$")
	public void user_confirms_their_purchase() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    driver.findElement(By.name("process")).click();
	}

	@Then("^User is sent to Payment Page$")
	public void user_is_sent_to_Payment_Page() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		MatcherAssert.assertThat(driver.getCurrentUrl(), IsEqual.equalTo(Strings.PaymentPage));
	}

	@When("^User cancels their purchase$")
	public void user_cancels_their_purchase() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    driver.findElement(By.name("cancel")).click();
	}
	
	@Then("^User is sent back to Menu Page$")
	public void user_is_sent_back_to_Menu_Page() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		MatcherAssert.assertThat(driver.getCurrentUrl(), IsEqual.equalTo(Strings.MenuPage));
	}

	@When("^User clicks on the delete button for an item in their Cart$")
	public void user_clicks_on_the_delete_button_for_an_item_in_their_Cart() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		WebElement remove = driver.findElement(By.id("item-remove"));
		itemID = remove.getAttribute("value");
	    remove.click();
	}

	@Then("^User is sent back to Review Order Page$")
	public void user_is_sent_back_to_Review_Order_Page() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    MatcherAssert.assertThat(driver.getCurrentUrl(), IsEqual.equalTo(Strings.OrderReviewPage));
	}

	@Then("^User no longer sees that item in their Cart$")
	public void user_no_longer_sees_that_item_in_their_Cart() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    for(WebElement item : driver.findElements(By.id("item-remove"))) {
	    	if(item.getAttribute("value").equals(itemID)) {
	    		Assert.fail();
	    	}
	    }
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

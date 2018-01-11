package cucumber_stepDefinition;

import static org.hamcrest.MatcherAssert.assertThat;
import org.hamcrest.core.IsEqual;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import util.MyWebDriver;
import util.Strings;

public class MenuPageSteps {
	
	@Given("^User is logged in$")
	public void user_is_logged_in() {
		MyWebDriver.createChromeDriver(Strings.htmlRoot);
		MyWebDriver.driver.findElement(By.id("hdLogin")).click();
		MyWebDriver.driver.findElement(By.id("username")).sendKeys("mike");
		MyWebDriver.driver.findElement(By.id("password")).sendKeys("mike");
		MyWebDriver.driver.findElement(By.id("login")).click();
	}
	
	@When("^User navigates to Menu Page$")
	public void user_navigates_to_Menu_Page() {
		WebElement el = MyWebDriver.driver.findElement(By.id("hdMenu"));
		MyWebDriver.scrollTo(el);
		el.click();
	}
	
	@When("^User adds Item to Cart$")
	public void user_adds_item_to_Cart() {
		WebElement btnCart = MyWebDriver.driver.findElement(By.name("cart"));
		MyWebDriver.scrollTo(btnCart);
		btnCart.click();
	}
	
	@When("^User accepts message$")
	public void user_accepts_message() {
		MyWebDriver.driver.switchTo().alert().accept();
	}
	
	@When("^User clicks Process Order$")
	public void user_clicks() {
		WebElement btnProcess = MyWebDriver.driver.findElement(By.name("process"));
		MyWebDriver.scrollTo(btnProcess);
		btnProcess.click();
	}
	
	@Then("^User sees Logout Button$")
	public void user_sees_Logout_Button() {
		assertThat(MyWebDriver.driver.findElement(By.id("hdLogout")).isDisplayed(),IsEqual.equalTo(true));
	}
	
	@Then("^User sees message \"(.*)\"$")
	public void user_sees_message(String message) {
		String result = MyWebDriver.driver.switchTo().alert().getText();
		MyWebDriver.driver.switchTo().alert().accept();
		assertThat(result,IsEqual.equalTo(message));
	}
	
	@Then("^User is sent to Locations Page$")
	public void user_is_sent_to_Locations_Page() throws InterruptedException {
		Thread.sleep(200);
		assertThat(MyWebDriver.driver.getCurrentUrl(), IsEqual.equalTo(Strings.LocationsPage));
	}
}
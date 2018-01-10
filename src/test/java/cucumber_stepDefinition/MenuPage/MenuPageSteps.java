package cucumber_stepDefinition.MenuPage;

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

public class MenuPageSteps {
	WebDriver driver;
	
	@Given("^User is logged in$")
	public void user_is_logged_in() {
		CreateWebDriver createWebDriver = new CreateWebDriver();
		String URL = "http://localhost/RestaurantDelivery-MichaelGabriel-Maven/";
		driver = createWebDriver.createChromeDriver(URL);
		driver.findElement(By.id("hdLogin")).click();
		driver.findElement(By.id("username")).sendKeys("mike");
		driver.findElement(By.id("password")).sendKeys("mike");
		driver.findElement(By.id("login")).click();
	}
	
	@When("^User navigates to Menu Page$")
	public void user_navigates_to_Menu_Page() {
		driver.findElement(By.id("hdMenu")).click();
	}
	
	@When("^User adds Item to Cart$")
	public void user_adds_item_to_Cart() {
		driver.findElement(By.name("cart")).click();
	}
	
	@When("^User accepts message$")
	public void user_accepts_message() {
		driver.switchTo().alert().accept();
	}
	
	@When("^User clicks Process Order$")
	public void user_clicks() {
		driver.findElement(By.name("process")).click();
	}
	
	@Then("^User sees Logout Button$")
	public void user_sees_Logout_Button() {
		assertThat(driver.findElement(By.id("hdLogout")).isDisplayed(),IsEqual.equalTo(true));
	}
	
	@Then("^User sees message \"(.*)\"$")
	public void user_sees_message(String message) {
		String result = driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();
		assertThat(result,IsEqual.equalTo(message));
	}
	
	@Then("^User is sent to Locations Page$")
	public void user_is_sent_to_Locations_Page() throws InterruptedException {
		Thread.sleep(200);
		assertThat(driver.getCurrentUrl(), IsEqual.equalTo(Strings.locationsPage));
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

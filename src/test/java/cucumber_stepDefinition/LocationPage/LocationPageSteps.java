package cucumber_stepDefinition.LocationPage;

import static org.testng.Assert.fail;

import java.util.List;

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

public class LocationPageSteps {
	WebDriver driver;
	
	@Given("^User has item in cart$")
	public void user_has_item_in_cart() {
		CreateWebDriver createWebDriver = new CreateWebDriver();
		String URL = "http://localhost/RestaurantDelivery-MichaelGabriel-Maven/";
		driver = createWebDriver.createChromeDriver(URL);
		driver.findElement(By.id("hdLogin")).click();
		driver.findElement(By.id("username")).sendKeys("mike");
		driver.findElement(By.id("password")).sendKeys("mike");
		driver.findElement(By.id("login")).click();
		driver.findElement(By.name("cart")).click();
		driver.switchTo().alert().accept();
	}
	
	@When("^User processes order$")
	public void user_processes_order() {
		driver.findElement(By.name("process")).click();
	}
	
	@Then("^User should see a button for each Location$")
	public void user_should_see_a_button_for_each_Location() {
		List<WebElement> locations = driver.findElements(By.id("location"));
		for(WebElement location : locations) {
			if(!location.findElement(By.name("bt")).isDisplayed()) {
				fail("Button not found");
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


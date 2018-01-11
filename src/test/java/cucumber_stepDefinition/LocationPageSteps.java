package cucumber_stepDefinition;

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
import util.MyWebDriver;
import util.Strings;

public class LocationPageSteps {
	
	@When("^User processes order$")
	public void user_processes_order() {
		MyWebDriver.driver.findElement(By.name("process")).click();
	}
	
	@Then("^User should see a button for each Location$")
	public void user_should_see_a_button_for_each_Location() {
		List<WebElement> locations = MyWebDriver.driver.findElements(By.id("location"));
		for(WebElement location : locations) {
			if(!location.findElement(By.name("bt")).isDisplayed()) {
				fail("Button not found");
			}
		}
	}
}


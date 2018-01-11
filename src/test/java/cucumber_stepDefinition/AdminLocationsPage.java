package cucumber_stepDefinition;

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import util.MyWebDriver;
import util.Strings;

public class AdminLocationsPage {
	@When("^User Navigates to Locations List$")
	public void user_Navigates_to_Locations_List() throws Throwable {
		MyWebDriver.driver.findElement(By.id("navLocation")).click();
	}

	@When("^User Adds a Location$")
	public void user_Adds_a_Location() throws Throwable {
		WebElement el = MyWebDriver.driver.findElement(By.id("newLocation"));
		//name
		el.findElement(By.id("name")).sendKeys("test");
		//desc
		el.findElement(By.id("desc")).sendKeys("test");
		//address
		el.findElement(By.id("address")).sendKeys("test test rd");
		//zipcode
		el.findElement(By.id("zipcode")).sendKeys("12345");
		//addDesc
		el.findElement(By.id("addDesc")).sendKeys("test");
		//staff
		el.findElement(By.id("staff")).sendKeys("12");
		
		//create

		WebElement el1 = MyWebDriver.driver.findElement(By.id("create"));
		MyWebDriver.scrollTo(el1);
		el1.click();
	}

	@Then("^User is taken back to Locations List$")
	public void user_is_taken_back_to_Locations_List() throws Throwable {
		MatcherAssert.assertThat(MyWebDriver.driver.getCurrentUrl(), IsEqual.equalTo(Strings.AdminLocationsPage));
	}

	@When("^User Removes a Location$")
	public void user_Removes_a_Location() throws Throwable {
		WebElement el = MyWebDriver.driver.findElement(By.name("delete"));
		MyWebDriver.scrollTo(el);
		el.click();
	}

	@When("^User Edits a Location$")
	public void user_Edits_a_Location() throws Throwable {
		WebElement el = MyWebDriver.driver.findElement(By.name("update"));
		MyWebDriver.scrollTo(el);
		el.click();
	}

	@Then("^User sees Edit button for each Location$")
	public void user_sees_Edit_button_for_each_Location() throws Throwable {
		for(WebElement el : MyWebDriver.driver.findElements(By.id("existingLocation"))) {
			if(!el.findElement(By.name("update")).isDisplayed()) {
				Assert.fail();
			}
		}
	}

	@Then("^User sees Delete button for each Location$")
	public void user_sees_Delete_button_for_each_Location() throws Throwable {
		for(WebElement el : MyWebDriver.driver.findElements(By.id("existingLocation"))) {
			if(!el.findElement(By.name("delete")).isDisplayed()) {
				Assert.fail();
			}
		}
	}
}

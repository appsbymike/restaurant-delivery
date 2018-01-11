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

public class AdminOrdersPageSteps {
	@When("^User Navigates to Orders List$")
	public void user_Navigates_to_Orders_List() throws Throwable {
		MyWebDriver.driver.findElement(By.id("navOrder")).click();
	}

	@Then("^User sees Delete button for each Order$")
	public void user_sees_Delete_button_for_each_Order() throws Throwable {
		for(WebElement el : MyWebDriver.driver.findElements(By.id("order"))) {
			if(!el.findElement(By.name("id")).isDisplayed()) {
				Assert.fail("Delete Button not Displayed");
			}
		}
	}

	@When("^User Deletes an Order$")
	public void user_Deletes_an_Order() throws Throwable {
		MyWebDriver.driver.findElement(By.name("id")).click();
		
	}

	@Then("^User is taken back to Orders List$")
	public void user_is_taken_back_to_Orders_List() throws Throwable {
		MatcherAssert.assertThat(MyWebDriver.driver.getCurrentUrl(), IsEqual.equalTo(Strings.AdminOrdersPage));
	}
}

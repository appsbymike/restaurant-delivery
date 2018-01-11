package cucumber_stepDefinition;

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.openqa.selenium.By;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import util.MyWebDriver;
import util.Strings;

public class PastOrdersPageSteps {
	@When("^User navigates to Past Orders Page$")
	public void user_is_viewing_Past_Orders() throws Throwable {
	    MyWebDriver.driver.findElement(By.id("hdOrders")).click();
	}

	@Then("^User sees the Logout Button$")
	public void user_sees_the_Logout_Button() throws Throwable {
	    MatcherAssert.assertThat(MyWebDriver.driver.findElement(By.id("hdLogout")).isDisplayed(), IsEqual.equalTo(true));
	}
	
	@Then("^User sees the Menu Button$")
	public void user_sees_the_Menu_Button() throws Throwable {
	    MatcherAssert.assertThat(MyWebDriver.driver.findElement(By.id("hdMenu")).isDisplayed(), IsEqual.equalTo(true));
	}	
}

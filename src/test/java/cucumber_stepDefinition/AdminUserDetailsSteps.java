package cucumber_stepDefinition;

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import util.MyWebDriver;
import util.Strings;

public class AdminUserDetailsSteps {
	@When("^User clicks on a User$")
	public void user_clicks_on_a_User() throws Throwable {
		MyWebDriver.driver.findElement(By.id("user")).click();
	}

	@When("^User updates a User$")
	public void user_updates_a_User() throws Throwable {
		WebElement el = MyWebDriver.driver.findElement(By.name("update"));
		MyWebDriver.scrollTo(el);
		el.click();
	}

	@Then("^User is sent back to User List$")
	public void user_is_sent_back_to_User_List() throws Throwable {
		MatcherAssert.assertThat(MyWebDriver.driver.getCurrentUrl(), IsEqual.equalTo(Strings.AdminUserPage));
	}

	@Then("^User sees past reviews$")
	public void user_sees_past_reviews() throws Throwable {
		MatcherAssert.assertThat(MyWebDriver.driver.findElement(By.id("reviews")).isDisplayed(), IsEqual.equalTo(true));
	}
}

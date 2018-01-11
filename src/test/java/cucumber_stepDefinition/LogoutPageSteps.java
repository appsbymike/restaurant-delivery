package cucumber_stepDefinition;

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.openqa.selenium.By;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import util.MyWebDriver;

public class LogoutPageSteps {
	@When("^User logs out$")
	public void user_logs_out() throws Throwable {
		MyWebDriver.driver.findElement(By.id("hdLogout")).click();
	}

	@Then("^Logout message should be displayed$")
	public void logout_message_should_be_displayed() throws Throwable {
		String result1 = MyWebDriver.driver.findElement(By.id("mes1")).getText();
		String expected1 = "Thanks for Visiting Us !!! You have been logged out";
		String result2 = MyWebDriver.driver.findElement(By.id("mes2")).getText();
		String expected2 = "Want to login again? Click below";
		boolean result = (result1.equals(expected1) && result2.equals(expected2));
		MatcherAssert.assertThat(result, IsEqual.equalTo(true));
	}

	@Then("^Logout link should be displayed$")
	public void logout_link_should_be_displayed() throws Throwable {
		boolean result = MyWebDriver.driver.findElement(By.linkText("Login")).isDisplayed();
		MatcherAssert.assertThat(result, IsEqual.equalTo(true));
	}
}

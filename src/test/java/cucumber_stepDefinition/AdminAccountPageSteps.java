package cucumber_stepDefinition;

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.openqa.selenium.By;

import cucumber.api.java.en.Then;
import util.MyWebDriver;

public class AdminAccountPageSteps {
	@Then("^User sees Navigate Application Button$")
	public void user_sees_Navigate_Application_Button() {
		boolean displayed = MyWebDriver.driver.findElement(By.id("hdNavigate")).isDisplayed();
		MatcherAssert.assertThat(displayed, IsEqual.equalTo(true));
	}
}

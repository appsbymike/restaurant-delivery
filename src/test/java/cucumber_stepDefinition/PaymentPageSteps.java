package cucumber_stepDefinition;

import org.hamcrest.MatcherAssert;
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
import util.MyWebDriver;
import util.Strings;

public class PaymentPageSteps {
	@When("^User confirms order$")
	public void user_confirmed_order() throws Throwable {
		MyWebDriver.driver.findElement(By.name("process")).click();
	}

	@When("^User enters (.*), (.*), (.*)$")
	public void user_enters(String card, String sec, String zip) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		MyWebDriver.driver.findElement(By.name("ccnumber")).sendKeys(card);
		MyWebDriver.driver.findElement(By.name("seccode")).sendKeys(sec);
		MyWebDriver.driver.findElement(By.name("zipcode")).sendKeys(zip);
		MyWebDriver.driver.findElement(By.name("process")).click();
	}
	
	@Then("^User sees missing field message for (.*)$")
	public void user_sees_missing_field_message_for_ccnumber(String field) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		String expected = "Please fill out this field.";
		String result = MyWebDriver.driver.findElement(By.name(field)).getAttribute("validationMessage");
		MatcherAssert.assertThat(result, IsEqual.equalTo(expected));
	}
}

package cucumber_stepDefinition;

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import del.res.bo.ReviewsBO;
import util.MyWebDriver;
import util.Strings;

public class SingleItemPageSteps {
	String reviewMessage = "This is a Test Review";
	boolean createdReview;
	
	@Before
	public void before() {
		createdReview = false;
	}
	
	@Given("^User navigates to Single Item Page$")
	public void user_navigated_to_review_an_Item() throws Throwable {
		MyWebDriver.driver.findElement(By.name("reviews")).click();
	}

	@When("^User writes a review$")
	public void user_writes_a_review() throws Throwable {
		MyWebDriver.driver.findElement(By.name("reviewText")).sendKeys(reviewMessage);
	}

	@When("^User submits a review$")
	public void user_submits_a_review() throws Throwable {
		WebElement btnReview = MyWebDriver.driver.findElement(By.name("review"));
		MyWebDriver.scrollTo(btnReview);
		btnReview.click();
		MyWebDriver.driver.switchTo().alert().accept();
	    createdReview = true;
	}

	@Then("^User should see the review they submitted$")
	public void user_should_see_the_review_they_submitted() throws Throwable {
	    String result = MyWebDriver.driver.findElement(By.id("reviewText")).getText();
	    MatcherAssert.assertThat(result, IsEqual.equalTo(reviewMessage));
	}
	
	@After
	public void after(Scenario scenario) throws InterruptedException {
		if(createdReview) {
			ReviewsBO reviewsBO = new ReviewsBO();
			reviewsBO.deleteUserReviews("3");
		}
	}
}

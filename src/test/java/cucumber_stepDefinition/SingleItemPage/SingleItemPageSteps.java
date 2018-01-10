package cucumber_stepDefinition.SingleItemPage;

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
import util.CreateWebDriver;
import util.Strings;

public class SingleItemPageSteps {
	WebDriver driver;
	String reviewMessage = "This is a Test Review";
	boolean createdReview;
	
	@Before
	public void before() {
		createdReview = false;
	}
	
	@Given("^User navigated to review an Item$")
	public void user_navigated_to_review_an_Item() throws Throwable {
		CreateWebDriver createWebDriver = new CreateWebDriver();
		driver = createWebDriver.createChromeDriver(Strings.htmlRoot);
		driver.findElement(By.id("hdLogin")).click();
		driver.findElement(By.id("username")).sendKeys("mike");
		driver.findElement(By.id("password")).sendKeys("mike");
		driver.findElement(By.id("login")).click();
		driver.findElement(By.name("reviews")).click();
	}

	@Then("^Logout button should be displayed$")
	public void logout_button_should_be_displayed() throws Throwable {
	    Assert.assertTrue(driver.findElement(By.id("hdLogout")).isDisplayed());
	}

	@When("^User writes a review$")
	public void user_writes_a_review() throws Throwable {
	    driver.findElement(By.name("reviewText")).sendKeys(reviewMessage);
	}

	@When("^User submits a review$")
	public void user_submits_a_review() throws Throwable {
		WebElement btnReview = driver.findElement(By.name("review"));
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].scrollIntoView(true);",btnReview);
		btnReview.click();
	    driver.switchTo().alert().accept();
	    createdReview = true;
	}

	@Then("^User should see the review they submitted$")
	public void user_should_see_the_review_they_submitted() throws Throwable {
	    String result = driver.findElement(By.id("reviewText")).getText();
	    MatcherAssert.assertThat(result, IsEqual.equalTo(reviewMessage));
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
		if(createdReview) {
			ReviewsBO reviewsBO = new ReviewsBO();
			reviewsBO.deleteUserReviews("3");
		}
	}
}

package cucumber_stepDefinition;

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
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

public class AccountDetailsPageSteps {
	@When("^User navigates to the Account Details Page$")
	public void user_is_viewing_their_Account_Details() throws Throwable {
		MyWebDriver.driver.findElement(By.id("hdAccount")).click();
	}

	@When("^User enters data (.*),(.*),(.*),(.*),(.*),(.*),(.*)$")
	public void user_enters_data(String firstname, String lastname, String password, String repassword, String address, String phone, String email) throws Throwable {
	    WebElement txtFirstname = MyWebDriver.driver.findElement(By.name("firstname"));
	    txtFirstname.clear();
		txtFirstname.sendKeys(firstname);
	    WebElement txtLastname = MyWebDriver.driver.findElement(By.name("lastname"));
	    txtLastname.clear();
	    txtLastname.sendKeys(lastname);
	    WebElement txtPassword = MyWebDriver.driver.findElement(By.name("password"));
	    txtPassword.clear();
	    txtPassword.sendKeys(password);
	    WebElement txtRepassword = MyWebDriver.driver.findElement(By.name("repassword"));
	    txtRepassword.clear();
	    txtRepassword.sendKeys(repassword);
	    WebElement txtAddress = MyWebDriver.driver.findElement(By.name("address"));
	    txtAddress.clear();
	    txtAddress.sendKeys(address);
	    WebElement txtPhone = MyWebDriver.driver.findElement(By.name("phone"));
	    txtPhone.clear();
	    txtPhone.sendKeys(phone);
	    WebElement txtEmail = MyWebDriver.driver.findElement(By.name("email"));
	    txtEmail.clear();
	    txtEmail.sendKeys(email);
	    
	    WebElement btnUpdate = MyWebDriver.driver.findElement(By.name("update"));
	    MyWebDriver.scrollTo(btnUpdate);
	    btnUpdate.click();
	}

	@Then("^User sees empty field message for (.*)$")
	public void user_sees_empty_field_message_for(String field) throws Throwable {
		String expected = "Please fill out this field.";
	    String result = MyWebDriver.driver.findElement(By.name(field)).getAttribute("validationMessage");
	    MatcherAssert.assertThat(result, IsEqual.equalTo(expected));
	}
}

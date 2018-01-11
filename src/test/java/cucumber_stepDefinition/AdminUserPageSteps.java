package cucumber_stepDefinition;

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import util.MyWebDriver;

public class AdminUserPageSteps {
	@When("^User Navigates to Navigation Panel$")
	public void user_navigates_to_Navigation_Panel(){
		MyWebDriver.driver.findElement(By.id("hdNavigate")).click();
	}
	
	@When("^User Navigates to User List$")
	public void user_navigates_to_User_List() {
		MyWebDriver.driver.findElement(By.id("navUser")).click();
	}
	
	@Then("^User sees Location List Button$")
	public void user_sees_Location_List_Button() throws Throwable {
		String expected = "navLocation";
		MatcherAssert.assertThat(MyWebDriver.driver.findElement(By.id(expected)).isDisplayed(), IsEqual.equalTo(true));
	}

	@Then("^User Sees Order List Button$")
	public void user_Sees_Order_List_Button() throws Throwable {
		String expected = "navOrder";
		MatcherAssert.assertThat(MyWebDriver.driver.findElement(By.id(expected)).isDisplayed(), IsEqual.equalTo(true));
	}

	@Then("^User Sees Item List Button$")
	public void user_Sees_Item_List_Button() throws Throwable {
		String expected = "navItem";
		MatcherAssert.assertThat(MyWebDriver.driver.findElement(By.id(expected)).isDisplayed(), IsEqual.equalTo(true));
	}
	
	@Then("^User sees list of users in the specified format$")
	public void user_sees_list_of_users_in_the_specified_format() {
		String format = "[a-zA-Z]{1,20} [a-zA-Z]{1,20} Phone: [0-9]{10} Email: [a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}$";
		for(WebElement el : MyWebDriver.driver.findElements(By.id("user"))){
			if(!el.getText().matches(format)) {
				Assert.fail("Matching Error. Actual Value: " + el.getText());
			}
		}
	}
}

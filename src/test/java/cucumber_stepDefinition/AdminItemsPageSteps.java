package cucumber_stepDefinition;

import java.util.List;

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import util.MyWebDriver;
import util.Strings;

public class AdminItemsPageSteps {
	@When("^User Navigates to Item List$")
	public void user_Navigates_to_Item_List() throws Throwable {
		MyWebDriver.driver.findElement(By.id("navItem")).click();
	}

	@Then("^User Sees User List Button$")
	public void user_Sees_User_List_Button() throws Throwable {
		String expected = "navUser";
		MatcherAssert.assertThat(MyWebDriver.driver.findElement(By.id(expected)).isDisplayed(), IsEqual.equalTo(true));
	}

	@When("^User Adds an Item$")
	public void user_Adds_an_Item() throws Throwable {
		WebElement el = MyWebDriver.driver.findElement(By.id("newItem"));
		//name
		el.findElement(By.id("name")).sendKeys("Test Item");
		//price
		el.findElement(By.id("price")).sendKeys("1.11");
		//desc
		el.findElement(By.id("desc")).sendKeys("Test Item");
		WebElement create = el.findElement(By.id("create"));
		MyWebDriver.scrollTo(create);
		create.click();
	}

	@Then("^User is taken back to Items List$")
	public void user_is_taken_back_to_Items_List() throws Throwable {
		MatcherAssert.assertThat(MyWebDriver.driver.getCurrentUrl(), IsEqual.equalTo(Strings.AdminItemsPage));
	}

	@When("^User Deletes an Item$")
	public void user_Deletes_an_Item() throws Throwable {
		List<WebElement> items = MyWebDriver.driver.findElements(By.name("delete"));
		WebElement delete = items.get(items.size() - 1);
		MyWebDriver.scrollTo(delete);
		delete.click();
	}

	@When("^User Edits an Item$")
	public void user_Edits_an_Item() throws Throwable {
		WebElement update = MyWebDriver.driver.findElement(By.name("update"));
		MyWebDriver.scrollTo(update);
		update.click();
	}
	
	@Then("^User sees Edit button for each Item$")
	public void user_sees_Edit_button_for_each_Item() throws Throwable {
		for(WebElement el : MyWebDriver.driver.findElements(By.id("existingItem"))) {
			if(!el.findElement(By.name("update")).isDisplayed()){
				Assert.fail("Edit button not found for: " + el.findElement(By.id("name")).getText());
			}
		}
	}

	@Then("^User sees Delete button for each Item$")
	public void user_sees_Delete_button_for_each_Item() throws Throwable {
		for(WebElement el : MyWebDriver.driver.findElements(By.id("existingItem"))) {
			if(!el.findElement(By.name("delete")).isDisplayed()){
				Assert.fail("Delete button not found for: " + el.findElement(By.id("name")).getText());
			}
		}
	}
}

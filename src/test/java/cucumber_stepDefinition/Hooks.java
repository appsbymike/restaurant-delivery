package cucumber_stepDefinition;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import util.MyWebDriver;

public class Hooks {
	@After
	public void after(Scenario scenario) throws InterruptedException {
		//For some reason, Thread.sleep must be called before driver.quit()
		//In order to actually end the chromedriver process.
		//TODO: Find out how little time we can get away with
//		if(scenario.isFailed()) {
//			TakesScreenshot camera = (TakesScreenshot) MyWebDriver.driver;
//            byte[] screenshot = camera.getScreenshotAs(OutputType.BYTES);
//			scenario.embed(screenshot, "image/png");
//		}
		MyWebDriver.close();
	}
}

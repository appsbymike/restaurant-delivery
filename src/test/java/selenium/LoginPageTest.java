package selenium;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.hamcrest.core.IsEqual;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.common.io.Files;

import util.CreateWebDriver;
import util.Strings;

import static org.hamcrest.MatcherAssert.assertThat;

public class LoginPageTest {
	WebDriver driver;
	
	WebElement txtUsername;
	WebElement txtPassword;
	WebElement btnLogin;
	WebElement btnRegister;
	
	boolean loggedIn;
	
	@BeforeMethod
	public void before() {
		CreateWebDriver createWebDriver = new CreateWebDriver();
		driver = createWebDriver.createChromeDriver(Strings.loginPage);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		txtUsername = driver.findElement(By.id("username"));
		txtPassword = driver.findElement(By.id("password"));
		btnLogin = driver.findElement(By.id("login"));
		loggedIn = false;
	}
	
	@DataProvider
	public Iterator<Object[]> loginData(){
		ArrayList<Object[]> params = new ArrayList<Object[]>();
		params.add(new Object[] {"CASE05","mike","mike",true});
		params.add(new Object[] {"CASE06","mike","dasdsad",false});
		return params.iterator();
	}
	
	@Test(dataProvider="loginData")
	public void login(String desc,String username, String password, Boolean expected) throws InterruptedException {
		Reporter.log("Testing " + desc + ": ");
		txtUsername.sendKeys(username);
		txtPassword.sendKeys(password);
		btnLogin.click();
		Thread.sleep(250);
		
		if(expected) {
			loggedIn = true;
			assertThat(driver.getCurrentUrl(), IsEqual.equalTo(Strings.menuPage));
		}
		else {
			String failText = driver.findElement(By.id("invalid")).getText();
			assertThat(failText, IsEqual.equalTo("Invalid Username or Password"));
		}
	}
	
	@DataProvider
	public Iterator<Object[]> invalidFieldData(){
		ArrayList<Object[]> params = new ArrayList<Object[]>();
		params.add(new Object[] {"CASE01","\"mike","mike","username","invalid"});
		params.add(new Object[] {"CASE02","mike","$123##123","password","invalid"});
		params.add(new Object[] {"CASE03","","mike","username","missing"});
		params.add(new Object[] {"CASE04","mike","","password","missing"});
		return params.iterator();
	}
	
	//Cases where a field is invalid
	@Test(dataProvider="invalidFieldData")
	public void invalidField(String desc, String username, String password, String field, String expected) throws InterruptedException {
		Reporter.log("Testing " + desc + ": ");
		txtUsername.sendKeys(username);
		txtPassword.sendKeys(password);
		btnLogin.click();
		Thread.sleep(500);
		if(field.equals("username")) {
			if(expected.equals("invalid")) {
				String validator = "Please match the requested format.";
				String result = txtUsername.getAttribute("validationMessage");
				assertThat(result, IsEqual.equalTo(validator));
			}
			else if(expected.equals("missing")) {
				String validator = "Please fill out this field.";
				String result = txtUsername.getAttribute("validationMessage");
				assertThat(result, IsEqual.equalTo(validator));
			}
		}
		else if (field.equals("password")) {
			if(expected.equals("invalid")) {
				String validator = "Please match the requested format.";
				String result = txtPassword.getAttribute("validationMessage");
				assertThat(result, IsEqual.equalTo(validator));
			}
			else if(expected.equals("missing")) {
				String validator = "Please fill out this field.";
				String result = txtPassword.getAttribute("validationMessage");
				assertThat(result, IsEqual.equalTo(validator));
			}
		}
	}
	
	//Login as Admin
	@Test
	public void loginAsAdmin() throws InterruptedException {
		String username = "admin";
		String password = "admin";
		txtUsername.sendKeys(username);
		txtPassword.sendKeys(password);
		btnLogin.click();
		Thread.sleep(500);
		loggedIn = true;
		assertThat(driver.getCurrentUrl(), IsEqual.equalTo(Strings.htmlAdminRoot));
	}
	
	@AfterMethod
	public void after(ITestResult result) throws WebDriverException, IOException {
		if(result.getStatus() == ITestResult.FAILURE) {
			Reporter.log("Test Failed.");
			Object[] params = result.getParameters();
			String className = result.getTestClass().getClass().getSimpleName();
			String caseName = (String) params[0];
			String fileName = result.getTestContext().getOutputDirectory() + "\\" + className + "\\" + caseName + ".png";
			File newFile = new File(fileName);
			Files.createParentDirs(newFile);
			if(!newFile.exists()) {
				newFile.createNewFile();
			}
			Files.copy(((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE), newFile);
			System.out.println(newFile.getAbsolutePath());
			Reporter.setCurrentTestResult(result);
			Reporter.log("<br><a href='"+ newFile.getAbsolutePath() + "'> <img src='"+ newFile.getAbsolutePath() + "' height='200' width='200'/> </a>");
		}
		else {
			if(loggedIn) {
				try {
					driver.findElement(By.id("hdLogout")).click();
				}
				catch (Exception e) {}
			}
			Reporter.log("Test Passed.");
		}
		driver.quit();
	}
}

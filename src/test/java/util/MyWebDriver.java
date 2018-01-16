package util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class MyWebDriver {
	
	public static WebDriver driver;
	
	public static void createChromeDriver(String URL) {
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\src\\main\\resources\\chromedriver.exe");
		
		//Uncomment to use regular browser
//		ChromeDriver d = new ChromeDriver();
		
		//Comment to use regular browser
		ChromeOptions o = new ChromeOptions();
		o.addArguments("--headless");
		ChromeDriver d = new ChromeDriver(o);
		
		d.get(URL);
		driver = d;
	}
	
	public static void scrollTo(WebElement element) {
		JavascriptExecutor js = ((JavascriptExecutor) MyWebDriver.driver);
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	public static void close() throws InterruptedException {
		Thread.sleep(200);
		driver.quit();
	}
}

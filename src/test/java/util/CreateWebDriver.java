package util;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class CreateWebDriver {
	public ChromeDriver createDebugChromeDriver(String URL) {
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\src\\main\\resources\\chromedriver.exe");
		ChromeDriver d = new ChromeDriver();
		d.get(URL);
		return d;
	}
	
	public ChromeDriver createChromeDriver(String URL) {
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\src\\main\\resources\\chromedriver.exe");
		ChromeOptions o = new ChromeOptions();
		o.addArguments("--headless");
		ChromeDriver d = new ChromeDriver(o);
		d.get(URL);
		return d;
	}
}

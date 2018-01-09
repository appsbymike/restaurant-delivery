package util;

import org.openqa.selenium.chrome.ChromeDriver;

public class CreateWebDriver {
	public ChromeDriver createChromeDriver(String URL) {
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\src\\main\\resources\\chromedriver.exe");
		ChromeDriver d = new ChromeDriver();
		d.get(URL);
		return d;
	}
}

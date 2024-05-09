package sys;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public abstract class WebControl {
	protected static WebDriver driver;

	public WebControl() {
		System.setProperty("webdriver.chrome.driver", "src/exe/chromedriver.exe");
		driver = new ChromeDriver();
		driver.close();
	}
}

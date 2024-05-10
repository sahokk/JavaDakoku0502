package web_control;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebControl {
	private static ChromeOptions chromeOptions = new ChromeOptions();
	private static final WebDriver DRIVER = new ChromeDriver(chromeOptions);

	private WebControl() {
		System.setProperty("webdriver.chrome.driver", "src/web/chromedriver.exe");
		// chromeOptions.addArguments("--headless");
	}

	public static WebDriver getInstance() {
		return DRIVER;
	}
}
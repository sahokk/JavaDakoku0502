package web_control;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public abstract class WebControl {
	private WebDriver driver;
	private String pageUrl;
	private boolean flagLogin;
	private ChromeOptions chromeOptions;

	protected WebControl() {
		System.setProperty("webdriver.chrome.driver", "src/web_control/chromedriver.exe");
		chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--headless");
		this.driver = new ChromeDriver(chromeOptions);
		flagLogin = false;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public String getPageUrl() {
		return pageUrl;
	}

	protected void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	protected void accessLoginPage() {
		if (!flagLogin) {
			driver.get(pageUrl);
		}
	}

	public void finishDriver() {
		driver.close();
	}

	public void toggleFlagLogin() {
		this.flagLogin = !this.flagLogin;
	}

	public boolean isFlagLogin() {
		return flagLogin;
	}

}

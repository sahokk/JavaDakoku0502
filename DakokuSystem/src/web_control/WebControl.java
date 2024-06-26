package web_control;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public abstract class WebControl {
	private WebDriver driver;
	private String pageUrl;
	private boolean flagLogin;
	private static ChromeOptions chromeOptions = new ChromeOptions();

	protected WebControl() {
		chromeOptions.addArguments("--headless");
		this.driver = new ChromeDriver(chromeOptions);
		this.flagLogin = false;
	}

	public WebDriver getDriver() {
		return driver;
	}

	protected void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public String getPageUrl() {
		return pageUrl;
	}

	protected void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	public void accessLoginPage() {
		if (!flagLogin) {
			driver.navigate().to(pageUrl);
		}
		toggleFlagLogin(pageUrl);
	}

	public void toggleFlagLogin(String currentUrl) {
		this.flagLogin = currentUrl.contains("Login") ? false : !currentUrl.contains("Logout");
	}

	public boolean isFlagLogin() {
		return flagLogin;
	}

}

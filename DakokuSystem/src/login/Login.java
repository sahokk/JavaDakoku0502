package login;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import web_control.WebControl;

public abstract class Login {
	private String loginUrl;
	private By loginIdField, loginPassField, loginButton;
	private String loginId, loginPass;
	private WebControl webControl;
	private WebDriver driver;
	private boolean isLogin;

	protected Login(WebControl webControl, By loginIdField, By loginPassField, By loginButton, String loginId,
			String loginPass) {
		this.webControl = webControl;
		this.driver = webControl.getDriver();
		this.loginUrl = webControl.getPageUrl();
		this.loginIdField = loginIdField;
		this.loginPassField = loginPassField;
		this.loginButton = loginButton;
		this.loginId = loginId;
		this.loginPass = loginPass;
		this.isLogin = webControl.isFlagLogin();

	}

	private void sendLoginIdToField(String loginId) {
		if (loginId != "") {
			WebElement element = driver.findElement(loginIdField);
			element.clear();
			element.sendKeys(loginId);
		}

	}

	private void sendLoginPassToField(String loginPass) {
		if (loginPass != "") {
			WebElement element = driver.findElement(loginPassField);
			element.clear();
			element.sendKeys(loginPass);
		}
	}

	private void pushLoginButton() {
		driver.findElement(loginButton).click();
	}

	public boolean login() {
		if (!isLogin) {
			try {
				this.sendLoginIdToField(loginId);
				this.sendLoginPassToField(loginPass);
				this.pushLoginButton();
				if (!driver.getCurrentUrl().equals(loginUrl)) {
					webControl.toggleFlagLogin();
				}
			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}
		}

		return isLogin;

	}

	public boolean loginTest(String loginId, String loginPass) {
		if (!isLogin) {
			try {
				this.sendLoginIdToField(loginId);
				this.sendLoginPassToField(loginPass);
				this.pushLoginButton();
				if (!driver.getCurrentUrl().contains("Login")) {
					webControl.toggleFlagLogin();
				}
			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}
		}

		return isLogin;
	}

	public WebDriver getDriver() {
		return this.driver;
	}

}

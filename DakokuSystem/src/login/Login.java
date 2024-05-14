package login;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import web_control.WebControl;

public abstract class Login {
	private By loginIdField, loginPassField, loginButton;
	private String loginId, loginPass, logoutUrl;
	private WebControl webControl;
	private WebDriver driver;
	private boolean isLogin;

	protected Login(WebControl webControl, By loginIdField, By loginPassField, By loginButton, String logoutUrl) {
		this.webControl = webControl;
		this.driver = webControl.getDriver();
		this.loginIdField = loginIdField;
		this.loginPassField = loginPassField;
		this.loginButton = loginButton;
		this.logoutUrl = logoutUrl;
		this.loginId = "";
		this.loginPass = "";
		webControl.toggleFlagLogin(webControl.getPageUrl());
		this.isLogin = webControl.isFlagLogin();

	}

	protected void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	protected void setLoginPass(String loginPass) {
		this.loginPass = loginPass;
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

	protected void pushLogoutButton() {
		driver.navigate().to(logoutUrl);
		driver.navigate().to(webControl.getPageUrl());
	}

	public boolean login() {
		isLogin = webControl.isFlagLogin();
		if (!isLogin) {
			try {
				this.sendLoginIdToField(loginId);
				this.sendLoginPassToField(loginPass);
				this.pushLoginButton();
			} catch (NoSuchElementException e) {
				e.printStackTrace();
			} finally {
				webControl.toggleFlagLogin(driver.getCurrentUrl());
				isLogin = webControl.isFlagLogin();
			}
		}

		return isLogin;

	}

	public boolean loginTest(String loginId, String loginPass) {
		this.logout();
		if (!isLogin) {
			try {
				this.sendLoginIdToField(loginId);
				this.sendLoginPassToField(loginPass);
				this.pushLoginButton();
			} catch (NoSuchElementException e) {
				e.printStackTrace();
			} finally {
				webControl.toggleFlagLogin(driver.getCurrentUrl());
				isLogin = webControl.isFlagLogin();
			}
		}

		return isLogin;
	}

	private void logout() {
		isLogin = webControl.isFlagLogin();
		if (isLogin) {
			try {
				this.pushLogoutButton();
			} catch (NoSuchElementException e) {
				e.printStackTrace();
			} finally {
				webControl.toggleFlagLogin(driver.getCurrentUrl());
				isLogin = webControl.isFlagLogin();
				System.out.println("ログアウトしました");
			}
		}
	}

	public WebDriver getDriver() {
		return this.driver;
	}

}
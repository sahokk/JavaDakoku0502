package login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.NoSuchDriverException;

import web_control.WebControl;

public abstract class Login {
	private String loginUrl;
	private By loginIdField, loginPassField, loginButton;
	private String loginId, loginPass;
	private WebDriver driver = WebControl.getInstance();
	private boolean flagLogin;

	protected Login(String loginUrl, By loginIdField, By loginPassField, By loginButton) {
		this.loginUrl = loginUrl;
		this.loginIdField = loginIdField;
		this.loginPassField = loginPassField;
		this.loginButton = loginButton;
		this.loginId = null;
		this.loginPass = null;
		this.flagLogin = false;
	}

	private void accessLoginPage() {
		driver.get(loginUrl);
	}

	private void sendLoginIdToField() {
		if (this.loginId != null) {
			driver.findElement(loginIdField).sendKeys(loginId);
		}

	}

	private void sendLoginPassToField() {
		if (this.loginPass != null) {
			driver.findElement(loginPassField).sendKeys(loginPass);
		}
	}

	private void pushLoginButton() {
		driver.findElement(loginButton).click();
	}

	public boolean login() {
		try {
			this.accessLoginPage();
			this.sendLoginIdToField();
			this.sendLoginPassToField();
			this.pushLoginButton();
			if (!driver.getCurrentUrl().equals(loginUrl)) {
				flagLogin = true;
			}
		} catch (NoSuchDriverException e) {
			e.printStackTrace();
		}
		return flagLogin;

	}

	public boolean loginTest(String loginId, String loginPass) {
		this.setLoginId(loginId);
		this.setLoginPass(loginPass);
		boolean flagLogin = this.login();
		driver.close();
		return flagLogin;
	}

	protected void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	protected void setLoginPass(String loginPass) {
		this.loginPass = loginPass;
	}

	public WebDriver getDriver() {
		return this.driver;
	}

}

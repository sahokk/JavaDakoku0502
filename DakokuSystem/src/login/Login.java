package login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import sys.WebControl;

public abstract class Login extends WebControl {
	private String loginUrl;
	private By loginIdField, loginPassField, loginButton;
	private String loginId, loginPass;

	protected Login(String loginUrl, By loginIdField, By loginPassField, By loginButton) {
		super();
		this.loginUrl = loginUrl;
		this.loginIdField = loginIdField;
		this.loginPassField = loginPassField;
		this.loginButton = loginButton;
	}

	private void accessLoginPage() {
		getDriver().get(loginUrl);
	}

	private void sendLoginIdToField() {
		WebElement loginIdElement = getDriver().findElement(loginIdField);
		loginIdElement.sendKeys(loginId);
	}

	private void sendLoginPassToField() {
		WebElement loginPassElement = getDriver().findElement(loginPassField);
		loginPassElement.sendKeys(loginPass);
	}

	private void pushLoginButton() {
		getDriver().findElement(loginButton).click();
	}

	public void login() {
		this.accessLoginPage();
		this.sendLoginIdToField();
		this.sendLoginPassToField();
		this.pushLoginButton();
	}

	protected void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	protected void setLoginPass(String loginPass) {
		this.loginPass = loginPass;
	}

}
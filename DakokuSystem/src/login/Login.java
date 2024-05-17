package login;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import dao.DAO;
import db_control.DbColumns;
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
		this.loginId = null;
		this.loginPass = null;
		this.isLogin = webControl.isFlagLogin();

	}

	protected void setInfo(DAO dao) {
		this.loginId = dao.serchDBInfo(DbColumns.LOGIN_ID.getValue()).getUser();
		this.loginPass = dao.serchDBInfo(DbColumns.LOGIN_PASS.getValue()).getPass();
	}

	private void sendLoginIdToField(String loginId) {
		if (loginId != null) {
			WebElement element = driver.findElement(loginIdField);
			element.clear();
			element.sendKeys(loginId);
		}

	}

	private void sendLoginPassToField(String loginPass) {
		if (loginPass != null) {
			WebElement element = driver.findElement(loginPassField);
			element.clear();
			element.sendKeys(loginPass);
		}
	}

	private void pushLoginButton() {
		driver.findElement(loginButton).click();
	}

	private void pushLogoutButton() {
		driver.navigate().to(logoutUrl);
		webControl.toggleFlagLogin(logoutUrl);
		webControl.accessLoginPage();
	}

	public boolean login() {
		logout();
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

	public boolean login(String loginId, String loginPass) {
		logout();
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
				isLogin = webControl.isFlagLogin();
			}
		}
	}

	public void finishDriver() {
		if (driver != null) {
			driver.close();
			driver.quit();
		}
	}
}
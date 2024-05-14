package dakoku;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;

import login.Login;
import web_control.WebControl;

public abstract class Dakoku {

	private WebDriver driver;
	private Login login;
	private String dakokuUrl;
	private By dakokuButtonIn, dakokuButtonOut;

	private boolean flagDakoku;

	public Dakoku(WebControl webControl, Login login, String dakokuUrl, By dakokuButtonIn, By dakokuButtonOut) {
		this.driver = webControl.getDriver();
		this.login = login;
		this.dakokuUrl = dakokuUrl;
		this.dakokuButtonIn = dakokuButtonIn;
		this.dakokuButtonOut = dakokuButtonOut;
		this.flagDakoku = false;
	}

	protected void accessDakokuPage() {
		driver.navigate().to(dakokuUrl);

	}

	protected abstract void pushDakokuListButton();

	private void pushDakokuButtonIn() {
		driver.findElement(dakokuButtonIn).click();
	}

	private void pushDakokuButtonOut() {
		driver.findElement(dakokuButtonOut).click();
	}

	public boolean dakoku(Dakokustate dakokustate) {
		flagDakoku = false;
		try {
			boolean isLogin = login.login();
			if (isLogin) {
				this.accessDakokuPage();
				this.pushDakokuListButton();
				if (dakokustate == Dakokustate.IN) {
					this.pushDakokuButtonIn();
				} else {
					this.pushDakokuButtonOut();
				}
				flagDakoku = true;
			}
		} catch (ElementNotInteractableException e) {
			e.printStackTrace();
		}
		return flagDakoku;

	}

}

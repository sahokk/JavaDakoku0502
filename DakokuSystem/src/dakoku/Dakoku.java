package dakoku;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import login.Login;
import utility.Dakokustate;
import web_control.WebControl;

public abstract class Dakoku {

	private WebDriver driver;
	private WebControl webControl;
	private Login login;
	private String dakokuUrl;
	private By dakokuButtonIn, dakokuButtonOut;

	private boolean flagDakoku;

	public Dakoku(WebControl webControl, Login login, String dakokuUrl, By dakokuButtonIn, By dakokuButtonOut) {
		this.webControl = webControl;
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
		try {
			if (!webControl.isFlagLogin()) {
				login.login();
			}
			this.accessDakokuPage();
			this.pushDakokuListButton();
			switch (dakokustate) {
			case IN: {
				this.pushDakokuButtonIn();
				break;
			}
			case OUT: {
				this.pushDakokuButtonOut();
				break;
			}
			}
			flagDakoku = true;
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}

		return flagDakoku;
	}

}

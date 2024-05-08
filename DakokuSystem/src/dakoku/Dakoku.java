package dakoku;

import org.openqa.selenium.By;

import sys.WebControl;
import utility.Dakokustate;

public abstract class Dakoku extends WebControl {
	private String dakokuUrl;
	private By dakokuButtonIn, dakokuButtonOut;

	public Dakoku(String dakokuUrl, By dakokuButtonIn, By dakokuButtonOut) {
		super();
		this.dakokuUrl = dakokuUrl;
		this.dakokuButtonIn = dakokuButtonIn;
		this.dakokuButtonOut = dakokuButtonOut;
	}

	private void accessDakokuPage() {
		getDriver().get(dakokuUrl);
	}

	public abstract void pushDakokuListButton();

	private void pushDakokuButtonIn() {
		getDriver().findElement(dakokuButtonIn).click();
	}

	private void pushDakokuButtonOut() {
		getDriver().findElement(dakokuButtonOut).click();
	}

	public void dakoku(Dakokustate dakokustate) {
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
		default:
			throw new IllegalArgumentException("Unexpected value: " + dakokustate);
		}
	}

}
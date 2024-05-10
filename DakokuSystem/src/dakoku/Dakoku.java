package dakoku;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.NoSuchDriverException;

import utility.Dakokustate;

public abstract class Dakoku {

	private By dakokuButtonIn, dakokuButtonOut;
	protected WebDriver driver;
	private boolean flagDakoku = false;

	public Dakoku(By dakokuButtonIn, By dakokuButtonOut) {
		this.dakokuButtonIn = dakokuButtonIn;
		this.dakokuButtonOut = dakokuButtonOut;
	}

	protected abstract void accessDakokuPage();

	protected abstract void pushDakokuListButton();

	private void pushDakokuButtonIn() {
		driver.findElement(dakokuButtonIn).click();
	}

	private void pushDakokuButtonOut() {
		driver.findElement(dakokuButtonOut).click();
	}

	public boolean dakoku(Dakokustate dakokustate) {
		try {
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
		} catch (NoSuchDriverException e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}

		return flagDakoku;
	}

}

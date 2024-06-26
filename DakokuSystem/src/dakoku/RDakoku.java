package dakoku;

import org.openqa.selenium.By;

import db_control.RInformations;
import web_control.MyUrls;
import web_control.RWebControl;
import web_control.WebControl;

public class RDakoku extends Dakoku {
	private By dakokuListButton;
	private static WebControl webControl = RWebControl.getInstance();

	public RDakoku() {
		super(webControl, MyUrls.R_DAKOKU_URL.getValue(), RInformations.DAKOKU_BUTTON_IN.getValue(),
				RInformations.DAKOKU_BUTTON_OUT.getValue());
		this.dakokuListButton = RInformations.DAKOKU_LIST_BUTTON.getValue();
	}

	@Override
	protected void pushDakokuListButton() {
		webControl.getDriver().findElement(dakokuListButton).click();
	}

}

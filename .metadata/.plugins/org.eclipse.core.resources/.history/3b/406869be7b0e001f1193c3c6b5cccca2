package dakoku;

import org.openqa.selenium.By;

import login.RLogin;
import utility.Dakokustate;
import utility.MyUrls;
import utility.RInformations;

public class RDakoku extends Dakoku {
	private By dakokuListButton;

	public RDakoku() {
		super(MyUrls.R_DAKOKU_URL.getValue(), RInformations.DAKOKU_BUTTON_IN.getValue(),
				RInformations.DAKOKU_BUTTON_OUT.getValue());
		this.dakokuListButton = RInformations.DAKOKU_LIST_BUTTON.getValue();
	}

	@Override
	public void pushDakokuListButton() {
		driver.findElement(dakokuListButton).click();

	}

	@Override
	public void dakoku(Dakokustate dakokustate) {
		RLogin.getInstance().login();
		super.dakoku(dakokustate);
	}

}

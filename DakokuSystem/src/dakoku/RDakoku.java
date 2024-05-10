package dakoku;

import org.openqa.selenium.By;

import db_control.RInformations;
import login.RLogin;
import utility.Dakokustate;
import utility.MyUrls;

public class RDakoku extends Dakoku {
	private By dakokuListButton;
	private String dakokuUrl;

	public RDakoku() {
		super(RInformations.DAKOKU_BUTTON_IN.getValue(), RInformations.DAKOKU_BUTTON_OUT.getValue());
		this.dakokuListButton = RInformations.DAKOKU_LIST_BUTTON.getValue();
		this.dakokuUrl = MyUrls.R_DAKOKU_URL.getValue();
		super.driver = RLogin.getInstance().getDriver();
	}

	/**
	 * @param dakokustate: Dakokusutate 出勤か退勤か ログイン実行できれば、引数に応じて打刻をする。
	 *                     できなければログイン失敗を返す。
	 */
	@Override
	public boolean dakoku(Dakokustate dakokustate) {
		if (RLogin.getInstance().login()) {
			return super.dakoku(dakokustate);
		} else {

			return false;
		}

	}

	@Override
	protected void pushDakokuListButton() {
		driver.findElement(dakokuListButton).click();
	}

	@Override
	protected void accessDakokuPage() {
		driver.navigate().to(this.dakokuUrl);

	}

}

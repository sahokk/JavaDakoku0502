package dakoku;

import db_control.JcInformations;
import login.Login;
import web_control.JcWebControl;
import web_control.MyUrls;
import web_control.WebControl;

public class JcDakoku extends Dakoku {

	private static WebControl webControl = JcWebControl.getInstance();

	public JcDakoku(Login jcLogin) {
		super(webControl, jcLogin, MyUrls.JC_DAKOKU_URL.getValue(), JcInformations.DAKOKU_BUTTON_IN.getValue(),
				JcInformations.DAKOKU_BUTTON_OUT.getValue());

	}

	@Override
	protected void pushDakokuListButton() {
		// TODO 自動生成されたメソッド・スタブ

	}

}

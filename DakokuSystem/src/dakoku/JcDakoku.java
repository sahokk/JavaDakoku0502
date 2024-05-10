package dakoku;

import db_control.JcInformations;
import login.JcLogin;
import utility.Dakokustate;

public class JcDakoku extends Dakoku {

	public JcDakoku() {
		super(JcInformations.DAKOKU_BUTTON_IN.getValue(), JcInformations.DAKOKU_BUTTON_OUT.getValue());
		super.driver = JcLogin.getInstance().getDriver();
	}

	@Override
	public boolean dakoku(Dakokustate dakokustate) {
		if (JcLogin.getInstance().login()) {
			return super.dakoku(dakokustate);
		} else {
			return false;
		}

	}

	@Override
	protected void accessDakokuPage() {
	}

	@Override
	protected void pushDakokuListButton() {
		// TODO 自動生成されたメソッド・スタブ

	}

}

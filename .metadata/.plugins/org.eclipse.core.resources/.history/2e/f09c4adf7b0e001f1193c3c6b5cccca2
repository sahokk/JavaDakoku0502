package dakoku;

import login.JcLogin;
import utility.Dakokustate;
import utility.JcInformations;
import utility.MyUrls;

public class JcDakoku extends Dakoku {

	public JcDakoku() {
		super(MyUrls.JC_LOGIN_URL.getValue(), JcInformations.DAKOKU_BUTTON_IN.getValue(),
				JcInformations.DAKOKU_BUTTON_OUT.getValue());
	}

	@Override
	public void pushDakokuListButton() {
		// Jcの打刻では必要がないのでなし
	}

	@Override
	public void dakoku(Dakokustate dakokustate) {
		JcLogin.getInstance().login();
		super.dakoku(dakokustate);
	}

}

package dakoku;

import db_control.JcInformations;
import web_control.JcWebControl;
import web_control.MyUrls;
import web_control.WebControl;

public class JcDakoku extends Dakoku {

	private static WebControl webControl = JcWebControl.getInstance();

	public JcDakoku() {
		super(webControl, MyUrls.JC_DAKOKU_URL.getValue(), JcInformations.DAKOKU_BUTTON_IN.getValue(),
				JcInformations.DAKOKU_BUTTON_OUT.getValue());

	}

	@Override
	protected void pushDakokuListButton() {

	}

}

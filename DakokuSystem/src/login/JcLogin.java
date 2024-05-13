package login;

import db_control.DbColumns;
import db_control.DbControl;
import db_control.JcInformations;
import web_control.JcWebControl;

public class JcLogin extends Login {

	public JcLogin(DbControl dbControl) {
		super(JcWebControl.getInstance(), JcInformations.LOGIN_ID_FIELD.getValue(),
				JcInformations.LOGIN_PASS_FIELD.getValue(), JcInformations.LOGIN_BUTTON_FIELD.getValue(),
				dbControl.getInfo(DbColumns.LOGIN_ID), dbControl.getInfo(DbColumns.LOGIN_PASS));
	}

}

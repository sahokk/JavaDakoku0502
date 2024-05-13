package login;

import db_control.DbColumns;
import db_control.DbControl;
import db_control.RInformations;
import web_control.RWebControl;

public class RLogin extends Login {

	public RLogin(DbControl dbControl) {
		super(RWebControl.getInstance(), RInformations.LOGIN_ID_FIELD.getValue(),
				RInformations.LOGIN_PASS_FIELD.getValue(), RInformations.LOGIN_BUTTON_FIELD.getValue(),
				dbControl.getInfo(DbColumns.LOGIN_ID), dbControl.getInfo(DbColumns.LOGIN_PASS));
	}

}

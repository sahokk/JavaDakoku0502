package login;

import java.sql.Connection;

import dao.RDAO;
import db_control.RInformations;
import web_control.MyUrls;
import web_control.RWebControl;

public class RLogin extends Login {

	public RLogin(Connection con) {
		super(RWebControl.getInstance(), RInformations.LOGIN_ID_FIELD.getValue(),
				RInformations.LOGIN_PASS_FIELD.getValue(), RInformations.LOGIN_BUTTON_FIELD.getValue(),
				MyUrls.R_LOGOUT_URL.getValue());
		setInfo(new RDAO(con));
	}

}

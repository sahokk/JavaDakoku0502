package login;

import java.sql.Connection;

import dao.JcDAO;
import db_control.JcInformations;
import web_control.JcWebControl;
import web_control.MyUrls;

public class JcLogin extends Login {

	public JcLogin(Connection con) {
		super(JcWebControl.getInstance(), JcInformations.LOGIN_ID_FIELD.getValue(),
				JcInformations.LOGIN_PASS_FIELD.getValue(), JcInformations.LOGIN_BUTTON_FIELD.getValue(),
				MyUrls.JC_LOGOUT_URL.getValue());
		setInfo(new JcDAO(con));
	}

}

package login;

import dao.JcDAO;
import db_control.DbColumns;
import db_control.JcInformations;
import web_control.JcWebControl;
import web_control.MyUrls;

public class JcLogin extends Login {

	private JcDAO dao;

	public JcLogin(JcDAO dao) {
		super(JcWebControl.getInstance(), JcInformations.LOGIN_ID_FIELD.getValue(),
				JcInformations.LOGIN_PASS_FIELD.getValue(), JcInformations.LOGIN_BUTTON_FIELD.getValue(),
				MyUrls.JC_LOGOUT_URL.getValue());
		this.dao = dao;
	}

	@Override
	public boolean login() {
		super.setLoginId(dao.serchDBInfo(DbColumns.LOGIN_ID.getValue()).getUser());
		super.setLoginPass(dao.serchDBInfo(DbColumns.LOGIN_PASS.getValue()).getPass());
		return super.login();
	}

}

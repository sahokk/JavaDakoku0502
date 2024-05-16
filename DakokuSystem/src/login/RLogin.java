package login;

import dao.RDAO;
import db_control.DbColumns;
import db_control.RInformations;
import web_control.MyUrls;
import web_control.RWebControl;

public class RLogin extends Login {
	private RDAO dao;

	public RLogin(RDAO dao) {
		super(RWebControl.getInstance(), RInformations.LOGIN_ID_FIELD.getValue(),
				RInformations.LOGIN_PASS_FIELD.getValue(), RInformations.LOGIN_BUTTON_FIELD.getValue(),
				MyUrls.RLOGOUT_URL.getValue());
		this.dao = dao;
	}

	@Override
	public boolean login() {
		super.setLoginId(dao.serchDBInfo(DbColumns.LOGIN_ID.getValue()).getUser());
		super.setLoginPass(dao.serchDBInfo(DbColumns.LOGIN_PASS.getValue()).getPass());
		return super.login();
	}

}

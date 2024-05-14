package login;

import db_control.DbColumns;
import db_control.DbControl;
import db_control.JcInformations;
import web_control.JcWebControl;
import web_control.MyUrls;

public class JcLogin extends Login {

	private DbControl dbControl;

	public JcLogin(DbControl dbControl) {
		super(JcWebControl.getInstance(), JcInformations.LOGIN_ID_FIELD.getValue(),
				JcInformations.LOGIN_PASS_FIELD.getValue(), JcInformations.LOGIN_BUTTON_FIELD.getValue(),
				MyUrls.JC_LOGOUT_URL.getValue());
		this.dbControl = dbControl;
	}

	@Override
	public boolean login() {
		super.setLoginId(dbControl.getInfo(DbColumns.LOGIN_ID));
		super.setLoginPass(dbControl.getInfo(DbColumns.LOGIN_PASS));
		return super.login();
	}

}

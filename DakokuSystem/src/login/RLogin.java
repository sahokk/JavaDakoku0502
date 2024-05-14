package login;

import db_control.DbColumns;
import db_control.DbControl;
import db_control.RInformations;
import web_control.MyUrls;
import web_control.RWebControl;

public class RLogin extends Login {
	private DbControl dbControl;

	public RLogin(DbControl dbControl) {
		super(RWebControl.getInstance(), RInformations.LOGIN_ID_FIELD.getValue(),
				RInformations.LOGIN_PASS_FIELD.getValue(), RInformations.LOGIN_BUTTON_FIELD.getValue(),
				MyUrls.RLOGOUT_URL.getValue());
		this.dbControl = dbControl;
	}

	@Override
	public boolean login() {
		super.setLoginId(dbControl.getInfo(DbColumns.LOGIN_ID));
		super.setLoginPass(dbControl.getInfo(DbColumns.LOGIN_PASS));
		return super.login();
	}

}

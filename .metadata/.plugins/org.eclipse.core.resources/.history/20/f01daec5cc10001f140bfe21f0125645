package login;

import org.openqa.selenium.By;

import db_control.DbColumns;
import db_control.DbControl;
import db_control.RInfoControl;
import db_control.RInformations;
import utility.MyUrls;

public class RLogin extends Login {

	private static DbControl dbControl = RInfoControl.getInstance();
	private static Login rLogin = new RLogin(MyUrls.R_LOGIN_URL.getValue(), RInformations.LOGIN_ID_FIELD.getValue(),
			RInformations.LOGIN_PASS_FIELD.getValue(), RInformations.LOGIN_BUTTON_FIELD.getValue());

	private RLogin(String loginUrl, By loginIdField, By loginPassField, By loginButton) {
		super(loginUrl, loginIdField, loginPassField, loginButton);
	}

	public static Login getInstance() {
		return rLogin;
	}

	@Override
	public boolean login() {
		super.setLoginId(dbControl.getInfo(DbColumns.LOGIN_ID));
		super.setLoginPass(dbControl.getInfo(DbColumns.LOGIN_PASS));
		return super.login();
	}

}

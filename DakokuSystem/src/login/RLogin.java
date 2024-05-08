package login;

import org.openqa.selenium.By;

import db_control.RInfoControl;
import utility.DbColumns;
import utility.MyUrls;
import utility.RInformations;

public class RLogin extends Login {

	private static Login rLogin = new RLogin(MyUrls.R_LOGIN_URL.getValue(), RInformations.LOGIN_ID_FIELD.getValue(),
			RInformations.LOGIN_PASS_FIELD.getValue(), RInformations.LOGIN_BUTTON_FIELD.getValue());

	private RLogin(String loginUrl, By loginIdField, By loginPassField, By loginButton) {
		super(loginUrl, loginIdField, loginPassField, loginButton);
		super.setLoginId(new RInfoControl().getInfo(DbColumns.LOGIN_ID));
		super.setLoginPass(new RInfoControl().getInfo(DbColumns.LOGIN_PASS));
	}

	public static Login getInstance() {
		return rLogin;
	}

}

package login;

import org.openqa.selenium.By;

import db_control.DbColumns;
import db_control.DbControl;
import db_control.JcInfoControl;
import db_control.JcInformations;
import utility.MyUrls;

public class JcLogin extends Login {

	private static DbControl dbControl = JcInfoControl.getInstance();
	private static Login jcLogin = new JcLogin(MyUrls.JC_LOGIN_URL.getValue(), JcInformations.LOGIN_ID_FIELD.getValue(),
			JcInformations.LOGIN_PASS_FIELD.getValue(), JcInformations.LOGIN_BUTTON_FIELD.getValue());

	private JcLogin(String loginUrl, By loginIdField, By loginPassField, By loginButton) {
		super(loginUrl, loginIdField, loginPassField, loginButton);
	}

	public static Login getInstance() {
		return jcLogin;
	}

	@Override
	public boolean login() {
		super.setLoginId(dbControl.getInfo(DbColumns.LOGIN_ID));
		super.setLoginPass(dbControl.getInfo(DbColumns.LOGIN_PASS));
		return super.login();
	}

}

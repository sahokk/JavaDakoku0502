package login;

import org.openqa.selenium.By;

import db_control.JcInfoControl;
import utility.DbColumns;
import utility.JcInformations;
import utility.MyUrls;

public class JcLogin extends Login {

	private static Login jcLogin = new JcLogin(MyUrls.JC_LOGIN_URL.getValue(), JcInformations.LOGIN_ID_FIELD.getValue(),
			JcInformations.LOGIN_PASS_FIELD.getValue(), JcInformations.LOGIN_BUTTON_FIELD.getValue());

	private JcLogin(String loginUrl, By loginIdField, By loginPassField, By loginButton) {
		super(loginUrl, loginIdField, loginPassField, loginButton);
		super.setLoginId(new JcInfoControl().getInfo(DbColumns.LOGIN_ID));
		super.setLoginPass(new JcInfoControl().getInfo(DbColumns.LOGIN_PASS));
	}

	public static Login getInstance() {
		return jcLogin;
	}

}
package db_control;

import login.JcLogin;

public class JcInfoControl extends DbControl {

	private static DbControl dbControl = new JcInfoControl();

	private JcInfoControl() {
		super();
		super.setTableName(DbTableNames.JC_TABLE_NAME.getValue());
	}

	public static DbControl getInstance() {
		return dbControl;
	}

	@Override
	public String setInfo(String loginId, String loginPass) {
		boolean flagLogin = JcLogin.getInstance().loginTest(loginId, loginPass);
		return (flagLogin ? "ログインテストに成功しました。" : "ログインテストに失敗しました。") + super.setInfo(loginId, loginPass);

	}

	@Override
	public String updateInfo(String loginId, String loginPass) {
		boolean flagLogin = JcLogin.getInstance().loginTest(loginId, loginPass);
		return (flagLogin ? "ログインテストに成功しました。" : "ログインテストに失敗しました。") + super.updateInfo(loginId, loginPass);
	}

}

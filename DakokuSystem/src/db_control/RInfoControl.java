package db_control;

import login.RLogin;

public class RInfoControl extends DbControl {

	private static DbControl dbControl = new RInfoControl();

	private RInfoControl() {
		super();
		super.setTableName(DbTableNames.R_TABLE_NAME.getValue());
	}

	public static DbControl getInstance() {
		return dbControl;
	}

	@Override
	public String setInfo(String loginId, String loginPass) {
		boolean flagLogin = RLogin.getInstance().loginTest(loginId, loginPass);
		return (flagLogin ? "ログインテストに成功しました。" : "ログインテストに失敗しました。") + super.setInfo(loginId, loginPass);

	}

	@Override
	public String updateInfo(String loginId, String loginPass) {
		boolean flagLogin = RLogin.getInstance().loginTest(loginId, loginPass);
		return (flagLogin ? "ログインテストに成功しました。" : "ログインテストに失敗しました。") + super.updateInfo(loginId, loginPass);
	}

}

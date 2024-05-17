package run;

import java.awt.Dialog;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import dakoku.Dakoku;
import dakoku.Dakokustate;
import dakoku.JcDakoku;
import dakoku.RDakoku;
import dao.ConnectionManager;
import dao.JcDAO;
import dao.RDAO;
import login.JcLogin;
import login.Login;
import login.RLogin;
import settings.RunOnce;
import ui_control.MainFrame;
import ui_control.SettingDialog;

public class MainSystem {
	private static Login jcLogin;
	private static Login rLogin;
	private static Dakoku jcDakoku;
	private static Dakoku rDakoku;
	private static Dakokustate dakokustate;
	private static SettingDialog sd;
	private SimpleDateFormat dateFormat;
	private Calendar calendar;

	private Connection con;
	private boolean isConnectable = false;
	private boolean isFirstOpened = true;

	private static MainSystem mainSystem = new MainSystem();

	private MainSystem() {
		dateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH時mm分");
	}

	public static MainSystem getInstance() {
		return mainSystem;
	}

	public boolean[] doLogin(boolean[] loginItem) {
		boolean loginState[] = { false, false };
		if (loginItem[0]) {
			jcLogin = new JcLogin(con);
			loginState[0] = jcLogin.login();
		}
		if (loginItem[1]) {
			rLogin = new RLogin(con);
			loginState[1] = rLogin.login();
		}
		return loginState;

	}

	public boolean doLoginTest(int loginItem, String loginId, String loginPass) {
		if (loginItem == 0) {
			jcLogin = new JcLogin(con);
			return jcLogin.login(loginId, loginPass);
		}
		if (loginItem == 1) {
			rLogin = new RLogin(con);
			return rLogin.login(loginId, loginPass);
		}
		return false;
	}

	public String doDakoku(boolean jc, boolean r) {
		String res = "";
		calendar = Calendar.getInstance();
		dakokustate = calendar.get(Calendar.AM_PM) == Calendar.AM ? Dakokustate.IN : Dakokustate.OUT;
		boolean[] loginState = doLogin(new boolean[] { jc, r });
		if (loginState[0]) {
			jcDakoku = new JcDakoku();
			res += "Jc: "
					+ (jcDakoku.dakoku(dakokustate) ? "打刻完了 " + dateFormat.format(new Date()) + dakokustate.getValue()
							: "打刻失敗")
					+ "\n";
		}
		if (loginState[1]) {
			rDakoku = new RDakoku();
			res += "R: "
					+ (rDakoku.dakoku(dakokustate) ? "打刻完了 " + dateFormat.format(new Date()) + dakokustate.getValue()
							: "打刻失敗")
					+ "\n";
		}
		return res;
	}

	public String pushSettingButton(MainFrame frame) {
		sd = new SettingDialog(frame);
		sd.setBounds(100, 100, 500, 200);
		sd.setResizable(false);
		sd.setVisible(true);
		sd.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		String str = "";
		if (!sd.isCanceled()) {
			boolean[] setItem = { !sd.getJcLoginIdField().isBlank() && !sd.getJcLoginPassField().isBlank(),
					!sd.getRLoginIdField().isBlank() && !sd.getRLoginPassField().isBlank() };

			if (setItem[0]) {
				boolean isLogin = doLoginTest(0, sd.getJcLoginIdField(), sd.getJcLoginPassField());
				str += new JcDAO(con).updateDBInfo(isLogin, sd.getJcLoginIdField(), sd.getJcLoginPassField()) + "\n";
			}
			if (setItem[1]) {
				boolean isLogin = doLoginTest(1, sd.getRLoginIdField(), sd.getRLoginPassField());
				str += new RDAO(con).updateDBInfo(isLogin, sd.getRLoginIdField(), sd.getRLoginPassField()) + "\n";
			}
		}
		return str;
	}

	public void quitDriver() {
		if (jcLogin != null)
			jcLogin.finishDriver();
		if (rLogin != null)
			rLogin.finishDriver();
	}

	public boolean isConnectableDB(String user, String pass) {
		con = ConnectionManager.getConnection(user, pass);
		RunOnce runOnce = RunOnce.getInstance(con);
		isConnectable = (con != null);
		if (isConnectable) {
			isFirstOpened = runOnce.run();
			runOnce.setFirst(isFirstOpened);
		}
		return isConnectable;
	}

	public boolean isFirstOpened() {
		return isFirstOpened;
	}

}

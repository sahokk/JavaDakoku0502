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
import dao.DAO;
import dao.JcDAO;
import dao.RDAO;
import login.JcLogin;
import login.Login;
import login.RLogin;
import settings.RunOnce;
import ui_control.MainFrame;
import ui_control.SettingDialog;
import web_control.JcWebControl;
import web_control.RWebControl;

public class MainSystem {
	private static Login jcLogin;
	private static Login rLogin;
	private static Dakoku jcDakoku;
	private static Dakoku rDakoku;
	private JcDAO jcDao;
	private RDAO rDao;
	private static Dakokustate dakokustate;
	private static SettingDialog sd;
	private SimpleDateFormat dateFormat;
	private Calendar calendar;

	private Connection con;

	private boolean isConnectable = false;

	private boolean isFirstOpened = true;

	public MainSystem() {
		con = ConnectionManager.getConnection();
		jcDao = new JcDAO(con);
		jcLogin = new JcLogin(jcDao);

		rDao = new RDAO(con);
		rLogin = new RLogin(rDao);
		dateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH時mm分");

	}

	public void doLogin(boolean jc, boolean r) {
		if (jc) {
			jcLogin.login();
		}
		if (r) {
			rLogin.login();
		}

	}

	public String doDakoku(boolean jc, boolean r) {
		String res = "";
		calendar = Calendar.getInstance();
		dakokustate = calendar.get(Calendar.AM_PM) == Calendar.AM ? Dakokustate.IN : Dakokustate.OUT;

		if (jc) {
			jcDakoku = new JcDakoku(jcLogin);
			res += "Jc: "
					+ (jcDakoku.dakoku(dakokustate) ? "打刻完了 " + dateFormat.format(new Date()) + dakokustate.getValue()
							: "打刻失敗")
					+ "\n";
		}
		if (r) {
			rDakoku = new RDakoku(rLogin);
			res += "R: "
					+ (rDakoku.dakoku(dakokustate) ? "打刻完了 " + dateFormat.format(new Date()) + dakokustate.getValue()
							: "打刻失敗")
					+ "\n";
		}
		return res;
	}

	public String pushSettingButton(MainFrame frame, boolean isFirstOpened) {
		sd = new SettingDialog(frame);
		sd.setBounds(100, 100, 500, 200);
		sd.setResizable(false);
		sd.setVisible(true);
		sd.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		String str = "";
		if (!sd.isCanceled()) {
			con = ConnectionManager.getConnection();
			if (!sd.getJcLoginIdField().isBlank() && !sd.getJcLoginPassField().isBlank()) {
				DAO dao = new JcDAO(con);
				str += dao.updateDBInfo(jcLogin, sd.getJcLoginIdField(), sd.getJcLoginPassField()) + "\n";
			}
			if (!sd.getRLoginIdField().isBlank() && !sd.getRLoginPassField().isBlank()) {
				DAO dao = new RDAO(con);
				str += dao.updateDBInfo(rLogin, sd.getRLoginIdField(), sd.getRLoginPassField()) + "\n";
			}
		}
		return str;
	}

	public void quitDriver() {
		JcWebControl.getInstance().finishDriver();
		RWebControl.getInstance().finishDriver();
	}

	public boolean isConnectableDB(String user, String pass) {
		RunOnce runOnce = RunOnce.getInstance(user, pass, con);
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

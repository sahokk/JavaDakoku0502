package run;

import java.awt.Dialog;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import dakoku.Dakoku;
import dakoku.Dakokustate;
import dakoku.JcDakoku;
import dakoku.RDakoku;
import db_control.DbControl;
import db_control.JcInfoControl;
import db_control.RInfoControl;
import login.JcLogin;
import login.Login;
import login.RLogin;
import ui_control.MainFrame;
import ui_control.SettingDialog;

public class MainSystem {
	private static Login jcLogin;
	private static Login rLogin;
	private static Dakoku jcDakoku;
	private static Dakoku rDakoku;
	private DbControl jcInfoControl;
	private DbControl rInfoControl;
	private static Dakokustate dakokustate;
	private static SettingDialog sd;
	private SimpleDateFormat dateFormat;
	private Calendar calendar;

	public MainSystem() {
		jcInfoControl = new JcInfoControl();
		jcLogin = new JcLogin(jcInfoControl);

		rInfoControl = new RInfoControl();
		rLogin = new RLogin(rInfoControl);
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
			res += "Jc:"
					+ (jcDakoku.dakoku(dakokustate) ? "打刻完了" + dateFormat.format(new Date()) + dakokustate.getValue()
							: "打刻失敗")
					+ "\n";
		}
		if (r) {
			rDakoku = new RDakoku(rLogin);
			res += "R:" + (rDakoku.dakoku(dakokustate) ? "打刻完了" + dateFormat.format(new Date()) + dakokustate.getValue()
					: "打刻失敗") + "\n";
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
			if (sd.getJcLoginIdField() != "" && sd.getJcLoginPassField() != "") {
				str += jcInfoControl.updateInfo(jcLogin, sd.getJcLoginIdField(), sd.getJcLoginPassField()) + "\n";
			}
			if (sd.getRLoginIdField() != "" && sd.getRLoginPassField() != "") {
				str += rInfoControl.updateInfo(rLogin, sd.getRLoginIdField(), sd.getRLoginPassField()) + "\n";
			}
		}
		return str;
	}

	public void finishDriver() {
		jcLogin.getDriver().close();
		jcLogin.getDriver().quit();
		rLogin.getDriver().close();
		rLogin.getDriver().quit();
	}
}
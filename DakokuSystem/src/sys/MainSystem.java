package sys;

import java.awt.Dialog;

import dakoku.Dakoku;
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
import utility.Dakokustate;

public class MainSystem {
	private static Login jcLogin;
	private static Login rLogin;
	private static Dakoku jcDakoku;
	private static Dakoku rDakoku;
	private static DbControl jcInfoControl;
	private static DbControl rInfoControl;
	// private static WebDriver driver;
	private static Dakokustate dakokustate;
	private static SettingDialog sd;

	public MainSystem() {
		jcLogin = JcLogin.getInstance();
		rLogin = RLogin.getInstance();
		jcInfoControl = JcInfoControl.getInstance();
		rInfoControl = RInfoControl.getInstance();
		// driver = WebControl.getInstance();
	}

	public void doLogin(boolean jc, boolean r) {
		if (jc) {
			jcLogin.login();
		}
		if (r) {
			rLogin.login();
		}

	}

	public void doDakoku(boolean jc, boolean r) {
//		時間によってステートを変える　出社か退社か
		dakokustate = Dakokustate.OUT;
		if (jc) {
			jcDakoku = new JcDakoku();
			jcDakoku.dakoku(dakokustate);
		}
		if (r) {
			rDakoku = new RDakoku();
			rDakoku.dakoku(dakokustate);
		}
	}

	public String[] settingButton(MainFrame frame) {
		sd = new SettingDialog(frame);
		sd.setBounds(100, 100, 500, 200);
		sd.setResizable(false);
		sd.setVisible(true);
		sd.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);

		if (!sd.isCanceled()) {
			String[] str = {
					jcInfoControl.updateInfo(sd.getJcLoginIdField().getText(), sd.getJcLoginPassField().getText()),
					rInfoControl.updateInfo(sd.getrLoginIdField().getText(), sd.getrLoginPassField().getText()) };
			return str;
		}
		return null;
	}
}

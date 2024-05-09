package sys;

import dakoku.Dakoku;
import dakoku.JcDakoku;
import dakoku.RDakoku;
import db_control.DbControl;
import login.JcLogin;
import login.Login;
import login.RLogin;
import utility.Dakokustate;

public class MainSystem {
	private static Login JC_LOGIN;
	private static Login R_LOGIN;
	private static Dakoku jcDakoku;
	private static Dakoku rDakoku;
	private static DbControl jcInfoControl;
	private static DbControl rInfoControl;
	private static Dakokustate dakokustate;

	public MainSystem() {
		JC_LOGIN = JcLogin.getInstance();
		R_LOGIN = RLogin.getInstance();
	}

	public void login(boolean jc, boolean r) {
		if (jc) {
			JC_LOGIN.login();
		}
		if (r) {
			R_LOGIN.login();
		}

	}

	public void dakoku(boolean jc, boolean r) {
//		時間によってステートを変える　出社か退社か
		dakokustate = Dakokustate.IN;
		if (jc) {
			jcDakoku = new JcDakoku();
			jcDakoku.dakoku(dakokustate);
		}
		if (r) {
			rDakoku = new RDakoku();
			rDakoku.dakoku(dakokustate);
		}
	}
}

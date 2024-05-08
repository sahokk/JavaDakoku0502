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
	private static final Login JC_LOGIN = JcLogin.getInstance();
	private static final Login R_LOGIN = RLogin.getInstance();
	private Dakoku jcDakoku;
	private Dakoku rDakoku;
	private DbControl jcInfoControl;
	private DbControl rInfoControl;
	private Dakokustate dakokustate;

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
		this.dakokustate = Dakokustate.OUT;
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

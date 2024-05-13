package web_control;

public class JcWebControl extends WebControl {

	private static JcWebControl webControl = new JcWebControl();

	private JcWebControl() {
		super();
		setPageUrl(MyUrls.JC_LOGIN_URL.getValue());
		accessLoginPage();
	}

	public static JcWebControl getInstance() {
		return webControl;
	}

}

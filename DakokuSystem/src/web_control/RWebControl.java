package web_control;

public class RWebControl extends WebControl {

	private static RWebControl webControl = new RWebControl();

	private RWebControl() {
		super();
		setPageUrl(MyUrls.R_LOGIN_URL.getValue());
		accessLoginPage();
	}

	public static RWebControl getInstance() {
		return webControl;
	}

}

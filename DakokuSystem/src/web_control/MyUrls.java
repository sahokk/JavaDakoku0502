package web_control;

public enum MyUrls {
	JC_LOGIN_URL("https://z001.majorflowz.com/MFZT/2515/ZtAccount/Login"),
	JC_DAKOKU_URL("https://z001.majorflowz.com/MFZT/2515/"),
	JC_LOGOUT_URL("https://z001.majorflowz.com/MFZT/2515/ZtLogout/Logout"),
	R_LOGIN_URL("https://r-system1.com/Login/Index/0"),
	R_DAKOKU_URL("https://r-system1.com/TimeCard"),
	R_LOGOUT_URL("https://r-system1.com/Login/Index/0");

	private String str;

	private MyUrls(String str) {
		this.str = str;
	}

	public String getValue() {
		return this.str;
	}
}

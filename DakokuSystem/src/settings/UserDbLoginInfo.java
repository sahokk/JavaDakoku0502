package settings;

public class UserDbLoginInfo {
	private static String user;
	private static String pass;

	private static UserDbLoginInfo userDbLoginInfo = new UserDbLoginInfo();

	private UserDbLoginInfo() {

	}

	public static String getUser() {
		return user;
	}

	public static void setUser(String user) {
		UserDbLoginInfo.user = user;
	}

	public static String getPass() {
		return pass;
	}

	public static void setPass(String pass) {
		UserDbLoginInfo.pass = pass;
	}

	public static UserDbLoginInfo getInstance() {
		return userDbLoginInfo;
	}

}

package db_control;

public enum DbLoginInfo {
	ROOT_URL("jdbc:mysql://localhost:3306/mysql"),
	GIKEN_DB_NAME("db_giken2"),
	GIKEN_URL("jdbc:mysql://localhost:3306/db_giken2");

	private String str;

	private DbLoginInfo(String str) {
		this.str = str;
	}

	public String getValue() {
		return this.str;
	}
}

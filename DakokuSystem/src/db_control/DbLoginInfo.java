package db_control;

public enum DbLoginInfo {
	ROOT_USER("root"),
	ROOT_PASSWORD("Tech_123"),
	ROOT_URL("jdbc:mysql://localhost:3306/techdb"),
	GIKEN_USER("testuser"),
	GIKEN_PASSWORD("giken2"),
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
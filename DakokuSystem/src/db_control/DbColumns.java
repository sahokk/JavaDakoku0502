package db_control;

public enum DbColumns {
	LOGIN_ID("login_id"), LOGIN_PASS("login_pass");

	private String columnsName;

	private DbColumns(String columnsName) {
		this.columnsName = columnsName;
	}

	public String getValue() {
		return this.columnsName;
	}
}

package db_control;

public enum DbColumns {
	LOGIN_ID("login_id", "VARCHAR(50)"), LOGIN_PASS("login_pass", "VARCHAR(50)");

	private String columnsName;
	private String dataType;

	private DbColumns(String columnsName, String dataType) {
		this.columnsName = columnsName;
		this.dataType = dataType;
	}

	public String getValue() {
		return this.columnsName;
	}

	public String getInfo() {
		return this.columnsName + " " + this.dataType;
	}
}

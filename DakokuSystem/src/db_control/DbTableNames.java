package db_control;

public enum DbTableNames {
	JC_TABLE_NAME("jc_info"),
	R_TABLE_NAME("r_info");

	private String str;

	private DbTableNames(String str) {
		this.str = str;
	}

	public String getValue() {
		return this.str;
	}
}

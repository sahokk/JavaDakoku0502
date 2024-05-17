package db_control;

import java.sql.Connection;

import dao.ConnectionManager;

public abstract class DbControl extends ConnectionManager {
	private static Connection con;
	private String colmunsName;
	private String tableName;

	public DbControl(Connection con) {
		DbControl.con = con;
		this.colmunsName = "";

	}

	protected void setTableName(String tableName) {
		this.tableName = tableName;
	}

	protected static Connection getSqlCon() {
		return sqlCon;
	}

}

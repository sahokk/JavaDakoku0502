package entity;

import java.io.Serializable;

import db_control.DbColumns;

public class DBInfo implements Serializable {
	private String user;
	private String pass;

	public DBInfo() {
	}

	public DBInfo(String colmunsName, String str) {
		if (colmunsName.equals(DbColumns.LOGIN_ID.getValue())) {
			this.user = str;
		} else {
			this.pass = str;
		}
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

}

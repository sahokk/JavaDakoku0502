package settings;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db_control.DbColumns;
import db_control.DbControl;
import db_control.DbLoginInfo;
import db_control.DbTableNames;

public class RunOnce extends DbControl {

	private PreparedStatement sqlStmt = null;
	private ResultSet res = null;

	private boolean isFirst;

	private static RunOnce runOnce = new RunOnce();

	private RunOnce() {
		super();
		this.isFirst = true;
	}

	private void checkFirst() throws SQLException {
		try {
			sqlStmt = getSqlCon().prepareStatement("show databases;");
			res = sqlStmt.executeQuery();
			while (res.next()) {
				if (res.getString("Database").equals(DbLoginInfo.GIKEN_DB_NAME.getValue())) {
					isFirst = false;
					break;
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	private void makeDB() throws SQLException {
		try {
			sqlStmt = getSqlCon().prepareStatement("CREATE DATABASE " + DbLoginInfo.GIKEN_DB_NAME.getValue() + ";");
			sqlStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	private void makeTables() throws SQLException {
		try {
			getSqlCon().setAutoCommit(false);
			useGikenDB();

			sqlStmt = getSqlCon().prepareStatement("CREATE TABLE " + DbTableNames.JC_TABLE_NAME.getValue() + " ("
					+ DbColumns.LOGIN_ID.getInfo() + ", " + DbColumns.LOGIN_PASS.getInfo() + ");");
			sqlStmt.executeUpdate();

			sqlStmt = getSqlCon()
					.prepareStatement("INSERT INTO " + DbTableNames.JC_TABLE_NAME.getValue() + " VALUES (?, ?);");
			sqlStmt.setString(1, "");
			sqlStmt.setString(2, "");
			sqlStmt.executeUpdate();

			sqlStmt = getSqlCon().prepareStatement("CREATE TABLE " + DbTableNames.R_TABLE_NAME.getValue() + " ("
					+ DbColumns.LOGIN_ID.getInfo() + ", " + DbColumns.LOGIN_PASS.getInfo() + ");");
			sqlStmt.executeUpdate();

			sqlStmt = getSqlCon()
					.prepareStatement("INSERT INTO " + DbTableNames.R_TABLE_NAME.getValue() + " VALUES (?, ?);");
			sqlStmt.setString(1, "");
			sqlStmt.setString(2, "");
			sqlStmt.executeUpdate();

			getSqlCon().commit();
		} catch (SQLException e) {
			try {
				getSqlCon().rollback();
			} catch (Exception e2) {
				throw e2;
			}
			throw e;
		} finally {
			getSqlCon().setAutoCommit(true);
		}
	}

	public boolean loginCheck() {
		connectDB();
		return isConnectable();
	}

	public boolean run() {
		if (isConnectable()) {
			try {
				this.checkFirst();
				if (isFirst) {
					this.makeDB();
					this.makeTables();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				closeDB(res, sqlStmt, getSqlCon());
			}
		}
		return isFirst;
	}

	public static RunOnce getInstance(String user, String pass) {
		setUser(user);
		setPass(pass);
		return runOnce;
	}

	public void setFirst(boolean isFirst) {
		this.isFirst = isFirst;
	}

}

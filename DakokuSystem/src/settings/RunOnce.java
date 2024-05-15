package settings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db_control.DbColumns;
import db_control.DbLoginInfo;
import db_control.DbTableNames;

public class RunOnce {

	private static Connection sqlCon = null;
	private PreparedStatement sqlStmt = null;
	private ResultSet res = null;

	private static void connectDB() {
		try {
			sqlCon = DriverManager.getConnection(DbLoginInfo.ROOT_URL.getValue(), DbLoginInfo.ROOT_USER.getValue(),
					DbLoginInfo.ROOT_PASSWORD.getValue());
			System.out.println("DB connect success");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void closeDB(ResultSet res, PreparedStatement stmt, Connection con) {
		try {
			if (res != null) {
				res.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private boolean checkFirst() throws SQLException {
		try {
			sqlStmt = sqlCon.prepareStatement("show databases;");
			res = sqlStmt.executeQuery();
			while (res.next()) {
				if (res.getString("Database").equals(DbLoginInfo.GIKEN_DB_NAME.getValue())) {
					return false;
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return true;
	}

	private void makeDB() throws SQLException {
		try {
			sqlStmt = sqlCon.prepareStatement("CREATE DATABASE " + DbLoginInfo.GIKEN_DB_NAME.getValue() + ";");
			sqlStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	private void makeTables() throws SQLException {
		try {
			sqlCon.setAutoCommit(false);
			sqlStmt = sqlCon.prepareStatement("USE " + DbLoginInfo.GIKEN_DB_NAME.getValue() + ";");
			sqlStmt.executeUpdate();

			sqlStmt = sqlCon.prepareStatement("CREATE TABLE " + DbTableNames.JC_TABLE_NAME.getValue() + " ("
					+ DbColumns.LOGIN_ID.getInfo() + ", " + DbColumns.LOGIN_PASS.getInfo() + ");");
			sqlStmt.executeUpdate();

			sqlStmt = sqlCon
					.prepareStatement("INSERT INTO " + DbTableNames.JC_TABLE_NAME.getValue() + " VALUES (?, ?);");
			sqlStmt.setString(1, "");
			sqlStmt.setString(2, "");
			sqlStmt.executeUpdate();

			sqlStmt = sqlCon.prepareStatement("CREATE TABLE " + DbTableNames.R_TABLE_NAME.getValue() + " ("
					+ DbColumns.LOGIN_ID.getInfo() + ", " + DbColumns.LOGIN_PASS.getInfo() + ");");
			sqlStmt.executeUpdate();

			sqlStmt = sqlCon
					.prepareStatement("INSERT INTO " + DbTableNames.R_TABLE_NAME.getValue() + " VALUES (?, ?);");
			sqlStmt.setString(1, "");
			sqlStmt.setString(2, "");
			sqlStmt.executeUpdate();

			sqlCon.commit();
		} catch (SQLException e) {
			sqlCon.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			sqlCon.setAutoCommit(true);
		}
	}

	public boolean run() {
		connectDB();
		try {
			if (this.checkFirst()) {

				this.makeDB();
				this.makeTables();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDB(res, sqlStmt, sqlCon);
		}
		return false;
	}

}

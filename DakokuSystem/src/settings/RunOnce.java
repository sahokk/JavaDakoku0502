package settings;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.DAO;
import db_control.DbColumns;
import db_control.DbLoginInfo;
import db_control.DbTableNames;

public class RunOnce extends DAO {

	private boolean isFirst;

	private static RunOnce runOnce = new RunOnce();

	private static Connection con;

	private RunOnce() {
		super(con);
		this.isFirst = true;
	}

	private void checkFirst() throws SQLException {
		String sql = "show databases;";
		PreparedStatement stmt = null;
		ResultSet res = null;
		try {
			stmt = con.prepareStatement(sql);
			res = stmt.executeQuery();
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
		String sql = "CREATE DATABASE " + DbLoginInfo.GIKEN_DB_NAME.getValue() + ";";
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(sql);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	private void makeTables() throws SQLException {
		PreparedStatement stmt = null;
		try {
			con.setAutoCommit(false);
			switchDB();

			stmt = con.prepareStatement("CREATE TABLE " + DbTableNames.JC_TABLE_NAME.getValue() + " ("
					+ DbColumns.LOGIN_ID.getInfo() + ", " + DbColumns.LOGIN_PASS.getInfo() + ");");
			stmt.executeUpdate();

			stmt = con.prepareStatement("INSERT INTO " + DbTableNames.JC_TABLE_NAME.getValue() + " VALUES (?, ?);");
			stmt.setString(1, "");
			stmt.setString(2, "");
			stmt.executeUpdate();

			stmt = con.prepareStatement("CREATE TABLE " + DbTableNames.R_TABLE_NAME.getValue() + " ("
					+ DbColumns.LOGIN_ID.getInfo() + ", " + DbColumns.LOGIN_PASS.getInfo() + ");");
			stmt.executeUpdate();

			stmt = con.prepareStatement("INSERT INTO " + DbTableNames.R_TABLE_NAME.getValue() + " VALUES (?, ?);");
			stmt.setString(1, "");
			stmt.setString(2, "");
			stmt.executeUpdate();

			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (Exception e2) {
				throw e2;
			}
			throw e;
		} finally {
			con.setAutoCommit(true);
		}
	}

	public boolean run() {
		if (con != null) {
			try {
				this.checkFirst();
				if (isFirst) {
					this.makeDB();
					this.makeTables();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
			}
		}
		return isFirst;
	}

	public Connection getCon() {
		return con;
	}

	public static RunOnce getInstance(Connection con) {
		RunOnce.con = con;
		return runOnce;
	}

	public void setFirst(boolean isFirst) {
		this.isFirst = isFirst;
	}

}

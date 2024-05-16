package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db_control.DbColumns;
import db_control.DbLoginInfo;
import entity.DBInfo;
import login.Login;

public abstract class DAO {
	protected Connection con;
	private String tableName;
	private String dbName;

	protected DAO(Connection con) {
		this.con = con;
		this.dbName = DbLoginInfo.GIKEN_DB_NAME.getValue();
	}

	protected void setTableName(String tableName) {
		this.tableName = tableName;
	}

	protected void switchDB() throws SQLException {
		String sql = "USE " + dbName + ";";
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(sql);
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw e;
		}
	}

	public DBInfo serchDBInfo(String colmunsName) {
		String sql = "SELECT " + colmunsName + " FROM " + tableName + ";";
		PreparedStatement stmt = null;
		ResultSet res = null;
		DBInfo dbInfo = null;
		try {
			switchDB();
			stmt = con.prepareStatement(sql);
			res = stmt.executeQuery();
			if (res.next()) {
				dbInfo = new DBInfo(colmunsName, res.getString(colmunsName));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDB(res, stmt);
		}
		return dbInfo;
	}

	public String updateDBInfo(Login login, String loginId, String loginPass) {
		String str = "";
		boolean isLogin = login.loginTest(loginId, loginPass);
		PreparedStatement stmt = null;
		ResultSet res = null;

		if (isLogin) {
			str += "ログインテスト完了 （ " + tableName + " ）\n";
			String sql = "UPDATE " + tableName + " SET " + DbColumns.LOGIN_ID.getValue() + "=?, "
					+ DbColumns.LOGIN_PASS.getValue() + "=?;";
			try {
				switchDB();
				stmt = con.prepareStatement(sql);
				stmt.setString(1, loginId);
				stmt.setString(2, loginPass);

				int no = stmt.executeUpdate();
				if (no == 1) {
					str += "情報の更新に成功しました。　（ " + DbColumns.LOGIN_ID.getValue() + ": " + loginId + " ）\n";
				} else {
					str += "情報の更新に失敗しました。";
				}
			} catch (SQLException e) {

			} finally {
				closeDB(res, stmt);
			}
		} else {
			str = "ログインテストに失敗しました。" + tableName + "\n";
		}
		return str;
	}

	public static void closeDB(ResultSet res, PreparedStatement stmt) {
		try {
			if (res != null) {
				res.close();
			}
			if (stmt != null) {
				stmt.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

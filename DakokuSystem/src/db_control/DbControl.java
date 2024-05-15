package db_control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import login.Login;

public abstract class DbControl {
	private String colmunsName;
	private String tableName;

	private static Connection sqlCon = null;
	private PreparedStatement sqlStmt = null;
	private ResultSet res = null;

	public DbControl() {
		this.colmunsName = "";
	}

	private static void connectDB() {
		try {
			sqlCon = DriverManager.getConnection(DbLoginInfo.GIKEN_URL.getValue(), DbLoginInfo.ROOT_USER.getValue(),
					DbLoginInfo.ROOT_PASSWORD.getValue());
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

	public String getInfo(DbColumns columns) {
		connectDB();
		colmunsName = columns.getValue();
		String sql = "SELECT " + colmunsName + " FROM " + tableName + ";";
		try {
			sqlStmt = sqlCon.prepareStatement(sql);
			res = sqlStmt.executeQuery();
			if (res.next()) {
				return !res.getString(colmunsName).isBlank() ? res.getString(colmunsName) : null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDB(res, sqlStmt, sqlCon);
		}
		return null;
	}

	public String updateInfo(Login login, String loginId, String loginPass) {
		String str = "";
		boolean isLogin = login.loginTest(loginId, loginPass);
		if (isLogin) {
			str += "ログインテスト完了 （ " + tableName + " ）\n";
			connectDB();
			String sql = "UPDATE " + tableName + " SET " + DbColumns.LOGIN_ID.getValue() + "=?, "
					+ DbColumns.LOGIN_PASS.getValue() + "=?;";
			try {
				sqlStmt = sqlCon.prepareStatement(sql);
				sqlStmt.setString(1, loginId);
				sqlStmt.setString(2, loginPass);

				int no = sqlStmt.executeUpdate();
				if (no == 1) {
					str += "情報の更新に成功しました。　（ " + DbColumns.LOGIN_ID.getValue() + ": " + loginId + " ）\n";
				} else {
					str += "情報の更新に失敗しました。";
				}
			} catch (SQLException e) {
				return e.getMessage();
			} finally {
				closeDB(res, sqlStmt, sqlCon);
			}
		} else {
			str = "ログインテストに失敗しました。" + tableName + "\n";
		}
		return str;
	}

	protected void setTableName(String tableName) {
		this.tableName = tableName;
	}

}

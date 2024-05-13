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

	private static final String sqlUserID = "testuser";
	private static final String sqlPassword = "giken2";
	private static final String dbUrl = "jdbc:mysql://localhost:3306/db_giken2";

	public DbControl() {
		this.colmunsName = "";
	}

	private static void connectDB() {
		try {
			sqlCon = DriverManager.getConnection(dbUrl, sqlUserID, sqlPassword);
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

	public String getInfo(DbColumns columns) {
		connectDB();
		colmunsName = columns.getValue();
		String sql = "SELECT " + colmunsName + " FROM " + tableName + ";";
		try {
			sqlStmt = sqlCon.prepareStatement(sql);
			res = sqlStmt.executeQuery();
			if (res.next()) {
				return res.getString(colmunsName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDB(res, sqlStmt, sqlCon);
		}
		return null;
	}

	public String setInfo(Login login, String loginId, String loginPass) {
		String str = "";
		if (login.loginTest(loginId, loginPass)) {
			connectDB();
			String sql = "INSERT INTO " + tableName + " values ('?', '?');";
			try {
				sqlStmt = sqlCon.prepareStatement(sql);
				sqlStmt.setString(1, DbColumns.LOGIN_ID.getValue());
				sqlStmt.setString(2, DbColumns.LOGIN_PASS.getValue());
				int no = sqlStmt.executeUpdate();
				if (no == 1) {
					str = "情報の登録に成功しました。" + tableName;
				} else {
					str = "情報の登録に失敗しました。" + tableName;
				}
			} catch (SQLException e) {
				return e.getMessage();
			} finally {
				closeDB(res, sqlStmt, sqlCon);
			}
		} else {
			str = "ログインテストに失敗しました。" + tableName;
		}
		return str;

	}

	public String updateInfo(Login login, String loginId, String loginPass) {
		String str = "";
		if (login.loginTest(loginId, loginPass)) {
			connectDB();
			String sql = "UPDATE " + tableName + " SET " + DbColumns.LOGIN_ID.getValue() + "='" + loginId + "', "
					+ DbColumns.LOGIN_PASS.getValue() + "='" + loginPass + "';";
			try {
				sqlStmt = sqlCon.prepareStatement(sql);

				int no = sqlStmt.executeUpdate();
				if (no == 1) {
					str = "情報の更新に成功しました。" + tableName;
				} else {
					str = "情報の更新に失敗しました。" + tableName;
				}
			} catch (SQLException e) {
				return e.getMessage();
			} finally {
				closeDB(res, sqlStmt, sqlCon);
			}
		} else {
			str = "ログインテストに失敗しました。" + tableName;
			;
		}
		return str;
	}

	protected void setTableName(String tableName) {
		this.tableName = tableName;
	}

}

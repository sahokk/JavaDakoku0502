package db_control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utility.DbColumns;

public abstract class DbControl {
	private String colmunsName;
	private String tableName;
	private static Connection sqlCon = null;
	private PreparedStatement sqlStmt = null;
	private ResultSet res = null;

	private static final String sqlUserID = "testuser";
	private static final String sqlPassword = "giken2";
	private static final String dbUrl = "jdbc:mysql://localhost:3306/db_giken2";

	private static boolean flagLogin;

	public DbControl() {
		flagLogin = false;
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

	public void setInfo(String loginId, String loginPass) {
		// 先にログインテストする
		connectDB();
		String sql = "INSERT INTO " + tableName + " values (?, ?);";
		try {
			sqlStmt = sqlCon.prepareStatement(sql);
			sqlStmt.setString(1, DbColumns.LOGIN_ID.getValue());
			sqlStmt.setString(2, DbColumns.LOGIN_PASS.getValue());
			int no = sqlStmt.executeUpdate();
			if (no == 1) {
//				登録完了
			} else {
//				登録失敗
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDB(res, sqlStmt, sqlCon);
		}
	}

	public void updateInfo(String loginId, String loginPass) {
		// 先にログインテストする
		connectDB();
		String sql = "UPDATE " + tableName + " SET ? = ?, ? = ?;";
		try {
			sqlStmt = sqlCon.prepareStatement(sql);
			sqlStmt.setString(1, DbColumns.LOGIN_ID.getValue());
			sqlStmt.setString(2, loginId);
			sqlStmt.setString(3, DbColumns.LOGIN_PASS.getValue());
			sqlStmt.setString(4, loginPass);

			int no = sqlStmt.executeUpdate();
			if (no == 1) {
//						登録完了
			} else {
//						登録失敗
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDB(res, sqlStmt, sqlCon);
		}
	}

	protected void setTableName(String tableName) {
		this.tableName = tableName;
	}

}

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

	protected static Connection sqlCon = null;
	private PreparedStatement sqlStmt = null;
	private ResultSet res = null;

	private static final String URL = "jdbc:mysql://localhost:3306/mysql";
	private static String user;
	private static String pass;

	private static boolean isConnectable;

	public DbControl() {
		this.colmunsName = "";
		isConnectable = false;

	}

	protected static boolean isConnectable() {
		return isConnectable;
	}

	private static void setConnectable(boolean isConnectable) {
		DbControl.isConnectable = isConnectable;
	}

	protected static void setUser(String user) {
		DbControl.user = user;
	}

	protected static void setPass(String pass) {
		DbControl.pass = pass;
	}

	protected static void connectDB() {
		try {
			DbControl.sqlCon = DriverManager.getConnection(URL, user, pass);
			System.out.println("success db con");
			setConnectable(true);
		} catch (SQLException e) {
			user = null;
			pass = null;
			e.printStackTrace();
			closeDB(null, null, sqlCon);
		}
	}

	protected static void closeDB(ResultSet res, PreparedStatement stmt, Connection con) {
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

	protected void useGikenDB() throws SQLException {
		String sql = "USE " + DbLoginInfo.GIKEN_DB_NAME.getValue() + ";";
		try {
			sqlStmt = getSqlCon().prepareStatement(sql);
			sqlStmt.executeUpdate();
		} catch (SQLException e) {
			throw e;
		}
	}

	public String getInfo(DbColumns columns) {
		connectDB();
		colmunsName = columns.getValue();
		String sql = "SELECT " + colmunsName + " FROM " + tableName + ";";
		try {
			useGikenDB();
			sqlStmt = getSqlCon().prepareStatement(sql);
			res = sqlStmt.executeQuery();
			if (res.next()) {
				return !res.getString(colmunsName).isBlank() ? res.getString(colmunsName) : null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDB(res, sqlStmt, getSqlCon());
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
				useGikenDB();
				sqlStmt = getSqlCon().prepareStatement(sql);
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
				closeDB(res, sqlStmt, getSqlCon());
			}
		} else {
			str = "ログインテストに失敗しました。" + tableName + "\n";
		}
		return str;
	}

	protected void setTableName(String tableName) {
		this.tableName = tableName;
	}

	protected static Connection getSqlCon() {
		return sqlCon;
	}

}

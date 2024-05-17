package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db_control.DbLoginInfo;

public class ConnectionManager {

	private static final String URL = DbLoginInfo.ROOT_URL.getValue();
	private static String user;
	private static String pass;

	private static boolean isConnectable;

	public ConnectionManager() {
		isConnectable = false;
	}

	public static Connection getConnection() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(URL, user, pass);
			System.out.println("success db con");
			setConnectable(true);
		} catch (SQLException e) {
			user = null;
			pass = null;
			closeDB(null, null, con);
		}
		return con;
	}

	public static Connection getConnection(String user, String pass) {
		Connection con = null;
		try {
			con = DriverManager.getConnection(URL, user, pass);
			System.out.println("success db con");
			setConnectable(true);
			ConnectionManager.user = user;
			ConnectionManager.pass = pass;
		} catch (SQLException e) {
			closeDB(null, null, con);
		}
		return con;
	}

	public static void closeDB(ResultSet res, PreparedStatement stmt, Connection con) {
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

	private static void setConnectable(boolean isConnectable) {
		ConnectionManager.isConnectable = isConnectable;
	}

	protected static boolean isConnectable() {
		return isConnectable;
	}
}

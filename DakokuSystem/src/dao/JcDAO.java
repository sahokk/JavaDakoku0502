package dao;

import java.sql.Connection;

import db_control.DbTableNames;

public class JcDAO extends DAO {

	public JcDAO(Connection con) {
		super(con);
		setTableName(DbTableNames.JC_TABLE_NAME.getValue());
	}

}

package dao;

import java.sql.Connection;

import db_control.DbTableNames;

public class RDAO extends DAO {

	public RDAO(Connection con) {
		super(con);
		setTableName(DbTableNames.R_TABLE_NAME.getValue());
	}

}

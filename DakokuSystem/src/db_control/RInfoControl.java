package db_control;

import utility.DbTableNames;

public class RInfoControl extends DbControl {

	public RInfoControl() {
		super();
		super.setTableName(DbTableNames.R_TABLE_NAME.getValue());
	}

}

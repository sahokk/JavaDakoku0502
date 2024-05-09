package db_control;

import utility.DbTableNames;

public class RInfoControl extends DbControl {

	private static DbControl dbControl = new RInfoControl();

	private RInfoControl() {
		super();
		super.setTableName(DbTableNames.R_TABLE_NAME.getValue());
	}

	public static DbControl getInstance() {
		return dbControl;
	}

}

package db_control;

import utility.DbTableNames;

public class JcInfoControl extends DbControl {

	public JcInfoControl() {
		super();
		super.setTableName(DbTableNames.JC_TABLE_NAME.getValue());
	}

}

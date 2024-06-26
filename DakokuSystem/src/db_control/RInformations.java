package db_control;

import org.openqa.selenium.By;

public enum RInformations {
	LOGIN_ID_FIELD(By.id("txbUserId")), LOGIN_PASS_FIELD(By.id("txbPassword")),
	LOGIN_BUTTON_FIELD(By.xpath("/html/body/div/div/div/form/div[5]/div[1]/button")),
	LOGOUT_ARROW_FIELD(By.id("headerSubMenuArrow")), LOGOUT_BUTTON_FIELD(By.id("headerSubMenuList")),
	DAKOKU_LIST_BUTTON(By.id("menuStamp")), DAKOKU_BUTTON_IN(By.id("btnStartTime")),
	DAKOKU_BUTTON_OUT(By.id("btnEndTime"));

	private By by;

	private RInformations(By by) {
		this.by = by;
	}

	public By getValue() {
		return this.by;
	}
}

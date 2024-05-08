package utility;

import org.openqa.selenium.By;

public enum JcInformations {
	LOGIN_ID_FIELD(By.id("LoginId")), LOGIN_PASS_FIELD(By.id("zc_Password")),
	LOGIN_BUTTON_FIELD(By.xpath("/html/body/div[1]/div/form/div[1]/div[2]/div[3]/button")),
	DAKOKU_BUTTON_IN(By.xpath("\"/html/body/div[2]/div/div/div[1]/div[3]/div/div[2]/ul/li[1]/button\"")),
	DAKOKU_BUTTON_OUT(By.xpath("/html/body/div[2]/div/div/div[1]/div[3]/div/div[2]/ul/li[2]/button"));

	private By by;

	private JcInformations(By by) {
		this.by = by;
	}

	public By getValue() {
		return this.by;
	}
}
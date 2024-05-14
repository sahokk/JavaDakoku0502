package dakoku;

public enum Dakokustate {
	IN("出社"), OUT("退社");

	private String str;

	private Dakokustate(String str) {
		this.str = str;
	}

	public String getValue() {
		return this.str;
	}
}

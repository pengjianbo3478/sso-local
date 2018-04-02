package cn.obanks.common;
public enum DeletedFlag {
	NORMAL("N"), DELETED("D");
	private final String value;

	DeletedFlag(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}

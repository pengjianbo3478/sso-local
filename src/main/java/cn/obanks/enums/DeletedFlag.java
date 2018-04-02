package cn.obanks.enums;
public enum DeletedFlag {
	NORMAL("N"), DELETED("D");
	private String value;

	private DeletedFlag(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}

package cn.obanks.enums;

public enum AcctType {
	
	CPM("CPM");;
	private String value;

	private AcctType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}

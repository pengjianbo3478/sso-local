package cn.obanks.enums;

/**
 * <ul>
 * <li>INACTIVE 未激活</li>
 * <li>ACTIVE 已激活</li>
 * <li>PEND_AUDIT 待审核</li>
 * <li>AUDIT 审核通过</li>
 * <li>REJECT 审核不通过</li>
 */
public enum UserStatus {

	ACTIVE("AC"), INACTIVE("IN"), PEND_AUDIT("PA"), AUDIT("AU"), REJECT("RJ");

	private String value;

	private UserStatus(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}

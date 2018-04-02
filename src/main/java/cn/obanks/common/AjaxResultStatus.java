package cn.obanks.common;
/**
* @Title: AjaxResultStatus.java
* @Description: AjaxResult状态
* @author xiongsiwei
* @date 2014年11月12日下午4:13:34  
* @Company: ftiao.cn
* @Copyright Copyright (c) 2014
 */
public enum AjaxResultStatus {
	// AjaxResult状态：Y、正常；N、失败
	Y("Y"), N("N");
	private final String value;

	AjaxResultStatus(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}

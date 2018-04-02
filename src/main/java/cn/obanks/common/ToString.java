package cn.obanks.common;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ToString {
	public static String reflectionToString(Object object) {
		return ToStringBuilder.reflectionToString(object, ToStringStyle.MULTI_LINE_STYLE);
	}
}

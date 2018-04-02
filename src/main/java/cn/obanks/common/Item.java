package cn.obanks.common;
import java.io.Serializable;
import org.springframework.stereotype.Component;

@Component
public class Item implements Serializable {
	private static final long serialVersionUID = 1L;
	private String key;
	private String value;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Item(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}

	public Item() {
		super();
	}
}

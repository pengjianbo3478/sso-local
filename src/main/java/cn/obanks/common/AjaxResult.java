package cn.obanks.common;
import java.io.Serializable;

public class AjaxResult implements Serializable {
	private static final long serialVersionUID = 1L;
	private String result;
	private String msg;
	private Object object;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getMsg() {
		return msg;
	}

	public AjaxResult(String result, String msg) {
		super();
		this.result = result;
		this.msg = msg;
	}

	public AjaxResult() {
		super();
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}
}

package cn.obanks.common;
import java.io.Serializable;

public class Paginate implements Serializable {
	private static final long serialVersionUID = 1L;
	private long total;// 数据条数
	private int currentPageNum = 1;// 当前页数
	private Object data;// 要显示的数据
	private int recordsPerPage = 20;// 每页显示多少条数据
	private int showTags = 5;// 显示多少个分页标签
	private Object condition;// 要传到SQL的查询条件
	@SuppressWarnings("unused")
	private long pageTotal;

	public long getPageTotal() {
		return (total + recordsPerPage - 1) / recordsPerPage;
	}

	public int getStartNum() {
		return (this.currentPageNum - 1) * this.recordsPerPage;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public int getCurrentPageNum() {
		return currentPageNum;
	}

	public void setCurrentPageNum(int currentPageNum) {
		this.currentPageNum = currentPageNum;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public int getRecordsPerPage() {
		return recordsPerPage;
	}

	public int getShowTags() {
		return showTags;
	}

	public void setShowTags(int showTags) {
		this.showTags = showTags;
	}

	public Object getCondition() {
		return condition;
	}

	public void setCondition(Object condition) {
		this.condition = condition;
	}

	public void setRecordsPerPage(int recordsPerPage) {
		this.recordsPerPage = recordsPerPage;
	}
}

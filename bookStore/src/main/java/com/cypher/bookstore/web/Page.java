package com.cypher.bookstore.web;

import java.util.List;

/**
 * @author Cypher-Z
 * @date 2018/8/10 - 20:54
 */
public class Page<T> {

	//	当前第几页
	private int pageNo;

	//  当前页的list
	private List<T> pageList;

	//	每页显示记录数
	private int pageSize = 3;

	//	总记录数
	private long totalItemCount;

	public Page(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageNo() {
		if (pageNo <= 0) {
			pageNo = 1;
		}
		if (pageNo > getPageCount()) {
			pageNo = getPageCount();
		}
		return pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<T> getPageList() {
		return pageList;
	}

	public void  setPageList(List<T> pageList){
		this.pageList = pageList;
	}

	//	得到记录总页数
	public int getPageCount() {
		int pageCount = (int) (totalItemCount / pageSize);
		if (totalItemCount % pageSize != 0) {
			pageCount++;
		}
		return pageCount;
	}

	public void setTotalItemCount(long totalItemCount) {
		this.totalItemCount = totalItemCount;
	}

	public boolean isHasNext() {
		if (pageNo < getPageCount()) {
			return true;
		}
		return false;
	}

	public boolean isHasPrev() {
		if (pageNo > 1) {
			return true;
		}
		return false;
	}

	public int getNextNo() {
		if (isHasNext()) {
			return getPageNo() + 1;
		}
		return getPageNo();
	}

	public int getPrevNo() {
		if (isHasPrev()) {
			return getPageNo() - 1;
		}
		return getPageNo();
	}

	@Override
	public String toString() {
		return "Page{" +
				"pageNo=" + pageNo +
				", pageList=" + pageList +
				", pageSize=" + pageSize +
				", totalItemCount=" + totalItemCount +
				'}';
	}
}

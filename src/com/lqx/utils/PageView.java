package com.lqx.utils;

import java.util.List;

public final class PageView<T> {
	/*开始索引*/
	private long startIndex;
	/*结束索引*/
	private long endIndex;
	/* 当前页码 */
	private int currentPage = 1;
	/* 总页数 */
	private int totalPages;
	/* 每页显示记录数 */
	private int maxResult = 50;
	/* 总记录数 */
	private long totalRecords;
	/* 最大显示页数 */
	private int maxPage = 10;
	/* 查询记录 */
	private List<T> records;
	
	public PageView(Integer currentPage) {
		this.currentPage = currentPage ==null ? 1 : currentPage;
	}	
	
	public long getStartIndex() {

		return this.startIndex;
	}

	public long getEndIndex() {

		return this.endIndex;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public int getTotalPages() {
		return this.totalPages;
	}

	public int getMaxResult() {
		return maxResult;
	}

	public long getTotalRecords() {
		return totalRecords;
	}
	

	public void setTotalRecords(long totalRecords) {
		this.totalRecords = totalRecords;
		setTotalPages(totalRecords, this.maxResult);
		setStartIndex(this.currentPage, this.totalPages, this.maxPage);
		setEndIndex(this.currentPage, this.totalPages, this.maxPage);
	}

	public int getMaxPage() {
		return maxPage;
	}
	
	public List<T> getRecords() {
		return records;
	}

	public void setRecords(List<T> records) {
		this.records = records;
	}

	public int getFirstResult() {
		return (currentPage - 1) * maxResult;
	}
	
	private void setTotalPages(long totalRecords, int maxResult) {
		this.totalPages = (int)(totalRecords % maxResult == 0 ? totalRecords
								/ maxResult	: totalRecords / maxResult + 1);
	}

	private void setStartIndex(int currentPage, int totalPages, int maxPage) {
		long i = maxPage % 2 == 0 ? maxPage / 2 - 1	: maxPage / 2;
		this.startIndex = (currentPage - i > 0) ? currentPage - i : 1;
		if (this.startIndex == 1)
				this.endIndex = totalPages < maxPage ? totalPages : maxPage;
	}

	private void setEndIndex(int currentPage, int totalPages, int maxPage) {
		this.endIndex = (currentPage + maxPage / 2 > totalPages) ? totalPages
				: currentPage + maxPage / 2;
		if (this.endIndex == totalPages)
			this.startIndex = totalPages < maxPage ? 1 : totalPages - maxPage + 1;
	}


}

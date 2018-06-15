package com.zhuoyue.bean;

import java.util.List;
 
/***
 * 
 * Copyright (C)            PBIM
 * 类        名: PagedResult
 * 功能描述:  用于返回分页的数据
 * @author   lizw
 * @version  @param <T> 
 * @Date	 2017	2017年3月8日		上午11:31:44
 */
public class PagedResult<T> extends BaseEntity {
	
	/*serialVersionUID*/
	private static final long serialVersionUID = 1L;

	private List<T> data;//数据
	
	private long pageNo;//当前页
	
	private long pageSize;//条数
	
	private long total;//总条数
	
	private long pages;//总页面数目


	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public long getPageNo() {
		return pageNo;
	}

	public void setPageNo(long pageNo) {
		this.pageNo = pageNo;
	}

	public long getPageSize() {
		return pageSize;
	}

	public void setPageSize(long pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public long getPages() {
		return pages;
	}

	public void setPages(long pages) {
		this.pages = pages;
	}
	
}

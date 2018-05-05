package com.wh.weiguang.model;

public class PageEntity {

	private Integer pageSize;
	
	private Integer pageStart;

	public PageEntity(Integer pageSize, Integer page) {
		this.pageSize = pageSize;
		this.pageStart = page*pageSize;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageStart() {
		return pageStart;
	}

	public void setPageStart(Integer pageStart) {
		this.pageStart = pageStart;
	}
	
	
}

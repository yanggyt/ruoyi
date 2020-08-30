package com.ruoyi.dfm.pojo;


import com.ruoyi.dfm.util.PropertiesUtils;

import java.io.Serializable;

public class Page implements Serializable {
	private static final long serialVersionUID = -4222587152137966045L;
	private int currentPage;
	private int pageSize;
	private int totalCount;
	private int totalPage;
	
	private String orderCase;
	private static int PAGE_SIZE = 10;
	// 获取系统文件根路径
//	static {
//		PAGE_SIZE = Integer.parseInt(PropertiesUtils.getProperties().getProperty("PAGE_SIZE"));
//	}
	public Page() {
		this.currentPage = 1;
		this.pageSize = PAGE_SIZE;
		this.totalCount = 0;
		this.totalPage = 1;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public String getOrderCase() {
		return orderCase;
	}
	public void setOrderCase(String orderCase) {
		this.orderCase = orderCase;
	}
	
}

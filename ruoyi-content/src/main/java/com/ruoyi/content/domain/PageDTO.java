package com.ruoyi.content.domain;

import java.util.List;

public class PageDTO extends BaseDTO{
	
	private static final int PageQuerySize = 5;	
	private int page = 1;             //当前页号
	private int rows = PageQuerySize;   //每页显示的行数	
	private int startRow = 0;              //当前页在数据库中的起始行
	private Object parameters;       		//查询参数
	private int total;               		//总页数
	@SuppressWarnings("rawtypes")
	private List dataRows;               		//查询结果信息   
	
	private String pageCode = "1";    		//默认为成功  1成功 2失败	
	private String pageMsg = "";   		//结果
	
	private int records;//记录数
	
	private String userData;//

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public Object getParameters() {
		return parameters;
	}

	public void setParameters(Object parameters) {
		this.parameters = parameters;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	@SuppressWarnings("rawtypes")
	public List getDataRows() {
		return dataRows;
	}

	@SuppressWarnings("rawtypes")
	public void setDataRows(List dataRows) {
		this.dataRows = dataRows;
	}

	public String getPageCode() {
		return pageCode;
	}

	public void setPageCode(String pageCode) {
		this.pageCode = pageCode;
	}

	public String getPageMsg() {
		return pageMsg;
	}

	public void setPageMsg(String pageMsg) {
		this.pageMsg = pageMsg;
	}

	public int getRecords() {
		return records;
	}

	public void setRecords(int records) {
		this.records = records;
	}

	public static int getPagequerysize() {
		return PageQuerySize;
	}

	public String getUserData() {
		return userData;
	}

	public void setUserData(String userData) {
		this.userData = userData;
	}
}

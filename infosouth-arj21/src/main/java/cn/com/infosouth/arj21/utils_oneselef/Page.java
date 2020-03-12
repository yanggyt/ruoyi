package cn.com.infosouth.arj21.utils_oneselef;

import java.util.List;

public class Page<E> {

 

	    /**
	     * 页码，从1开始
	     */
	    private int pageNum;
	    /**
	     * 页面大小
	     */
	    private int pageSize;
	    /**
	     * 总数
	     */
	    private long total;
	
	    private List<E> data;

		public int getPageNum() {
			return pageNum;
		}

		public void setPageNum(int pageNum) {
			this.pageNum = pageNum;
		}

		public int getPageSize() {
			return pageSize;
		}

		public void setPageSize(int pageSize) {
			this.pageSize = pageSize;
		}

		public long getTotal() {
			return total;
		}

		public void setTotal(long total) {
			this.total = total;
		}

		public List<?> getData() {
			return data;
		}

		public void setData(List<E> data) {
			this.data = data;
		}
	    
	    
	    
}

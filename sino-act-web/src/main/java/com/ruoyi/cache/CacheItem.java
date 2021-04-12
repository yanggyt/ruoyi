package com.ruoyi.cache;

public class CacheItem {
	 private String key;
	    private Object value;
	    private long timeOut;

	    public CacheItem() {

	    }

	    public CacheItem(String key, Object value) {
	        this.key = key;
	        this.value = value;
	        this.timeOut = 0;
	    }

	    public CacheItem(String key, Object value, long timeOut) {
	        this.key = key;
	        this.value = value;
	        this.timeOut = timeOut;
	    }

	    public String getKey() {
	        return key;
	    }

	    public void setKey(String key) {
	        this.key = key;
	    }

	    public Object getValue() {
	        return value;
	    }

	    public void setValue(Object value) {
	        this.value = value;
	    }

	    public long getTimeOut() {
	        return timeOut;
	    }

	    public void setTimeOut(long timeOut) {
	        this.timeOut = timeOut;
	    }

}

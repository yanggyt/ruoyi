package com.ruoyi.dto;

import com.sinosoft.activity.domain.DrawConfig;
import com.sinosoft.activity.domain.DrawPrizeInfo;

import java.io.Serializable;

public class GtPrizeConfigTemp implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3431401981054724955L;
	private long baseNumer;
	private long weightLength;
	private long startNumer;
	private long endNumber;
	private DrawConfig config;
	private DrawPrizeInfo prizeInfo;
	public long getBaseNumer() {
		return baseNumer;
	}
	public void setBaseNumer(long baseNumer) {
		this.baseNumer = baseNumer;
	}
	public long getWeightLength() {
		return weightLength;
	}
	public void setWeightLength(long weightLength) {
		this.weightLength = weightLength;
	}
	public long getStartNumer() {
		return startNumer;
	}
	public void setStartNumer(long startNumer) {
		this.startNumer = startNumer;
	}
	public long getEndNumber() {
		return endNumber;
	}
	public void setEndNumber(long endNumber) {
		this.endNumber = endNumber;
	}
	public DrawConfig getConfig() {
		return config;
	}
	public void setConfig(DrawConfig config) {
		this.config = config;
	}
	public DrawPrizeInfo getPrizeInfo() {
		return prizeInfo;
	}
	public void setPrizeInfo(DrawPrizeInfo prizeInfo) {
	this.prizeInfo = prizeInfo;
}
}

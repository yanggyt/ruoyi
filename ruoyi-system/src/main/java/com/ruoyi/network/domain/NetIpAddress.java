package com.ruoyi.network.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 网络IP表 net_ip_address
 *
 * @author TP
 * @date 2019-06-15
 */
public class NetIpAddress extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/**
	 * IP地址编号
	 */
	private Integer ipAddressId;
	/**
	 * IP地址类型
	 */
	private String ipAddressType;
	/**
	 * IP地址段
	 */
	private String ipAddressSection;
	/**
	 * 所属机房编号
	 */
	private String machingRoomId;
	/**
	 * IP使用规则
	 */
	private String ipUseRule;
	/**
	 * IP地址分配规则
	 */
	private String ipAllocRule;

	public Integer getIpAddressId() {
		return ipAddressId;
	}

	public void setIpAddressId(Integer ipAddressId) {
		this.ipAddressId = ipAddressId;
	}

	public String getIpAddressType() {
		return ipAddressType;
	}

	public void setIpAddressType(String ipAddressType) {
		this.ipAddressType = ipAddressType;
	}

	public String getIpAddressSection() {
		return ipAddressSection;
	}

	public void setIpAddressSection(String ipAddressSection) {
		this.ipAddressSection = ipAddressSection;
	}

	public String getMachingRoomId() {
		return machingRoomId;
	}

	public void setMachingRoomId(String machingRoomId) {
		this.machingRoomId = machingRoomId;
	}

	public String getIpUseRule() {
		return ipUseRule;
	}

	public void setIpUseRule(String ipUseRule) {
		this.ipUseRule = ipUseRule;
	}

	public String getIpAllocRule() {
		return ipAllocRule;
	}

	public void setIpAllocRule(String ipAllocRule) {
		this.ipAllocRule = ipAllocRule;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("ipAddressId", getIpAddressId())
				.append("ipAddressType", getIpAddressType()).append("ipAddressSection", getIpAddressSection())
				.append("machingRoomId", getMachingRoomId()).append("ipUseRule", getIpUseRule())
				.append("ipAllocRule", getIpAllocRule()).toString();
	}
}

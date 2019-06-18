package com.ruoyi.network.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.system.domain.SysDictData;
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
    private Integer dictCode;
    /**
     * IP地址类型   实体关联
     */
    private SysDictData ipAddressType;
    /**
     * IP地址段
     */
    private String ipAddressSection;
    /**
     * 所属机房编号
     */
    private Integer machineRoomId;
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

    public Integer getDictCode() {
        return dictCode;
    }

    public void setDictCode(Integer dictCode) {
        this.dictCode = dictCode;
    }

    public SysDictData getIpAddressType() {
        return ipAddressType;
    }

    public void setIpAddressType(SysDictData ipAddressType) {
        this.ipAddressType = ipAddressType;
    }

    public String getIpAddressSection() {
        return ipAddressSection;
    }

    public void setIpAddressSection(String ipAddressSection) {
        this.ipAddressSection = ipAddressSection;
    }

    public Integer getMachineRoomId() {
        return machineRoomId;
    }

    public void setMachineRoomId(Integer machineRoomId) {
        this.machineRoomId = machineRoomId;
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
                .append("machineRoomId", getMachineRoomId()).append("ipUseRule", getIpUseRule())
                .append("ipAllocRule", getIpAllocRule()).toString();
    }
}

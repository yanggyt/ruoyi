package com.ruoyi.template.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 服务器模板表 tmpl_server
 *
 * @author TP
 * @date 2019-06-12
 */
public class TmplServer extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 服务器模板编号
     */
    private Integer serverId;
    /**
     * 服务器品牌
     */
    private String serverBrand;
    /**
     * 服务器型号
     */
    private String serverType;
    /**
     * CPU主频
     */
    private String cpuFreq;
    /**
     * CPU数量
     */
    private Integer cpuNum;
    /**
     * IPMI端口
     */
    private Integer ipmiPort;
    /**
     * 服务器电源数量
     */
    private Integer powerNum;
    /**
     * 服务器raid卡
     */
    private String raidCard;

    public void setServerId(Integer serverId) {
        this.serverId = serverId;
    }

    public Integer getServerId() {
        return serverId;
    }

    public void setServerBrand(String serverBrand) {
        this.serverBrand = serverBrand;
    }

    public String getServerBrand() {
        return serverBrand;
    }

    public void setServerType(String serverType) {
        this.serverType = serverType;
    }

    public String getServerType() {
        return serverType;
    }

    public void setCpuFreq(String cpuFreq) {
        this.cpuFreq = cpuFreq;
    }

    public String getCpuFreq() {
        return cpuFreq;
    }

    public void setCpuNum(Integer cpuNum) {
        this.cpuNum = cpuNum;
    }

    public Integer getCpuNum() {
        return cpuNum;
    }

    public void setIpmiPort(Integer ipmiPort) {
        this.ipmiPort = ipmiPort;
    }

    public Integer getIpmiPort() {
        return ipmiPort;
    }

    public void setPowerNum(Integer powerNum) {
        this.powerNum = powerNum;
    }

    public Integer getPowerNum() {
        return powerNum;
    }

    public void setRaidCard(String raidCard) {
        this.raidCard = raidCard;
    }

    public String getRaidCard() {
        return raidCard;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("serverId", getServerId())
                .append("serverBrand", getServerBrand())
                .append("serverType", getServerType())
                .append("cpuFreq", getCpuFreq())
                .append("cpuNum", getCpuNum())
                .append("ipmiPort", getIpmiPort())
                .append("powerNum", getPowerNum())
                .append("raidCard", getRaidCard())
                .toString();
    }
}

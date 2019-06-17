package com.ruoyi.template.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

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
    /**
     * 服务器网卡类型
     */
    private List<TmplServerNetcard> serverNetcards;
    /**
     * 服务器硬盘类型
     */
    private List<TmplServerDisk> serverDisks;
    /**
     * 服务器内存类型
     */
    private List<TmplServerMemory> serverMemorys;

    public Integer getServerId() {
        return serverId;
    }

    public void setServerId(Integer serverId) {
        this.serverId = serverId;
    }

    public String getServerBrand() {
        return serverBrand;
    }

    public void setServerBrand(String serverBrand) {
        this.serverBrand = serverBrand;
    }

    public String getServerType() {
        return serverType;
    }

    public void setServerType(String serverType) {
        this.serverType = serverType;
    }

    public String getCpuFreq() {
        return cpuFreq;
    }

    public void setCpuFreq(String cpuFreq) {
        this.cpuFreq = cpuFreq;
    }

    public Integer getCpuNum() {
        return cpuNum;
    }

    public void setCpuNum(Integer cpuNum) {
        this.cpuNum = cpuNum;
    }

    public Integer getIpmiPort() {
        return ipmiPort;
    }

    public void setIpmiPort(Integer ipmiPort) {
        this.ipmiPort = ipmiPort;
    }

    public Integer getPowerNum() {
        return powerNum;
    }

    public void setPowerNum(Integer powerNum) {
        this.powerNum = powerNum;
    }

    public String getRaidCard() {
        return raidCard;
    }

    public void setRaidCard(String raidCard) {
        this.raidCard = raidCard;
    }

    public List<TmplServerNetcard> getServerNetcards() {
        return serverNetcards;
    }

    public void setServerNetcards(List<TmplServerNetcard> serverNetcards) {
        this.serverNetcards = serverNetcards;
    }

    public List<TmplServerDisk> getServerDisks() {
        return serverDisks;
    }

    public void setServerDisks(List<TmplServerDisk> serverDisks) {
        this.serverDisks = serverDisks;
    }

    public List<TmplServerMemory> getServerMemorys() {
        return serverMemorys;
    }

    public void setServerMemorys(List<TmplServerMemory> serverMemorys) {
        this.serverMemorys = serverMemorys;
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

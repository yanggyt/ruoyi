package com.ruoyi.template.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.system.domain.SysDictData;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.beans.Transient;

/**
 * 服务器内存类型表 tmpl_server_memory
 *
 * @author TP
 * @date 2019-06-16
 */
public class TmplServerMemory extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 服务器内存编号
     */
    private Integer serverMemoryId;
    /**
     * 服务器编号
     */
    private Integer serverId;
    /**
     * 服务器内存类型
     */
//    private Long serverMemoryType;
    private SysDictData serverMemoryType;
    /**
     * 服务器内存数量
     */
    private Integer serverMemoryNum;

    /**
     * 服务器内存总数 @Transient
     */
    private Integer serverMemoryTotal;

    public Integer getServerMemoryId() {
        return serverMemoryId;
    }

    public void setServerMemoryId(Integer serverMemoryId) {
        this.serverMemoryId = serverMemoryId;
    }

    public Integer getServerId() {
        return serverId;
    }

    public void setServerId(Integer serverId) {
        this.serverId = serverId;
    }

//    public Long getServerMemoryType() {
//        return serverMemoryType;
//    }

//    public void setServerMemoryType(Long serverMemoryType) {
//        this.serverMemoryType = serverMemoryType;
//    }

    public SysDictData getServerMemoryType() {
        return serverMemoryType;
    }

    public void setServerMemoryType(SysDictData serverMemoryType) {
        this.serverMemoryType = serverMemoryType;
    }

    public Integer getServerMemoryNum() {
        return serverMemoryNum;
    }

    public void setServerMemoryNum(Integer serverMemoryNum) {
        this.serverMemoryNum = serverMemoryNum;
    }

    @Transient
    public Integer getServerMemoryTotal() {
        return serverMemoryTotal;
    }

    public void setServerMemoryTotal(Integer serverMemoryTotal) {
        this.serverMemoryTotal = serverMemoryTotal;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("serverMemoryId", getServerMemoryId())
                .append("serverId", getServerId())
                .append("serverMemoryType", getServerMemoryType())
                .append("serverMemoryNum", getServerMemoryNum())
                .toString();
    }
}

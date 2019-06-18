package com.ruoyi.template.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.system.domain.SysDictData;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

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
    private SysDictData serverMemoryType;
    /**
     * 服务器内存数量
     */
    private Integer serverMemoryNum;

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

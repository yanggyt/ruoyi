package com.ruoyi.template.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 服务器硬盘类型表 tmpl_server_disk
 *
 * @author TP
 * @date 2019-06-14
 */
public class TmplServerDisk extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 服务器硬盘编号
     */
    private Integer serverDiskId;
    /**
     * 服务器编号
     */
    private Integer serverId;
    /**
     * 服务器硬盘类型
     */
    private Long serverDiskType;
    /**
     * 服务器网卡数量
     */
    private Integer serverDiskNum;

    public Integer getServerDiskId() {
        return serverDiskId;
    }

    public void setServerDiskId(Integer serverDiskId) {
        this.serverDiskId = serverDiskId;
    }

    public Integer getServerId() {
        return serverId;
    }

    public void setServerId(Integer serverId) {
        this.serverId = serverId;
    }

    public Long getServerDiskType() {
        return serverDiskType;
    }

    public void setServerDiskType(Long serverDiskType) {
        this.serverDiskType = serverDiskType;
    }

    public Integer getServerDiskNum() {
        return serverDiskNum;
    }

    public void setServerDiskNum(Integer serverDiskNum) {
        this.serverDiskNum = serverDiskNum;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("serverDiskId", getServerDiskId())
                .append("serverId", getServerId())
                .append("serverDiskType", getServerDiskType())
                .append("serverDiskNum", getServerDiskNum())
                .toString();
    }
}

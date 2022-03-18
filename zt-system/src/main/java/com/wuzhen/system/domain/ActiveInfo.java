package com.wuzhen.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wuzhen.common.annotation.Excel;
import com.wuzhen.common.annotation.Excel.ColumnType;
import com.wuzhen.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 活动信息 active_info
 *
 * @author zhengzheng
 */
public class ActiveInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 活动编号
     */
    @Excel(name = "报名用户编号", cellType = ColumnType.NUMERIC)
    private Long id;


    /**
     * 活动标题
     */
    @Excel(name = "活动标题")
    private String activeTitle;


    /**
     * 活动内容描述
     */
    @Excel(name = "活动内容描述")
    private String activeDesc;

    /**
     * 活动开始日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "活动开始日期")
    private String activeStartDate;

    /**
     * 活动结束日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "活动结束日期")
    private String activeEndDate;

    /**
     * 海报地址
     */
    @Excel(name = "活动类型")
    private String activeType;

    /**
     * 活动图片
     */
    @Excel(name = "活动图片")
    private String activePic;

    /**
     * 推荐人编号
     */
    @Excel(name = "活动状态")
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActiveTitle() {
        return activeTitle;
    }

    public void setActiveTitle(String activeTitle) {
        this.activeTitle = activeTitle;
    }

    public String getActiveDesc() {
        return activeDesc;
    }

    public void setActiveDesc(String activeDesc) {
        this.activeDesc = activeDesc;
    }

    public String getActiveStartDate() {
        return activeStartDate;
    }

    public void setActiveStartDate(String activeStartDate) {
        this.activeStartDate = activeStartDate;
    }

    public String getActiveEndDate() {
        return activeEndDate;
    }

    public void setActiveEndDate(String activeEndDate) {
        this.activeEndDate = activeEndDate;
    }

    public String getActiveType() {
        return activeType;
    }

    public void setActiveType(String activeType) {
        this.activeType = activeType;
    }

    public String getActivePic() {
        return activePic;
    }

    public void setActivePic(String activePic) {
        this.activePic = activePic;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("activeTitle", getActiveTitle())
                .append("activeDesc", getActiveDesc())
                .append("activeStartDate", getActiveStartDate())
                .append("activeEndDate", getActiveEndDate())
                .append("activeType", getActiveType())
                .append("activePic", getActivePic())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }

}

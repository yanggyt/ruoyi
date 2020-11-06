package com.ruoyi.front.domain;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 线上课程评价对象 online_courses_evaluate
 * 
 * @author ruoyi
 * @date 2020-11-05
 */
public class OnlineCoursesEvaluate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 线上课程ID */
    @Excel(name = "线上课程ID")
    private Long onlineCoursesId;

    //线上课程标题
    private String onlineCoursesName;

    /** 评价内容 */
    @Excel(name = "评价内容")
    private String evaluateContent;

    /** 匿名标志（0匿名 1用户） */
    @Excel(name = "匿名标志", readConverterExp = "0=匿名,1=用户")
    private String anonymousFlag;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 状态（0:待审核，1：审核不通过，2：审核通过） */
    @Excel(name = "状态", readConverterExp = "0=:待审核，1：审核不通过，2：审核通过")
    private String auditStatus;

    /** 审核者 */
    @Excel(name = "审核者")
    private String checkBy;

    /** 审核时间 */
    @Excel(name = "审核时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date checkTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public String getOnlineCoursesName()
    {
        return onlineCoursesName;
    }
    public void setOnlineCoursesName(String  onlineCoursesName)
    {
        this.onlineCoursesName = onlineCoursesName;
    }

    public Long getId() 
    {
        return id;
    }
    public void setOnlineCoursesId(Long onlineCoursesId) 
    {
        this.onlineCoursesId = onlineCoursesId;
    }

    public Long getOnlineCoursesId() 
    {
        return onlineCoursesId;
    }
    public void setEvaluateContent(String evaluateContent) 
    {
        this.evaluateContent = evaluateContent;
    }

    public String getEvaluateContent() 
    {
        return evaluateContent;
    }
    public void setAnonymousFlag(String anonymousFlag) 
    {
        this.anonymousFlag = anonymousFlag;
    }

    public String getAnonymousFlag() 
    {
        return anonymousFlag;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }
    public void setAuditStatus(String auditStatus) 
    {
        this.auditStatus = auditStatus;
    }

    public String getAuditStatus() 
    {
        return auditStatus;
    }
    public void setCheckBy(String checkBy) 
    {
        this.checkBy = checkBy;
    }

    public String getCheckBy() 
    {
        return checkBy;
    }
    public void setCheckTime(Date checkTime) 
    {
        this.checkTime = checkTime;
    }

    public Date getCheckTime() 
    {
        return checkTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("onlineCoursesId", getOnlineCoursesId())
            .append("onlineCoursesName", getOnlineCoursesName())
            .append("evaluateContent", getEvaluateContent())
            .append("anonymousFlag", getAnonymousFlag())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("auditStatus", getAuditStatus())
            .append("checkBy", getCheckBy())
            .append("checkTime", getCheckTime())
            .append("remark", getRemark())
            .toString();
    }
}

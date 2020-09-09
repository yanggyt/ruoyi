package com.ruoyi.his.domain;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 医生排班对象 his_doctor_schedule
 * 
 * @author bend
 * @date 2020-07-03
 */
public class HisDoctorSchedule extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** 机构ID */
    private String orgCode;

    /** 机构名称 */
    @Excel(name = "机构名称")
    private String orgName;

    /** 科室ID */
    private String deptId;

    /** 科室名称 */
    @Excel(name = "科室名称")
    private String deptName;

    /** 医生ID */
    private String doctorId;

    /** 医生名称 */
    @Excel(name = "医生名称")
    private String doctorName;

    /** 排班日期 */
    @Excel(name = "排班日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date scheduleDate;

    /** 班次标签（1上午 2下午） */
    private Integer scheduleTag;

    /** 班次 */
    @Excel(name = "班次")
    private String scheduleShift;

    /** 线上限制量 */
    @Excel(name = "线上限制量")
    private Integer onLimitNum;

    /** 线上已挂量 */
    @Excel(name = "线上已挂量")
    private Integer onNum;

    /** 线下限制量 */
    @Excel(name = "线下限制量")
    private Integer offLimitNum;

    /** 线下已挂量 */
    @Excel(name = "线下已挂量")
    private Integer offNum;

    /** 显示状态（0否 1是） */
    @Excel(name = "显示状态", readConverterExp = "0=否,1=是")
    private Integer isShow;

    /** 删除标记（0否 1是） */
    private Integer deleted;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setOrgCode(String orgCode) 
    {
        this.orgCode = orgCode;
    }

    public String getOrgCode() 
    {
        return orgCode;
    }
    public void setOrgName(String orgName) 
    {
        this.orgName = orgName;
    }

    public String getOrgName() 
    {
        return orgName;
    }
    public void setDeptId(String deptId) 
    {
        this.deptId = deptId;
    }

    public String getDeptId() 
    {
        return deptId;
    }
    public void setDeptName(String deptName) 
    {
        this.deptName = deptName;
    }

    public String getDeptName() 
    {
        return deptName;
    }
    public void setDoctorId(String doctorId) 
    {
        this.doctorId = doctorId;
    }

    public String getDoctorId() 
    {
        return doctorId;
    }
    public void setDoctorName(String doctorName) 
    {
        this.doctorName = doctorName;
    }

    public String getDoctorName() 
    {
        return doctorName;
    }
    public void setScheduleDate(Date scheduleDate) 
    {
        this.scheduleDate = scheduleDate;
    }

    public Date getScheduleDate() 
    {
        return scheduleDate;
    }
    public void setScheduleTag(Integer scheduleTag) 
    {
        this.scheduleTag = scheduleTag;
    }

    public Integer getScheduleTag() 
    {
        return scheduleTag;
    }
    public void setScheduleShift(String scheduleShift) 
    {
        this.scheduleShift = scheduleShift;
    }

    public String getScheduleShift() 
    {
        return scheduleShift;
    }
    public void setOnLimitNum(Integer onLimitNum) 
    {
        this.onLimitNum = onLimitNum;
    }

    public Integer getOnLimitNum() 
    {
        return onLimitNum;
    }
    public void setOnNum(Integer onNum) 
    {
        this.onNum = onNum;
    }

    public Integer getOnNum() 
    {
        return onNum;
    }
    public void setOffLimitNum(Integer offLimitNum) 
    {
        this.offLimitNum = offLimitNum;
    }

    public Integer getOffLimitNum() 
    {
        return offLimitNum;
    }
    public void setOffNum(Integer offNum) 
    {
        this.offNum = offNum;
    }

    public Integer getOffNum() 
    {
        return offNum;
    }
    public void setIsShow(Integer isShow) 
    {
        this.isShow = isShow;
    }

    public Integer getIsShow() 
    {
        return isShow;
    }
    public void setDeleted(Integer deleted) 
    {
        this.deleted = deleted;
    }

    public Integer getDeleted() 
    {
        return deleted;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("orgCode", getOrgCode())
            .append("orgName", getOrgName())
            .append("deptId", getDeptId())
            .append("deptName", getDeptName())
            .append("doctorId", getDoctorId())
            .append("doctorName", getDoctorName())
            .append("scheduleDate", getScheduleDate())
            .append("scheduleTag", getScheduleTag())
            .append("scheduleShift", getScheduleShift())
            .append("onLimitNum", getOnLimitNum())
            .append("onNum", getOnNum())
            .append("offLimitNum", getOffLimitNum())
            .append("offNum", getOffNum())
            .append("isShow", getIsShow())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("deleted", getDeleted())
            .toString();
    }
}

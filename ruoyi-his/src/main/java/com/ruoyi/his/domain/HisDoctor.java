package com.ruoyi.his.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 医生对象 his_doctor
 * 
 * @author bend
 * @date 2020-07-01
 */
public class HisDoctor extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** 用户ID */
    private Long memberId;

    /** 医生ID */
    private String doctorId;

    /** 医生名称 */
    @Excel(name = "医生名称")
    private String doctorName;

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

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String mobilePhone;

    /** 医生头像 */
    private String avatar;

    /** 职称头衔 */
    @Excel(name = "职称头衔")
    private String jobTitle;

    /** 工作年限 */
    @Excel(name = "工作年限")
    private Long workingYears;

    /** 擅长领域 */
    @Excel(name = "擅长领域")
    private String expertiseAreas;

    /** 医生简介 */
    @Excel(name = "医生简介")
    private String doctorProfile;

    /** 粉丝量 */
    private Long fans;

    /** 星级评分 */
    private BigDecimal starRating;

    /** 显示状态（0否 1是） */
    @Excel(name = "显示状态", readConverterExp = "0=否,1=是")
    private Integer isShow;

    /** 虚拟账户（0否 1是） */
    @Excel(name = "虚拟账户", readConverterExp = "0=否,1=是")
    private Integer isVirtualAccount;

    /** 挂号模板 */
    private String templateId;

    /** 模板名称 */
    @Excel(name = "模板名称")
    private String templateName;

    /** 日挂号量 */
    private Long dailyRegisteredNum;

    /** 排序号 */
    @Excel(name = "排序号")
    private Integer sortNo;

    /** 删除标记（0否 1是） */
    private Integer deleted;

    /** 优秀医生标识 默认不存在 */
    private boolean flag = false;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setMemberId(Long memberId) 
    {
        this.memberId = memberId;
    }

    public Long getMemberId() 
    {
        return memberId;
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
    public void setMobilePhone(String mobilePhone) 
    {
        this.mobilePhone = mobilePhone;
    }

    public String getMobilePhone() 
    {
        return mobilePhone;
    }
    public void setAvatar(String avatar) 
    {
        this.avatar = avatar;
    }

    public String getAvatar() 
    {
        return avatar;
    }
    public void setJobTitle(String jobTitle) 
    {
        this.jobTitle = jobTitle;
    }

    public String getJobTitle() 
    {
        return jobTitle;
    }
    public void setWorkingYears(Long workingYears) 
    {
        this.workingYears = workingYears;
    }

    public Long getWorkingYears() 
    {
        return workingYears;
    }
    public void setExpertiseAreas(String expertiseAreas) 
    {
        this.expertiseAreas = expertiseAreas;
    }

    public String getExpertiseAreas() 
    {
        return expertiseAreas;
    }
    public void setDoctorProfile(String doctorProfile) 
    {
        this.doctorProfile = doctorProfile;
    }

    public String getDoctorProfile() 
    {
        return doctorProfile;
    }
    public void setFans(Long fans) 
    {
        this.fans = fans;
    }

    public Long getFans() 
    {
        return fans;
    }
    public void setStarRating(BigDecimal starRating) 
    {
        this.starRating = starRating;
    }

    public BigDecimal getStarRating() 
    {
        return starRating;
    }
    public void setIsShow(Integer isShow) 
    {
        this.isShow = isShow;
    }

    public Integer getIsShow() 
    {
        return isShow;
    }
    public void setIsVirtualAccount(Integer isVirtualAccount) 
    {
        this.isVirtualAccount = isVirtualAccount;
    }

    public Integer getIsVirtualAccount() 
    {
        return isVirtualAccount;
    }
    public void setTemplateId(String templateId) 
    {
        this.templateId = templateId;
    }

    public String getTemplateId() 
    {
        return templateId;
    }
    public void setTemplateName(String templateName) 
    {
        this.templateName = templateName;
    }

    public String getTemplateName() 
    {
        return templateName;
    }
    public void setDailyRegisteredNum(Long dailyRegisteredNum) 
    {
        this.dailyRegisteredNum = dailyRegisteredNum;
    }

    public Long getDailyRegisteredNum() 
    {
        return dailyRegisteredNum;
    }
    public void setSortNo(Integer sortNo) 
    {
        this.sortNo = sortNo;
    }

    public Integer getSortNo() 
    {
        return sortNo;
    }
    public void setDeleted(Integer deleted) 
    {
        this.deleted = deleted;
    }

    public Integer getDeleted() 
    {
        return deleted;
    }

    public boolean isFlag()
    {
        return flag;
    }

    public void setFlag(boolean flag)
    {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("memberId", getMemberId())
            .append("doctorId", getDoctorId())
            .append("doctorName", getDoctorName())
            .append("orgCode", getOrgCode())
            .append("orgName", getOrgName())
            .append("deptId", getDeptId())
            .append("deptName", getDeptName())
            .append("mobilePhone", getMobilePhone())
            .append("avatar", getAvatar())
            .append("jobTitle", getJobTitle())
            .append("workingYears", getWorkingYears())
            .append("expertiseAreas", getExpertiseAreas())
            .append("doctorProfile", getDoctorProfile())
            .append("fans", getFans())
            .append("starRating", getStarRating())
            .append("isShow", getIsShow())
            .append("isVirtualAccount", getIsVirtualAccount())
            .append("templateId", getTemplateId())
            .append("templateName", getTemplateName())
            .append("dailyRegisteredNum", getDailyRegisteredNum())
            .append("sortNo", getSortNo())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("deleted", getDeleted())
            .toString();
    }
}

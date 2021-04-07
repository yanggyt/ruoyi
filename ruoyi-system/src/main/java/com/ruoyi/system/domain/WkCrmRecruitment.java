package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 招聘职位对象 wk_crm_recruitment
 * 
 * @author ruoyi
 * @date 2021-04-06
 */
public class WkCrmRecruitment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    /** 职位名称 */
    @Excel(name = "职位名称")
    private String jobTitle;

    /** 用人部门 */
    @Excel(name = "用人部门")
    private String employPersons;

    /** 工作性质 */
    @Excel(name = "工作性质")
    private String natureofWork;

    /** 工作城市 */
    @Excel(name = "工作城市")
    private String workCity;

    /** 招聘人数 */
    @Excel(name = "招聘人数")
    private Long hiring;

    /** 已入职人数 */
    @Excel(name = "已入职人数")
    private Long employees;

    /** 招聘进度 */
    @Excel(name = "招聘进度")
    private String schedule;

    /** 工作经验 */
    @Excel(name = "工作经验")
    private String experience;

    /** 学历要求 */
    @Excel(name = "学历要求")
    private String required;

    /** 薪资范围 */
    @Excel(name = "薪资范围")
    private String range;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setJobTitle(String jobTitle) 
    {
        this.jobTitle = jobTitle;
    }

    public String getJobTitle() 
    {
        return jobTitle;
    }
    public void setEmployPersons(String employPersons) 
    {
        this.employPersons = employPersons;
    }

    public String getEmployPersons() 
    {
        return employPersons;
    }
    public void setNatureofWork(String natureofWork) 
    {
        this.natureofWork = natureofWork;
    }

    public String getNatureofWork() 
    {
        return natureofWork;
    }
    public void setWorkCity(String workCity) 
    {
        this.workCity = workCity;
    }

    public String getWorkCity() 
    {
        return workCity;
    }
    public void setHiring(Long hiring) 
    {
        this.hiring = hiring;
    }

    public Long getHiring() 
    {
        return hiring;
    }
    public void setEmployees(Long employees) 
    {
        this.employees = employees;
    }

    public Long getEmployees() 
    {
        return employees;
    }
    public void setSchedule(String schedule) 
    {
        this.schedule = schedule;
    }

    public String getSchedule() 
    {
        return schedule;
    }
    public void setExperience(String experience) 
    {
        this.experience = experience;
    }

    public String getExperience() 
    {
        return experience;
    }
    public void setRequired(String required) 
    {
        this.required = required;
    }

    public String getRequired() 
    {
        return required;
    }
    public void setRange(String range) 
    {
        this.range = range;
    }

    public String getRange() 
    {
        return range;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("jobTitle", getJobTitle())
            .append("employPersons", getEmployPersons())
            .append("natureofWork", getNatureofWork())
            .append("workCity", getWorkCity())
            .append("hiring", getHiring())
            .append("employees", getEmployees())
            .append("schedule", getSchedule())
            .append("experience", getExperience())
            .append("required", getRequired())
            .append("range", getRange())
            .toString();
    }
}

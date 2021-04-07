package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 候选人对象 wk_crm_candidate
 * 
 * @author ruoyi
 * @date 2021-04-06
 */
public class WkCrmCandidate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Integer id;

    /** 应聘职位 */
    @Excel(name = "应聘职位")
    private String position;

    /** 用人部门 */
    @Excel(name = "用人部门")
    private String department;

    /** 候选人状态 */
    @Excel(name = "候选人状态")
    private String candidateStatus;

    /** 手机号 */
    @Excel(name = "手机号")
    private String phone;

    /** 性别 */
    @Excel(name = "性别")
    private String gender;

    /** 年龄 */
    @Excel(name = "年龄")
    private Long age;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String email;

    /** 招聘负责人 */
    @Excel(name = "招聘负责人")
    private String boss;

    /** 工作年限 */
    @Excel(name = "工作年限")
    private String workingYears;

    /** 学历 */
    @Excel(name = "学历")
    private String education;

    /** 毕业院校 */
    @Excel(name = "毕业院校")
    private String graduate;

    /** 最近工作单位 */
    @Excel(name = "最近工作单位")
    private String work;

    /** 招聘渠道 */
    @Excel(name = "招聘渠道")
    private String recruitment;

    /** 面试时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "面试时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date interview;

    /** 面试轮次 */
    @Excel(name = "面试轮次")
    private String degree;

    /** 面试官 */
    @Excel(name = "面试官")
    private String interviewer;

    /** 面试方式 */
    @Excel(name = "面试方式")
    private String waysOfIntervie;

    /** 其他面试官 */
    @Excel(name = "其他面试官")
    private String elseInterviewer;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date creation;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setPosition(String position) 
    {
        this.position = position;
    }

    public String getPosition() 
    {
        return position;
    }
    public void setDepartment(String department) 
    {
        this.department = department;
    }

    public String getDepartment() 
    {
        return department;
    }
    public void setCandidateStatus(String candidateStatus) 
    {
        this.candidateStatus = candidateStatus;
    }

    public String getCandidateStatus() 
    {
        return candidateStatus;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setGender(String gender) 
    {
        this.gender = gender;
    }

    public String getGender() 
    {
        return gender;
    }
    public void setAge(Long age) 
    {
        this.age = age;
    }

    public Long getAge() 
    {
        return age;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setBoss(String boss) 
    {
        this.boss = boss;
    }

    public String getBoss() 
    {
        return boss;
    }
    public void setWorkingYears(String workingYears) 
    {
        this.workingYears = workingYears;
    }

    public String getWorkingYears() 
    {
        return workingYears;
    }
    public void setEducation(String education) 
    {
        this.education = education;
    }

    public String getEducation() 
    {
        return education;
    }
    public void setGraduate(String graduate) 
    {
        this.graduate = graduate;
    }

    public String getGraduate() 
    {
        return graduate;
    }
    public void setWork(String work) 
    {
        this.work = work;
    }

    public String getWork() 
    {
        return work;
    }
    public void setRecruitment(String recruitment) 
    {
        this.recruitment = recruitment;
    }

    public String getRecruitment() 
    {
        return recruitment;
    }
    public void setInterview(Date interview) 
    {
        this.interview = interview;
    }

    public Date getInterview() 
    {
        return interview;
    }
    public void setDegree(String degree) 
    {
        this.degree = degree;
    }

    public String getDegree() 
    {
        return degree;
    }
    public void setInterviewer(String interviewer) 
    {
        this.interviewer = interviewer;
    }

    public String getInterviewer() 
    {
        return interviewer;
    }
    public void setWaysOfIntervie(String waysOfIntervie) 
    {
        this.waysOfIntervie = waysOfIntervie;
    }

    public String getWaysOfIntervie() 
    {
        return waysOfIntervie;
    }
    public void setElseInterviewer(String elseInterviewer) 
    {
        this.elseInterviewer = elseInterviewer;
    }

    public String getElseInterviewer() 
    {
        return elseInterviewer;
    }
    public void setCreation(Date creation) 
    {
        this.creation = creation;
    }

    public Date getCreation() 
    {
        return creation;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("position", getPosition())
            .append("department", getDepartment())
            .append("candidateStatus", getCandidateStatus())
            .append("phone", getPhone())
            .append("gender", getGender())
            .append("age", getAge())
            .append("email", getEmail())
            .append("boss", getBoss())
            .append("workingYears", getWorkingYears())
            .append("education", getEducation())
            .append("graduate", getGraduate())
            .append("work", getWork())
            .append("recruitment", getRecruitment())
            .append("interview", getInterview())
            .append("degree", getDegree())
            .append("interviewer", getInterviewer())
            .append("waysOfIntervie", getWaysOfIntervie())
            .append("elseInterviewer", getElseInterviewer())
            .append("creation", getCreation())
            .toString();
    }
}

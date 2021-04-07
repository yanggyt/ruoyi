package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 员工管理对象 wk_crm_staff_management1
 * 
 * @author ruoyi
 * @date 2021-04-06
 */
public class WkCrmStaffManagement1 extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 姓名 */
    @Excel(name = "姓名")
    private String name;

    /** 手机号 */
    @Excel(name = "手机号")
    private String mobilePhone;

    /** 证件类型 */
    @Excel(name = "证件类型")
    private String certificateType;

    /** 证件号码 */
    @Excel(name = "证件号码")
    private String certificateId;

    /** 性别 */
    @Excel(name = "性别")
    private String gender;

    /** 出生日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "出生日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date birthdayDate;

    /** 生日 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "生日", width = 30, dateFormat = "yyyy-MM-dd")
    private Date birthday;

    /** 年龄 */
    @Excel(name = "年龄")
    private Long age;

    /** 是否已婚 */
    @Excel(name = "是否已婚")
    private String married;

    /** 是否已孕 */
    @Excel(name = "是否已孕")
    private String pregnancy;

    /** 国家地区 */
    @Excel(name = "国家地区")
    private String countriesRegions;

    /** 民族 */
    @Excel(name = "民族")
    private String nation;

    /** 政治面貌 */
    @Excel(name = "政治面貌")
    private String politicsStatus;

    /** 籍贯 */
    @Excel(name = "籍贯")
    private String nativePlace;

    /** 户籍所在地 */
    @Excel(name = "户籍所在地")
    private String placeOfDomicile;

    /** 健康状态 */
    @Excel(name = "健康状态")
    private String health;

    /** 最高学历 */
    @Excel(name = "最高学历")
    private String highestEducation;

    /** 入职时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "入职时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date hireDate;

    /** 试用期 */
    @Excel(name = "试用期")
    private Long probationPeriod;

    /** 转正日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "转正日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date regularizationDate;

    /** 工号 */
    @Excel(name = "工号")
    private Long jobNumber;

    /** 部门 */
    @Excel(name = "部门")
    private String department;

    /** 直属上级 */
    @Excel(name = "直属上级")
    private String directSupervisor;

    /** 岗位 */
    @Excel(name = "岗位")
    private String post;

    /** 职级 */
    @Excel(name = "职级")
    private String jobGrade;

    /** 工作地点 */
    @Excel(name = "工作地点")
    private String workSite;

    /** 详细工作地点 */
    @Excel(name = "详细工作地点")
    private String detailedWorkLocation;

    /** 工作城市 */
    @Excel(name = "工作城市")
    private String workCity;

    /** 招聘渠道 */
    @Excel(name = "招聘渠道")
    private String recruitmentChannel;

    /** 聘用城市 */
    @Excel(name = "聘用城市")
    private String employmentCity;

    /** 司龄开始日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "司龄开始日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date commencementSeniorityDate;

    /** 司龄 */
    @Excel(name = "司龄")
    private Long workingYears;

    /** 合同类型 */
    @Excel(name = "合同类型")
    private String contractType;

    /** 现合同开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "现合同开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date contractCommencementTime;

    /** 现合同结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "现合同结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endOfContrac;

    /** 现合同期限 */
    @Excel(name = "现合同期限")
    private Long currentContractTerm;

    /** 工资卡卡号 */
    @Excel(name = "工资卡卡号")
    private Long wagesCardNumber;

    /** 工资卡开户城市 */
    @Excel(name = "工资卡开户城市")
    private String accountOpeningCity;

    /** 银行卡名称 */
    @Excel(name = "银行卡名称")
    private String bankCardName;

    /** 工资卡开户行 */
    @Excel(name = "工资卡开户行")
    private String payCardBank;

    /** 个人社保账号 */
    @Excel(name = "个人社保账号")
    private Long socialSecurityAccount;

    /** 个人公积金账号 */
    @Excel(name = "个人公积金账号")
    private Long providentFundAccount;

    /** 操作 */
    @Excel(name = "操作")
    private String operation;

    /** 办理转正 */
    @Excel(name = "办理转正")
    private String regularization;

    /** 调整部门岗位 */
    @Excel(name = "调整部门岗位")
    private String adjustmentOfDepartmentalPosts;

    /** 晋升/降级 */
    @Excel(name = "晋升/降级")
    private String promotion;

    /** 参保方案 */
    @Excel(name = "参保方案")
    private String ginsengProtectPlan;

    /** 办理离职 */
    @Excel(name = "办理离职")
    private String forDeparture;

    /** 钉钉用户id */
    @Excel(name = "钉钉用户id")
    private String userId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setMobilePhone(String mobilePhone) 
    {
        this.mobilePhone = mobilePhone;
    }

    public String getMobilePhone() 
    {
        return mobilePhone;
    }
    public void setCertificateType(String certificateType) 
    {
        this.certificateType = certificateType;
    }

    public String getCertificateType() 
    {
        return certificateType;
    }
    public void setCertificateId(String certificateId) 
    {
        this.certificateId = certificateId;
    }

    public String getCertificateId() 
    {
        return certificateId;
    }
    public void setGender(String gender) 
    {
        this.gender = gender;
    }

    public String getGender() 
    {
        return gender;
    }
    public void setBirthdayDate(Date birthdayDate) 
    {
        this.birthdayDate = birthdayDate;
    }

    public Date getBirthdayDate() 
    {
        return birthdayDate;
    }
    public void setBirthday(Date birthday) 
    {
        this.birthday = birthday;
    }

    public Date getBirthday() 
    {
        return birthday;
    }
    public void setAge(Long age) 
    {
        this.age = age;
    }

    public Long getAge() 
    {
        return age;
    }
    public void setMarried(String married) 
    {
        this.married = married;
    }

    public String getMarried() 
    {
        return married;
    }
    public void setPregnancy(String pregnancy) 
    {
        this.pregnancy = pregnancy;
    }

    public String getPregnancy() 
    {
        return pregnancy;
    }
    public void setCountriesRegions(String countriesRegions) 
    {
        this.countriesRegions = countriesRegions;
    }

    public String getCountriesRegions() 
    {
        return countriesRegions;
    }
    public void setNation(String nation) 
    {
        this.nation = nation;
    }

    public String getNation() 
    {
        return nation;
    }
    public void setPoliticsStatus(String politicsStatus) 
    {
        this.politicsStatus = politicsStatus;
    }

    public String getPoliticsStatus() 
    {
        return politicsStatus;
    }
    public void setNativePlace(String nativePlace) 
    {
        this.nativePlace = nativePlace;
    }

    public String getNativePlace() 
    {
        return nativePlace;
    }
    public void setPlaceOfDomicile(String placeOfDomicile) 
    {
        this.placeOfDomicile = placeOfDomicile;
    }

    public String getPlaceOfDomicile() 
    {
        return placeOfDomicile;
    }
    public void setHealth(String health) 
    {
        this.health = health;
    }

    public String getHealth() 
    {
        return health;
    }
    public void setHighestEducation(String highestEducation) 
    {
        this.highestEducation = highestEducation;
    }

    public String getHighestEducation() 
    {
        return highestEducation;
    }
    public void setHireDate(Date hireDate) 
    {
        this.hireDate = hireDate;
    }

    public Date getHireDate() 
    {
        return hireDate;
    }
    public void setProbationPeriod(Long probationPeriod) 
    {
        this.probationPeriod = probationPeriod;
    }

    public Long getProbationPeriod() 
    {
        return probationPeriod;
    }
    public void setRegularizationDate(Date regularizationDate) 
    {
        this.regularizationDate = regularizationDate;
    }

    public Date getRegularizationDate() 
    {
        return regularizationDate;
    }
    public void setJobNumber(Long jobNumber) 
    {
        this.jobNumber = jobNumber;
    }

    public Long getJobNumber() 
    {
        return jobNumber;
    }
    public void setDepartment(String department) 
    {
        this.department = department;
    }

    public String getDepartment() 
    {
        return department;
    }
    public void setDirectSupervisor(String directSupervisor) 
    {
        this.directSupervisor = directSupervisor;
    }

    public String getDirectSupervisor() 
    {
        return directSupervisor;
    }
    public void setPost(String post) 
    {
        this.post = post;
    }

    public String getPost() 
    {
        return post;
    }
    public void setJobGrade(String jobGrade) 
    {
        this.jobGrade = jobGrade;
    }

    public String getJobGrade() 
    {
        return jobGrade;
    }
    public void setWorkSite(String workSite) 
    {
        this.workSite = workSite;
    }

    public String getWorkSite() 
    {
        return workSite;
    }
    public void setDetailedWorkLocation(String detailedWorkLocation) 
    {
        this.detailedWorkLocation = detailedWorkLocation;
    }

    public String getDetailedWorkLocation() 
    {
        return detailedWorkLocation;
    }
    public void setWorkCity(String workCity) 
    {
        this.workCity = workCity;
    }

    public String getWorkCity() 
    {
        return workCity;
    }
    public void setRecruitmentChannel(String recruitmentChannel) 
    {
        this.recruitmentChannel = recruitmentChannel;
    }

    public String getRecruitmentChannel() 
    {
        return recruitmentChannel;
    }
    public void setEmploymentCity(String employmentCity) 
    {
        this.employmentCity = employmentCity;
    }

    public String getEmploymentCity() 
    {
        return employmentCity;
    }
    public void setCommencementSeniorityDate(Date commencementSeniorityDate) 
    {
        this.commencementSeniorityDate = commencementSeniorityDate;
    }

    public Date getCommencementSeniorityDate() 
    {
        return commencementSeniorityDate;
    }
    public void setWorkingYears(Long workingYears) 
    {
        this.workingYears = workingYears;
    }

    public Long getWorkingYears() 
    {
        return workingYears;
    }
    public void setContractType(String contractType) 
    {
        this.contractType = contractType;
    }

    public String getContractType() 
    {
        return contractType;
    }
    public void setContractCommencementTime(Date contractCommencementTime) 
    {
        this.contractCommencementTime = contractCommencementTime;
    }

    public Date getContractCommencementTime() 
    {
        return contractCommencementTime;
    }
    public void setEndOfContrac(Date endOfContrac) 
    {
        this.endOfContrac = endOfContrac;
    }

    public Date getEndOfContrac() 
    {
        return endOfContrac;
    }
    public void setCurrentContractTerm(Long currentContractTerm) 
    {
        this.currentContractTerm = currentContractTerm;
    }

    public Long getCurrentContractTerm() 
    {
        return currentContractTerm;
    }
    public void setWagesCardNumber(Long wagesCardNumber) 
    {
        this.wagesCardNumber = wagesCardNumber;
    }

    public Long getWagesCardNumber() 
    {
        return wagesCardNumber;
    }
    public void setAccountOpeningCity(String accountOpeningCity) 
    {
        this.accountOpeningCity = accountOpeningCity;
    }

    public String getAccountOpeningCity() 
    {
        return accountOpeningCity;
    }
    public void setBankCardName(String bankCardName) 
    {
        this.bankCardName = bankCardName;
    }

    public String getBankCardName() 
    {
        return bankCardName;
    }
    public void setPayCardBank(String payCardBank) 
    {
        this.payCardBank = payCardBank;
    }

    public String getPayCardBank() 
    {
        return payCardBank;
    }
    public void setSocialSecurityAccount(Long socialSecurityAccount) 
    {
        this.socialSecurityAccount = socialSecurityAccount;
    }

    public Long getSocialSecurityAccount() 
    {
        return socialSecurityAccount;
    }
    public void setProvidentFundAccount(Long providentFundAccount) 
    {
        this.providentFundAccount = providentFundAccount;
    }

    public Long getProvidentFundAccount() 
    {
        return providentFundAccount;
    }
    public void setOperation(String operation) 
    {
        this.operation = operation;
    }

    public String getOperation() 
    {
        return operation;
    }
    public void setRegularization(String regularization) 
    {
        this.regularization = regularization;
    }

    public String getRegularization() 
    {
        return regularization;
    }
    public void setAdjustmentOfDepartmentalPosts(String adjustmentOfDepartmentalPosts) 
    {
        this.adjustmentOfDepartmentalPosts = adjustmentOfDepartmentalPosts;
    }

    public String getAdjustmentOfDepartmentalPosts() 
    {
        return adjustmentOfDepartmentalPosts;
    }
    public void setPromotion(String promotion) 
    {
        this.promotion = promotion;
    }

    public String getPromotion() 
    {
        return promotion;
    }
    public void setGinsengProtectPlan(String ginsengProtectPlan) 
    {
        this.ginsengProtectPlan = ginsengProtectPlan;
    }

    public String getGinsengProtectPlan() 
    {
        return ginsengProtectPlan;
    }
    public void setForDeparture(String forDeparture) 
    {
        this.forDeparture = forDeparture;
    }

    public String getForDeparture() 
    {
        return forDeparture;
    }
    public void setUserId(String userId) 
    {
        this.userId = userId;
    }

    public String getUserId() 
    {
        return userId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("mobilePhone", getMobilePhone())
            .append("certificateType", getCertificateType())
            .append("certificateId", getCertificateId())
            .append("gender", getGender())
            .append("birthdayDate", getBirthdayDate())
            .append("birthday", getBirthday())
            .append("age", getAge())
            .append("married", getMarried())
            .append("pregnancy", getPregnancy())
            .append("countriesRegions", getCountriesRegions())
            .append("nation", getNation())
            .append("politicsStatus", getPoliticsStatus())
            .append("nativePlace", getNativePlace())
            .append("placeOfDomicile", getPlaceOfDomicile())
            .append("health", getHealth())
            .append("highestEducation", getHighestEducation())
            .append("hireDate", getHireDate())
            .append("probationPeriod", getProbationPeriod())
            .append("regularizationDate", getRegularizationDate())
            .append("jobNumber", getJobNumber())
            .append("department", getDepartment())
            .append("directSupervisor", getDirectSupervisor())
            .append("post", getPost())
            .append("jobGrade", getJobGrade())
            .append("workSite", getWorkSite())
            .append("detailedWorkLocation", getDetailedWorkLocation())
            .append("workCity", getWorkCity())
            .append("recruitmentChannel", getRecruitmentChannel())
            .append("employmentCity", getEmploymentCity())
            .append("commencementSeniorityDate", getCommencementSeniorityDate())
            .append("workingYears", getWorkingYears())
            .append("contractType", getContractType())
            .append("contractCommencementTime", getContractCommencementTime())
            .append("endOfContrac", getEndOfContrac())
            .append("currentContractTerm", getCurrentContractTerm())
            .append("wagesCardNumber", getWagesCardNumber())
            .append("accountOpeningCity", getAccountOpeningCity())
            .append("bankCardName", getBankCardName())
            .append("payCardBank", getPayCardBank())
            .append("socialSecurityAccount", getSocialSecurityAccount())
            .append("providentFundAccount", getProvidentFundAccount())
            .append("operation", getOperation())
            .append("regularization", getRegularization())
            .append("adjustmentOfDepartmentalPosts", getAdjustmentOfDepartmentalPosts())
            .append("promotion", getPromotion())
            .append("ginsengProtectPlan", getGinsengProtectPlan())
            .append("forDeparture", getForDeparture())
            .append("userId", getUserId())
            .toString();
    }
}

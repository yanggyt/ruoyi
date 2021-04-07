package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 组织管理对象 wk_crm_organization_management
 * 
 * @author ruoyi
 * @date 2021-04-06
 */
public class WkCrmOrganizationManagement extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    /** 公司 */
    @Excel(name = "公司")
    private String company;

    /** 总经理 */
    @Excel(name = "总经理")
    private String generalManager;

    /** 行政部 */
    @Excel(name = "行政部")
    private String administrationSection;

    /** 人事部 */
    @Excel(name = "人事部")
    private String ministryPersonnel;

    /** 财务部 */
    @Excel(name = "财务部")
    private String accountingDepartment;

    /** 研发部 */
    @Excel(name = "研发部")
    private String researchDevelopment;

    /** 市场部 */
    @Excel(name = "市场部")
    private String bazaar;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCompany(String company) 
    {
        this.company = company;
    }

    public String getCompany() 
    {
        return company;
    }
    public void setGeneralManager(String generalManager) 
    {
        this.generalManager = generalManager;
    }

    public String getGeneralManager() 
    {
        return generalManager;
    }
    public void setAdministrationSection(String administrationSection) 
    {
        this.administrationSection = administrationSection;
    }

    public String getAdministrationSection() 
    {
        return administrationSection;
    }
    public void setMinistryPersonnel(String ministryPersonnel) 
    {
        this.ministryPersonnel = ministryPersonnel;
    }

    public String getMinistryPersonnel() 
    {
        return ministryPersonnel;
    }
    public void setAccountingDepartment(String accountingDepartment) 
    {
        this.accountingDepartment = accountingDepartment;
    }

    public String getAccountingDepartment() 
    {
        return accountingDepartment;
    }
    public void setResearchDevelopment(String researchDevelopment) 
    {
        this.researchDevelopment = researchDevelopment;
    }

    public String getResearchDevelopment() 
    {
        return researchDevelopment;
    }
    public void setBazaar(String bazaar) 
    {
        this.bazaar = bazaar;
    }

    public String getBazaar() 
    {
        return bazaar;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("company", getCompany())
            .append("generalManager", getGeneralManager())
            .append("administrationSection", getAdministrationSection())
            .append("ministryPersonnel", getMinistryPersonnel())
            .append("accountingDepartment", getAccountingDepartment())
            .append("researchDevelopment", getResearchDevelopment())
            .append("bazaar", getBazaar())
            .toString();
    }
}

package com.ruoyi.busi.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 客户公司人员对象 busi_customer_person
 * 
 * @author WangCL
 * @date 2021-12-16
 */
public class BusiCustomerPerson extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID主键 */
    private String id;

    /** 所属公司 */
//    @Excel(name = "所属公司")
    private String companyId;

    /** 所属公司 */
    @Excel(name = "所属公司")
    private String companyName;

    /** 姓名 */
    @Excel(name = "姓名")
    private String personName;

    /** 性别 */
    @Excel(name = "性别")
    private String sex;

    /** 手机号码 */
    @Excel(name = "手机号码")
    private String phonenumber;

    /** 人员邮箱 */
    @Excel(name = "人员邮箱")
    private String email;

    /** 角色 */
    @Excel(name = "角色")
    private String role;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setCompanyId(String companyId) 
    {
        this.companyId = companyId;
    }

    public String getCompanyId() 
    {
        return companyId;
    }
    public void setPersonName(String personName) 
    {
        this.personName = personName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPersonName()
    {
        return personName;
    }
    public void setSex(String sex) 
    {
        this.sex = sex;
    }

    public String getSex() 
    {
        return sex;
    }
    public void setPhonenumber(String phonenumber) 
    {
        this.phonenumber = phonenumber;
    }

    public String getPhonenumber() 
    {
        return phonenumber;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setRole(String role) 
    {
        this.role = role;
    }

    public String getRole() 
    {
        return role;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("companyId", getCompanyId())
            .append("companyName", getCompanyName())
            .append("personName", getPersonName())
            .append("sex", getSex())
            .append("phonenumber", getPhonenumber())
            .append("email", getEmail())
            .append("role", getRole())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}

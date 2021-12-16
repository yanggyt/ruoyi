package com.ruoyi.busi.domain;

import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 客户公司对象 busi_customer_company
 * 
 * @author WangCL
 * @date 2021-12-16
 */
public class BusiCustomerCompany extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private String id;

    /** 公司名称 */
    @Excel(name = "公司名称")
    private String coName;

    /** 公司地址 */
    @Excel(name = "公司地址")
    private String addr;

    /** 对公账号 */
    @Excel(name = "对公账号")
    private String account;

    /** 纳税号 */
    @Excel(name = "纳税号")
    private String taxNumber;

    /** 客户公司人员信息 */
    private List<BusiCustomerPerson> busiCustomerPersonList;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setCoName(String coName) 
    {
        this.coName = coName;
    }

    public String getCoName() 
    {
        return coName;
    }
    public void setAddr(String addr) 
    {
        this.addr = addr;
    }

    public String getAddr() 
    {
        return addr;
    }
    public void setAccount(String account) 
    {
        this.account = account;
    }

    public String getAccount() 
    {
        return account;
    }
    public void setTaxNumber(String taxNumber) 
    {
        this.taxNumber = taxNumber;
    }

    public String getTaxNumber() 
    {
        return taxNumber;
    }

    public List<BusiCustomerPerson> getBusiCustomerPersonList()
    {
        return busiCustomerPersonList;
    }

    public void setBusiCustomerPersonList(List<BusiCustomerPerson> busiCustomerPersonList)
    {
        this.busiCustomerPersonList = busiCustomerPersonList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("coName", getCoName())
            .append("addr", getAddr())
            .append("account", getAccount())
            .append("taxNumber", getTaxNumber())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("busiCustomerPersonList", getBusiCustomerPersonList())
            .toString();
    }
}

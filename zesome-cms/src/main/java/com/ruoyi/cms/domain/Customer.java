package com.ruoyi.cms.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 客户表 cms_customer
 * 
 * @author pengc
 * @date 2019-05-26
 */
public class Customer extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 客户id */
	private Integer customerid;
	/** 名称 */
	private String name;
	/** 性别 */
	private Integer sex;
	/** 联系方式 */
	private String phone;
	/** 邮箱 */
	private String email;
	/** 咨询类型 */
	private Integer type;
	/** 内容 */
	private String context;

	public void setCustomerid(Integer customerid) 
	{
		this.customerid = customerid;
	}

	public Integer getCustomerid() 
	{
		return customerid;
	}
	public void setName(String name) 
	{
		this.name = name;
	}

	public String getName() 
	{
		return name;
	}
	public void setSex(Integer sex) 
	{
		this.sex = sex;
	}

	public Integer getSex() 
	{
		return sex;
	}
	public void setPhone(String phone) 
	{
		this.phone = phone;
	}

	public String getPhone() 
	{
		return phone;
	}
	public void setEmail(String email) 
	{
		this.email = email;
	}

	public String getEmail() 
	{
		return email;
	}
	public void setType(Integer type) 
	{
		this.type = type;
	}

	public Integer getType() 
	{
		return type;
	}
	public void setContext(String context) 
	{
		this.context = context;
	}

	public String getContext() 
	{
		return context;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("customerid", getCustomerid())
            .append("name", getName())
            .append("sex", getSex())
            .append("phone", getPhone())
            .append("email", getEmail())
            .append("type", getType())
            .append("context", getContext())
            .append("createTime", getCreateTime())
            .toString();
    }
}

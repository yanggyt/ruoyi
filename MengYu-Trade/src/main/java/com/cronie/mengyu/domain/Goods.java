package com.cronie.mengyu.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.base.BaseEntity;
import java.util.Date;

/**
 * 投资品种表 my_goods
 * 
 * @author cronie
 * @date 2018-11-15
 */
public class Goods extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 品种代码 */
	private String code;
	/** 品种名称 */
	private String name;
	/** 品种类型 （股标，基金等） */
	private Integer codeType;
	/** 行业类型（军工，证券等） */
	private Integer industryType;
	/** 板块类型（上证，深证 创业板等） */
	private Integer plateType;
	/** 创建人 */
	private Integer creater;
	/** 创建时间 */
	private Date createTime;

	public void setCode(String code) 
	{
		this.code = code;
	}

	public String getCode() 
	{
		return code;
	}
	public void setName(String name) 
	{
		this.name = name;
	}

	public String getName() 
	{
		return name;
	}
	public void setCodeType(Integer codeType) 
	{
		this.codeType = codeType;
	}

	public Integer getCodeType() 
	{
		return codeType;
	}
	public void setIndustryType(Integer industryType) 
	{
		this.industryType = industryType;
	}

	public Integer getIndustryType() 
	{
		return industryType;
	}
	public void setPlateType(Integer plateType) 
	{
		this.plateType = plateType;
	}

	public Integer getPlateType() 
	{
		return plateType;
	}
	public void setCreater(Integer creater) 
	{
		this.creater = creater;
	}

	public Integer getCreater() 
	{
		return creater;
	}
	public void setCreateTime(Date createTime) 
	{
		this.createTime = createTime;
	}

	public Date getCreateTime() 
	{
		return createTime;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("code", getCode())
            .append("name", getName())
            .append("codeType", getCodeType())
            .append("industryType", getIndustryType())
            .append("plateType", getPlateType())
            .append("creater", getCreater())
            .append("createTime", getCreateTime())
            .toString();
    }
}

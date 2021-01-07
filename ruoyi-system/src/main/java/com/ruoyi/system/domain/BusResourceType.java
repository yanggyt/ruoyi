package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 资源分类表 bus_resource_type
 * 
 * @author ruoyi
 * @date 2021-01-07
 */
public class BusResourceType extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 分类id */
	private Long typeId;
	/** 分类名称 */
	private String typeName;
	/** 上级分类id */
	private Long parentId;
	/** 祖籍节点列表 */
	private String parentIds;
	/** 祖籍节点名称列表 */
	private String parentNames;
	/** 分类级别 */
	private String classes;
	/** 分类排序 */
	private Long sort;

	public void setTypeId(Long typeId) 
	{
		this.typeId = typeId;
	}

	public Long getTypeId() 
	{
		return typeId;
	}
	public void setTypeName(String typeName) 
	{
		this.typeName = typeName;
	}

	public String getTypeName() 
	{
		return typeName;
	}
	public void setParentId(Long parentId) 
	{
		this.parentId = parentId;
	}

	public Long getParentId() 
	{
		return parentId;
	}
	public void setParentIds(String parentIds) 
	{
		this.parentIds = parentIds;
	}

	public String getParentIds() 
	{
		return parentIds;
	}
	public void setParentNames(String parentNames) 
	{
		this.parentNames = parentNames;
	}

	public String getParentNames() 
	{
		return parentNames;
	}
	public void setClasses(String classes) 
	{
		this.classes = classes;
	}

	public String getClasses() 
	{
		return classes;
	}
	public void setSort(Long sort) 
	{
		this.sort = sort;
	}

	public Long getSort() 
	{
		return sort;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("typeId", getTypeId())
            .append("typeName", getTypeName())
            .append("parentId", getParentId())
            .append("parentIds", getParentIds())
            .append("parentNames", getParentNames())
            .append("classes", getClasses())
            .append("sort", getSort())
            .toString();
    }
}

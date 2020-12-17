package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 关联实体维护对象 sys_relev_table
 * 
 * @author dalin
 * @date 2020-12-17
 */
public class RelevTable extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long relevId;

    /** 表单名称 */
    @Excel(name = "表单名称")
    private String relevName;

    /** 弹框 */
    @Excel(name = "关联实体")
    private String relevEntity;

    /**  */
    @Excel(name = "关联实体ID")
    private String relevEntityId;

    /**  */
    @Excel(name = "关联实体名称")
    private String relevEntityName;

    /** 默认值（1是） */
    @Excel(name = "默认值", readConverterExp = "1=是")
    private String isDefaultValue;

    /** 弹框 */
    @Excel(name = "关联表名")
    private String relevTable;

    /**  */
    @Excel(name = "关联表字段ID")
    private String relevTableId;

    /**  */
    @Excel(name = "关联表字段名称")
    private String relevTableName;

    public void setRelevId(Long relevId) 
    {
        this.relevId = relevId;
    }

    public Long getRelevId() 
    {
        return relevId;
    }
    public void setRelevName(String relevName) 
    {
        this.relevName = relevName;
    }

    public String getRelevName() 
    {
        return relevName;
    }
    public void setRelevEntity(String relevEntity) 
    {
        this.relevEntity = relevEntity;
    }

    public String getRelevEntity() 
    {
        return relevEntity;
    }
    public void setRelevEntityId(String relevEntityId) 
    {
        this.relevEntityId = relevEntityId;
    }

    public String getRelevEntityId() 
    {
        return relevEntityId;
    }
    public void setRelevEntityName(String relevEntityName) 
    {
        this.relevEntityName = relevEntityName;
    }

    public String getRelevEntityName() 
    {
        return relevEntityName;
    }
    public void setIsDefaultValue(String isDefaultValue) 
    {
        this.isDefaultValue = isDefaultValue;
    }

    public String getIsDefaultValue() 
    {
        return isDefaultValue;
    }
    public void setRelevTable(String relevTable) 
    {
        this.relevTable = relevTable;
    }

    public String getRelevTable() 
    {
        return relevTable;
    }
    public void setRelevTableId(String relevTableId) 
    {
        this.relevTableId = relevTableId;
    }

    public String getRelevTableId() 
    {
        return relevTableId;
    }
    public void setRelevTableName(String relevTableName) 
    {
        this.relevTableName = relevTableName;
    }

    public String getRelevTableName() 
    {
        return relevTableName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("relevId", getRelevId())
            .append("relevName", getRelevName())
            .append("relevEntity", getRelevEntity())
            .append("relevEntityId", getRelevEntityId())
            .append("relevEntityName", getRelevEntityName())
            .append("isDefaultValue", getIsDefaultValue())
            .append("relevTable", getRelevTable())
            .append("relevTableId", getRelevTableId())
            .append("relevTableName", getRelevTableName())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}

package com.ruoyi.front.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 合同模板对象 contract_template
 * 
 * @author ruoyi
 * @date 2020-10-21
 */
public class ContractTemplate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 合同分类id(contract_type表主键id) */
    @Excel(name = "合同分类id(contract_type表主键id)")
    private String type;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 简介 */
    @Excel(name = "简介")
    private String introduction;

    /** 详情内容 */
    @Excel(name = "详情内容")
    private String content;

    /** 点击量 */
    @Excel(name = "点击量")
    private Integer hits;

    /** 附件下载（多个地址用，分隔） */
    @Excel(name = "附件地址")
    private String enclosureUrl;

    @Excel(name = "附件名称")
    private String enclosureName;

    /** 状态（0正常 1停用） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setIntroduction(String introduction) 
    {
        this.introduction = introduction;
    }

    public String getIntroduction() 
    {
        return introduction;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setHits(Integer hits) 
    {
        this.hits = hits;
    }

    public Integer getHits() 
    {
        return hits;
    }
    public void setEnclosureUrl(String enclosureUrl) 
    {
        this.enclosureUrl = enclosureUrl;
    }

    public String getEnclosureUrl() 
    {
        return enclosureUrl;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    public String getEnclosureName() {
        return enclosureName;
    }

    public void setEnclosureName(String enclosureName) {
        this.enclosureName = enclosureName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("type", getType())
            .append("title", getTitle())
            .append("introduction", getIntroduction())
            .append("content", getContent())
            .append("hits", getHits())
            .append("enclosureUrl", getEnclosureUrl())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}

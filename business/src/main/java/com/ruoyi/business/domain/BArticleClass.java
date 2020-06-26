package com.ruoyi.business.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.TreeEntity;

/**
 * 文章分类对象 b_article_class
 * 
 * @author anjie
 * @date 2020-06-26
 */
public class BArticleClass extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 描述 */
    @Excel(name = "描述")
    private String articleDescribe;

    /** 附加参数 */
    @Excel(name = "附加参数")
    private String additional;

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
    public void setArticleDescribe(String articleDescribe) 
    {
        this.articleDescribe = articleDescribe;
    }

    public String getArticleDescribe() 
    {
        return articleDescribe;
    }
    public void setAdditional(String additional) 
    {
        this.additional = additional;
    }

    public String getAdditional() 
    {
        return additional;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("parentId", getParentId())
            .append("name", getName())
            .append("articleDescribe", getArticleDescribe())
            .append("additional", getAdditional())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}

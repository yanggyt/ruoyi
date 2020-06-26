package com.ruoyi.business.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 文章对象 b_article
 *
 * @author anjie
 * @date 2020-06-26
 */
public class BArticle extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 内容 */
    private String content;

    /** 语言 */
    @Excel(name = "语言")
    private String language;

    /** 来源 */
    @Excel(name = "来源")
    private String source;

    /** 外链 */
    @Excel(name = "外链")
    private String link;

    /** 分类id */
    @Excel(name = "分类id")
    private Long articleclassid;

    /** 分类id */
    @Excel(name = "分类名称")
    private String parentName;


    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }
    public void setContent(String content)
    {
        this.content = content;
    }

    public String getContent()
    {
        return content;
    }
    public void setLanguage(String language)
    {
        this.language = language;
    }

    public String getLanguage()
    {
        return language;
    }
    public void setSource(String source)
    {
        this.source = source;
    }

    public String getSource()
    {
        return source;
    }
    public void setLink(String link)
    {
        this.link = link;
    }

    public String getLink()
    {
        return link;
    }
    public void setArticleclassid(Long articleclassid)
    {
        this.articleclassid = articleclassid;
    }

    public Long getArticleclassid()
    {
        return articleclassid;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("title", getTitle())
            .append("content", getContent())
            .append("language", getLanguage())
            .append("source", getSource())
            .append("link", getLink())
            .append("articleclassid", getArticleclassid())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}

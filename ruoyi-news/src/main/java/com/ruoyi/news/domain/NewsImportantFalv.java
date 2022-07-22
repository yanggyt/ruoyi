package com.ruoyi.news.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 news_important_falv
 * 
 * @author admin@abdu.net.cn
 * @date 2022-07-19
 */
public class NewsImportantFalv extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 内容 */
    @Excel(name = "内容")
    private String contenu;

    /** 图文轮播 */
    @Excel(name = "图文轮播")
    private String image;

    /** 创建人ID */
    @Excel(name = "创建人ID")
    private Long auteurid;

    /** 作者 */
    @Excel(name = "作者")
    private String auteur;

    /** 修改人ID */
    @Excel(name = "修改人ID")
    private Long corrigerid;

    /** 来源 */
    @Excel(name = "来源")
    private String source;

    /** 点击数量 */
    @Excel(name = "点击数量")
    private Long clicks;

    /** 类型 */
    @Excel(name = "类型")
    private Long type;

    /** 更新日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date date;

    /** 分类 */
    @Excel(name = "分类")
    private Long classify;

    /** 状态 */
    @Excel(name = "状态")
    private Long status;

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
    public void setContenu(String contenu) 
    {
        this.contenu = contenu;
    }

    public String getContenu() 
    {
        return contenu;
    }
    public void setImage(String image) 
    {
        this.image = image;
    }

    public String getImage() 
    {
        return image;
    }
    public void setAuteurid(Long auteurid) 
    {
        this.auteurid = auteurid;
    }

    public Long getAuteurid() 
    {
        return auteurid;
    }
    public void setAuteur(String auteur) 
    {
        this.auteur = auteur;
    }

    public String getAuteur() 
    {
        return auteur;
    }
    public void setCorrigerid(Long corrigerid) 
    {
        this.corrigerid = corrigerid;
    }

    public Long getCorrigerid() 
    {
        return corrigerid;
    }
    public void setSource(String source) 
    {
        this.source = source;
    }

    public String getSource() 
    {
        return source;
    }
    public void setClicks(Long clicks) 
    {
        this.clicks = clicks;
    }

    public Long getClicks() 
    {
        return clicks;
    }
    public void setType(Long type) 
    {
        this.type = type;
    }

    public Long getType() 
    {
        return type;
    }
    public void setDate(Date date) 
    {
        this.date = date;
    }

    public Date getDate() 
    {
        return date;
    }
    public void setClassify(Long classify) 
    {
        this.classify = classify;
    }

    public Long getClassify() 
    {
        return classify;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("title", getTitle())
            .append("contenu", getContenu())
            .append("image", getImage())
            .append("auteurid", getAuteurid())
            .append("auteur", getAuteur())
            .append("corrigerid", getCorrigerid())
            .append("source", getSource())
            .append("clicks", getClicks())
            .append("type", getType())
            .append("date", getDate())
            .append("classify", getClassify())
            .append("status", getStatus())
            .toString();
    }
}

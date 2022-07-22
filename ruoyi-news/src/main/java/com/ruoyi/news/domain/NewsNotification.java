package com.ruoyi.news.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 news_notification
 * 
 * @author admin@abdu.net.cn
 * @date 2022-07-19
 */
public class NewsNotification extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 单位 */
    @Excel(name = "单位")
    private String unit;

    /** 授课人 */
    @Excel(name = "授课人")
    private String instructor;

    /** 内容 */
    @Excel(name = "内容")
    private String contenu;

    /** 发布者 */
    @Excel(name = "发布者")
    private String auteur;

    /** 创建日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date date;

    /** 附件 */
    @Excel(name = "附件")
    private String files;

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
    public void setUnit(String unit) 
    {
        this.unit = unit;
    }

    public String getUnit() 
    {
        return unit;
    }
    public void setInstructor(String instructor) 
    {
        this.instructor = instructor;
    }

    public String getInstructor() 
    {
        return instructor;
    }
    public void setContenu(String contenu) 
    {
        this.contenu = contenu;
    }

    public String getContenu() 
    {
        return contenu;
    }
    public void setAuteur(String auteur) 
    {
        this.auteur = auteur;
    }

    public String getAuteur() 
    {
        return auteur;
    }
    public void setDate(Date date) 
    {
        this.date = date;
    }

    public Date getDate() 
    {
        return date;
    }
    public void setFiles(String files) 
    {
        this.files = files;
    }

    public String getFiles() 
    {
        return files;
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
            .append("unit", getUnit())
            .append("instructor", getInstructor())
            .append("contenu", getContenu())
            .append("auteur", getAuteur())
            .append("date", getDate())
            .append("files", getFiles())
            .append("status", getStatus())
            .toString();
    }
}

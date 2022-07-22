package com.ruoyi.news.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 news_swat_news
 * 
 * @author admin@abdu.net.cn
 * @date 2022-07-19
 */
public class NewsSwatNews extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 第几期 */
    @Excel(name = "第几期")
    private Long idNumber;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 内容 */
    @Excel(name = "内容")
    private String contenu;

    /** 图文轮播 */
    @Excel(name = "图文轮播")
    private String image;

    /** 发布部门ID */
    @Excel(name = "发布部门ID")
    private Long deptid;

    /** 发布部门 */
    @Excel(name = "发布部门")
    private String deptname;

    /** 审核者 */
    @Excel(name = "审核者")
    private String check;

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

    /** 更新日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date date;

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
    public void setIdNumber(Long idNumber) 
    {
        this.idNumber = idNumber;
    }

    public Long getIdNumber() 
    {
        return idNumber;
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
    public void setDeptid(Long deptid) 
    {
        this.deptid = deptid;
    }

    public Long getDeptid() 
    {
        return deptid;
    }
    public void setDeptname(String deptname) 
    {
        this.deptname = deptname;
    }

    public String getDeptname() 
    {
        return deptname;
    }
    public void setCheck(String check) 
    {
        this.check = check;
    }

    public String getCheck() 
    {
        return check;
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
    public void setDate(Date date) 
    {
        this.date = date;
    }

    public Date getDate() 
    {
        return date;
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
            .append("idNumber", getIdNumber())
            .append("title", getTitle())
            .append("contenu", getContenu())
            .append("image", getImage())
            .append("deptid", getDeptid())
            .append("deptname", getDeptname())
            .append("check", getCheck())
            .append("auteur", getAuteur())
            .append("corrigerid", getCorrigerid())
            .append("source", getSource())
            .append("clicks", getClicks())
            .append("date", getDate())
            .append("status", getStatus())
            .toString();
    }
}

package com.ruoyi.news.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 news_photo
 * 
 * @author admin@abdu.net.cn
 * @date 2022-07-19
 */
public class NewsPhoto extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 图片 */
    @Excel(name = "图片")
    private String image;

    /** 来源 */
    @Excel(name = "来源")
    private String url;

    /** 上传日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "上传日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date date;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setImage(String image) 
    {
        this.image = image;
    }

    public String getImage() 
    {
        return image;
    }
    public void setUrl(String url) 
    {
        this.url = url;
    }

    public String getUrl() 
    {
        return url;
    }
    public void setDate(Date date) 
    {
        this.date = date;
    }

    public Date getDate() 
    {
        return date;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("image", getImage())
            .append("url", getUrl())
            .append("date", getDate())
            .toString();
    }
}

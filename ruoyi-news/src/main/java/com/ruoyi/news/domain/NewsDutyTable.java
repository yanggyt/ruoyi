package com.ruoyi.news.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 news_duty_table
 * 
 * @author admin@abdu.net.cn
 * @date 2022-07-19
 */
public class NewsDutyTable extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 值班领导 */
    @Excel(name = "值班领导")
    private String diriger;

    /** 值班干部 */
    @Excel(name = "值班干部")
    private String cadre;

    /** 值班日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "值班日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date date;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setDiriger(String diriger) 
    {
        this.diriger = diriger;
    }

    public String getDiriger() 
    {
        return diriger;
    }
    public void setCadre(String cadre) 
    {
        this.cadre = cadre;
    }

    public String getCadre() 
    {
        return cadre;
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
            .append("diriger", getDiriger())
            .append("cadre", getCadre())
            .append("date", getDate())
            .toString();
    }
}

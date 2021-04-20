package com.sinosoft.activity.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 白名单信息对象 draw_whitelist
 * 
 * @author dy
 * @date 2021-04-19
 */
public class DrawWhitelist extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    @Excel(name = "")
    private String WHITELISTID;

    /**  */
    @Excel(name = "")
    private String PHONE;

    /**  */
    @Excel(name = "")
    private String FILENAME;

    /**  */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "", width = 30, dateFormat = "yyyy-MM-dd")
    private Date CREATETIMESTAMP;

    /**  */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "", width = 30, dateFormat = "yyyy-MM-dd")
    private Date LASTUPDATETIMESTAMP;

    public void setWHITELISTID(String WHITELISTID) 
    {
        this.WHITELISTID = WHITELISTID;
    }

    public String getWHITELISTID() 
    {
        return WHITELISTID;
    }
    public void setPHONE(String PHONE) 
    {
        this.PHONE = PHONE;
    }

    public String getPHONE() 
    {
        return PHONE;
    }
    public void setFILENAME(String FILENAME) 
    {
        this.FILENAME = FILENAME;
    }

    public String getFILENAME() 
    {
        return FILENAME;
    }
    public void setCREATETIMESTAMP(Date CREATETIMESTAMP) 
    {
        this.CREATETIMESTAMP = CREATETIMESTAMP;
    }

    public Date getCREATETIMESTAMP() 
    {
        return CREATETIMESTAMP;
    }
    public void setLASTUPDATETIMESTAMP(Date LASTUPDATETIMESTAMP) 
    {
        this.LASTUPDATETIMESTAMP = LASTUPDATETIMESTAMP;
    }

    public Date getLASTUPDATETIMESTAMP() 
    {
        return LASTUPDATETIMESTAMP;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("WHITELISTID", getWHITELISTID())
            .append("PHONE", getPHONE())
            .append("FILENAME", getFILENAME())
            .append("CREATETIMESTAMP", getCREATETIMESTAMP())
            .append("LASTUPDATETIMESTAMP", getLASTUPDATETIMESTAMP())
            .toString();
    }
}

package com.sinosoft.activity.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户路由信息对象 draw_disc_config
 * 
 * @author dy
 * @date 2021-04-19
 */
public class DrawDiscConfig extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    @Excel(name = "")
    private String DISCDRAWCONFIGID;

    /**  */
    @Excel(name = "")
    private String ISSPECIALFLAG;

    /**  */
    @Excel(name = "")
    private String VSTATE;

    /**  */
    @Excel(name = "")
    private String ULEVEL;

    /**  */
    @Excel(name = "")
    private String DRAWCODE;

    /**  */
    @Excel(name = "")
    private Long GETNUMBER;

    /**  */
    @Excel(name = "")
    private String SOURCE;

    /**  */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "", width = 30, dateFormat = "yyyy-MM-dd")
    private Date CREATETIMESTAMP;

    /**  */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "", width = 30, dateFormat = "yyyy-MM-dd")
    private Date LASTUPDATETIMESTAMP;

    /**  */
    @Excel(name = "")
    private String DESCRIPTION;

    public void setDISCDRAWCONFIGID(String DISCDRAWCONFIGID) 
    {
        this.DISCDRAWCONFIGID = DISCDRAWCONFIGID;
    }

    public String getDISCDRAWCONFIGID() 
    {
        return DISCDRAWCONFIGID;
    }
    public void setISSPECIALFLAG(String ISSPECIALFLAG) 
    {
        this.ISSPECIALFLAG = ISSPECIALFLAG;
    }

    public String getISSPECIALFLAG() 
    {
        return ISSPECIALFLAG;
    }
    public void setVSTATE(String VSTATE) 
    {
        this.VSTATE = VSTATE;
    }

    public String getVSTATE() 
    {
        return VSTATE;
    }
    public void setULEVEL(String ULEVEL) 
    {
        this.ULEVEL = ULEVEL;
    }

    public String getULEVEL() 
    {
        return ULEVEL;
    }
    public void setDRAWCODE(String DRAWCODE) 
    {
        this.DRAWCODE = DRAWCODE;
    }

    public String getDRAWCODE() 
    {
        return DRAWCODE;
    }
    public void setGETNUMBER(Long GETNUMBER) 
    {
        this.GETNUMBER = GETNUMBER;
    }

    public Long getGETNUMBER() 
    {
        return GETNUMBER;
    }
    public void setSOURCE(String SOURCE) 
    {
        this.SOURCE = SOURCE;
    }

    public String getSOURCE() 
    {
        return SOURCE;
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
    public void setDESCRIPTION(String DESCRIPTION) 
    {
        this.DESCRIPTION = DESCRIPTION;
    }

    public String getDESCRIPTION() 
    {
        return DESCRIPTION;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("DISCDRAWCONFIGID", getDISCDRAWCONFIGID())
            .append("ISSPECIALFLAG", getISSPECIALFLAG())
            .append("VSTATE", getVSTATE())
            .append("ULEVEL", getULEVEL())
            .append("DRAWCODE", getDRAWCODE())
            .append("GETNUMBER", getGETNUMBER())
            .append("SOURCE", getSOURCE())
            .append("CREATETIMESTAMP", getCREATETIMESTAMP())
            .append("LASTUPDATETIMESTAMP", getLASTUPDATETIMESTAMP())
            .append("DESCRIPTION", getDESCRIPTION())
            .toString();
    }
}

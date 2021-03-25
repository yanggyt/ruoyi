package com.sinosoft.activity.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 抽奖记录信息对象 draw_record
 * 
 * @author ruoyi
 * @date 2021-03-25
 */
public class DrawRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String DRAWRECORDID;

    /** 抽奖流水 */
    @Excel(name = "抽奖流水")
    private String DRAWTRANSEQNO;

    /** 抽奖活动代码 */
    @Excel(name = "抽奖活动代码")
    private String DRAWCODE;

    /** 用户标识 */
    @Excel(name = "用户标识")
    private String USERID;

    /** 抽奖时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "抽奖时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date DRAWTIME;

    /** 抽奖结果 */
    @Excel(name = "抽奖结果")
    private String DRAWRESULT;

    /** 奖品代码 */
    @Excel(name = "奖品代码")
    private String PRIZECODE;

    /** 奖品类型 */
    @Excel(name = "奖品类型")
    private String PRIZETYPE;

    /** 账务日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "账务日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date CHECKINGDATE;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date CREATETIMESTAMP;

    /** 最后修改时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最后修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date LASTUPDATETIMESTAMP;

    /** 渠道 */
    @Excel(name = "渠道")
    private String CHANNEL;

    /** 用户名 */
    @Excel(name = "用户名")
    private String USERNAME;

    /** 奖品等级 */
    @Excel(name = "奖品等级")
    private String PRIZELEVEL;

    /** 奖品项目编码 */
    @Excel(name = "奖品项目编码")
    private String PROJECTCODE;

    /** 请求来源 */
    @Excel(name = "请求来源")
    private String SOURCE;

    /** 手机号 */
    @Excel(name = "手机号")
    private String PHONE;

    /** 扩展域 */
    @Excel(name = "扩展域")
    private String EXTAREA;

    public void setDRAWRECORDID(String DRAWRECORDID) 
    {
        this.DRAWRECORDID = DRAWRECORDID;
    }

    public String getDRAWRECORDID() 
    {
        return DRAWRECORDID;
    }
    public void setDRAWTRANSEQNO(String DRAWTRANSEQNO) 
    {
        this.DRAWTRANSEQNO = DRAWTRANSEQNO;
    }

    public String getDRAWTRANSEQNO() 
    {
        return DRAWTRANSEQNO;
    }
    public void setDRAWCODE(String DRAWCODE) 
    {
        this.DRAWCODE = DRAWCODE;
    }

    public String getDRAWCODE() 
    {
        return DRAWCODE;
    }
    public void setUSERID(String USERID) 
    {
        this.USERID = USERID;
    }

    public String getUSERID() 
    {
        return USERID;
    }
    public void setDRAWTIME(Date DRAWTIME) 
    {
        this.DRAWTIME = DRAWTIME;
    }

    public Date getDRAWTIME() 
    {
        return DRAWTIME;
    }
    public void setDRAWRESULT(String DRAWRESULT) 
    {
        this.DRAWRESULT = DRAWRESULT;
    }

    public String getDRAWRESULT() 
    {
        return DRAWRESULT;
    }
    public void setPRIZECODE(String PRIZECODE) 
    {
        this.PRIZECODE = PRIZECODE;
    }

    public String getPRIZECODE() 
    {
        return PRIZECODE;
    }
    public void setPRIZETYPE(String PRIZETYPE) 
    {
        this.PRIZETYPE = PRIZETYPE;
    }

    public String getPRIZETYPE() 
    {
        return PRIZETYPE;
    }
    public void setCHECKINGDATE(Date CHECKINGDATE) 
    {
        this.CHECKINGDATE = CHECKINGDATE;
    }

    public Date getCHECKINGDATE() 
    {
        return CHECKINGDATE;
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
    public void setCHANNEL(String CHANNEL) 
    {
        this.CHANNEL = CHANNEL;
    }

    public String getCHANNEL() 
    {
        return CHANNEL;
    }
    public void setUSERNAME(String USERNAME) 
    {
        this.USERNAME = USERNAME;
    }

    public String getUSERNAME() 
    {
        return USERNAME;
    }
    public void setPRIZELEVEL(String PRIZELEVEL) 
    {
        this.PRIZELEVEL = PRIZELEVEL;
    }

    public String getPRIZELEVEL() 
    {
        return PRIZELEVEL;
    }
    public void setPROJECTCODE(String PROJECTCODE) 
    {
        this.PROJECTCODE = PROJECTCODE;
    }

    public String getPROJECTCODE() 
    {
        return PROJECTCODE;
    }
    public void setSOURCE(String SOURCE) 
    {
        this.SOURCE = SOURCE;
    }

    public String getSOURCE() 
    {
        return SOURCE;
    }
    public void setPHONE(String PHONE) 
    {
        this.PHONE = PHONE;
    }

    public String getPHONE() 
    {
        return PHONE;
    }
    public void setEXTAREA(String EXTAREA) 
    {
        this.EXTAREA = EXTAREA;
    }

    public String getEXTAREA() 
    {
        return EXTAREA;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("DRAWRECORDID", getDRAWRECORDID())
            .append("DRAWTRANSEQNO", getDRAWTRANSEQNO())
            .append("DRAWCODE", getDRAWCODE())
            .append("USERID", getUSERID())
            .append("DRAWTIME", getDRAWTIME())
            .append("DRAWRESULT", getDRAWRESULT())
            .append("PRIZECODE", getPRIZECODE())
            .append("PRIZETYPE", getPRIZETYPE())
            .append("CHECKINGDATE", getCHECKINGDATE())
            .append("CREATETIMESTAMP", getCREATETIMESTAMP())
            .append("LASTUPDATETIMESTAMP", getLASTUPDATETIMESTAMP())
            .append("CHANNEL", getCHANNEL())
            .append("USERNAME", getUSERNAME())
            .append("PRIZELEVEL", getPRIZELEVEL())
            .append("PROJECTCODE", getPROJECTCODE())
            .append("SOURCE", getSOURCE())
            .append("PHONE", getPHONE())
            .append("EXTAREA", getEXTAREA())
            .toString();
    }
}

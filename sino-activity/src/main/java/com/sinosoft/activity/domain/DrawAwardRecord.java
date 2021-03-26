package com.sinosoft.activity.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 记录发奖信息对象 draw_award_record
 * 
 * @author dy
 * @date 2021-03-26
 */
public class DrawAwardRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String AWARDRECORDID;

    /** 抽奖活动代码 */
    @Excel(name = "抽奖活动代码")
    private String DRAWCODE;

    /** 奖品代码 */
    @Excel(name = "奖品代码")
    private String PRIZECODE;

    /** 用户标识 */
    @Excel(name = "用户标识")
    private String USERID;

    /** 抽奖记录标识 */
    @Excel(name = "抽奖记录标识")
    private String DRAWRECORDID;

    /** 发奖时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发奖时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date AWARDTIME;

    /** 发奖结果 */
    @Excel(name = "发奖结果")
    private String AWARDRESULT;

    /** 错误码 */
    @Excel(name = "错误码")
    private String RETURNCODE;

    /** 错误信息 */
    @Excel(name = "错误信息")
    private String RETURNMESSAGE;

    /** 手机号 */
    @Excel(name = "手机号")
    private String PHONE;

    /** 地址 */
    @Excel(name = "地址")
    private String ADDRESS;

    /** 用户名 */
    @Excel(name = "用户名")
    private String USERNAME;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date CREATETIMESTAMP;

    /** 最后修改时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最后修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date LASTUPDATETIMESTAMP;

    /** 抽奖流水 */
    @Excel(name = "抽奖流水")
    private String DRAWTRANSEQNO;

    /** 发奖流水 */
    @Excel(name = "发奖流水")
    private String AWARDTRANSEQNO;

    /** 奖品等级 */
    @Excel(name = "奖品等级")
    private String PRIZELEVEL;

    /** 奖品类型 */
    @Excel(name = "奖品类型")
    private String PRIZETYPE;

    /** 商户号 */
    @Excel(name = "商户号")
    private String MERCHANTCODE;

    /** 商户系统号 */
    @Excel(name = "商户系统号")
    private String MERCHANTSYSCODE;

    /** 渠道 */
    @Excel(name = "渠道")
    private String CHANNEL;

    /** 业务领域 */
    @Excel(name = "业务领域")
    private String BUSINESSAREA;

    /** 城市 */
    @Excel(name = "城市")
    private String CITY;

    /** 扩展 */
    @Excel(name = "扩展")
    private String EXTID;

    public void setAWARDRECORDID(String AWARDRECORDID) 
    {
        this.AWARDRECORDID = AWARDRECORDID;
    }

    public String getAWARDRECORDID() 
    {
        return AWARDRECORDID;
    }
    public void setDRAWCODE(String DRAWCODE) 
    {
        this.DRAWCODE = DRAWCODE;
    }

    public String getDRAWCODE() 
    {
        return DRAWCODE;
    }
    public void setPRIZECODE(String PRIZECODE) 
    {
        this.PRIZECODE = PRIZECODE;
    }

    public String getPRIZECODE() 
    {
        return PRIZECODE;
    }
    public void setUSERID(String USERID) 
    {
        this.USERID = USERID;
    }

    public String getUSERID() 
    {
        return USERID;
    }
    public void setDRAWRECORDID(String DRAWRECORDID) 
    {
        this.DRAWRECORDID = DRAWRECORDID;
    }

    public String getDRAWRECORDID() 
    {
        return DRAWRECORDID;
    }
    public void setAWARDTIME(Date AWARDTIME) 
    {
        this.AWARDTIME = AWARDTIME;
    }

    public Date getAWARDTIME() 
    {
        return AWARDTIME;
    }
    public void setAWARDRESULT(String AWARDRESULT) 
    {
        this.AWARDRESULT = AWARDRESULT;
    }

    public String getAWARDRESULT() 
    {
        return AWARDRESULT;
    }
    public void setRETURNCODE(String RETURNCODE) 
    {
        this.RETURNCODE = RETURNCODE;
    }

    public String getRETURNCODE() 
    {
        return RETURNCODE;
    }
    public void setRETURNMESSAGE(String RETURNMESSAGE) 
    {
        this.RETURNMESSAGE = RETURNMESSAGE;
    }

    public String getRETURNMESSAGE() 
    {
        return RETURNMESSAGE;
    }
    public void setPHONE(String PHONE) 
    {
        this.PHONE = PHONE;
    }

    public String getPHONE() 
    {
        return PHONE;
    }
    public void setADDRESS(String ADDRESS) 
    {
        this.ADDRESS = ADDRESS;
    }

    public String getADDRESS() 
    {
        return ADDRESS;
    }
    public void setUSERNAME(String USERNAME) 
    {
        this.USERNAME = USERNAME;
    }

    public String getUSERNAME() 
    {
        return USERNAME;
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
    public void setDRAWTRANSEQNO(String DRAWTRANSEQNO) 
    {
        this.DRAWTRANSEQNO = DRAWTRANSEQNO;
    }

    public String getDRAWTRANSEQNO() 
    {
        return DRAWTRANSEQNO;
    }
    public void setAWARDTRANSEQNO(String AWARDTRANSEQNO) 
    {
        this.AWARDTRANSEQNO = AWARDTRANSEQNO;
    }

    public String getAWARDTRANSEQNO() 
    {
        return AWARDTRANSEQNO;
    }
    public void setPRIZELEVEL(String PRIZELEVEL) 
    {
        this.PRIZELEVEL = PRIZELEVEL;
    }

    public String getPRIZELEVEL() 
    {
        return PRIZELEVEL;
    }
    public void setPRIZETYPE(String PRIZETYPE) 
    {
        this.PRIZETYPE = PRIZETYPE;
    }

    public String getPRIZETYPE() 
    {
        return PRIZETYPE;
    }
    public void setMERCHANTCODE(String MERCHANTCODE) 
    {
        this.MERCHANTCODE = MERCHANTCODE;
    }

    public String getMERCHANTCODE() 
    {
        return MERCHANTCODE;
    }
    public void setMERCHANTSYSCODE(String MERCHANTSYSCODE) 
    {
        this.MERCHANTSYSCODE = MERCHANTSYSCODE;
    }

    public String getMERCHANTSYSCODE() 
    {
        return MERCHANTSYSCODE;
    }
    public void setCHANNEL(String CHANNEL) 
    {
        this.CHANNEL = CHANNEL;
    }

    public String getCHANNEL() 
    {
        return CHANNEL;
    }
    public void setBUSINESSAREA(String BUSINESSAREA) 
    {
        this.BUSINESSAREA = BUSINESSAREA;
    }

    public String getBUSINESSAREA() 
    {
        return BUSINESSAREA;
    }
    public void setCITY(String CITY) 
    {
        this.CITY = CITY;
    }

    public String getCITY() 
    {
        return CITY;
    }
    public void setEXTID(String EXTID) 
    {
        this.EXTID = EXTID;
    }

    public String getEXTID() 
    {
        return EXTID;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("AWARDRECORDID", getAWARDRECORDID())
            .append("DRAWCODE", getDRAWCODE())
            .append("PRIZECODE", getPRIZECODE())
            .append("USERID", getUSERID())
            .append("DRAWRECORDID", getDRAWRECORDID())
            .append("AWARDTIME", getAWARDTIME())
            .append("AWARDRESULT", getAWARDRESULT())
            .append("RETURNCODE", getRETURNCODE())
            .append("RETURNMESSAGE", getRETURNMESSAGE())
            .append("PHONE", getPHONE())
            .append("ADDRESS", getADDRESS())
            .append("USERNAME", getUSERNAME())
            .append("CREATETIMESTAMP", getCREATETIMESTAMP())
            .append("LASTUPDATETIMESTAMP", getLASTUPDATETIMESTAMP())
            .append("DRAWTRANSEQNO", getDRAWTRANSEQNO())
            .append("AWARDTRANSEQNO", getAWARDTRANSEQNO())
            .append("PRIZELEVEL", getPRIZELEVEL())
            .append("PRIZETYPE", getPRIZETYPE())
            .append("MERCHANTCODE", getMERCHANTCODE())
            .append("MERCHANTSYSCODE", getMERCHANTSYSCODE())
            .append("CHANNEL", getCHANNEL())
            .append("BUSINESSAREA", getBUSINESSAREA())
            .append("CITY", getCITY())
            .append("EXTID", getEXTID())
            .toString();
    }
}

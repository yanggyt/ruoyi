package com.sinosoft.activity.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 存储奖项配置信息对象 draw_config
 *
 * @author xlh
 * @date 2021-03-26
 */
public class DrawConfig extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 业务主键 */
    private String DRAWCONFIGID;

    /** 奖品代码 */
    @Excel(name = "奖品代码")
    private String PRIZECODE;

    /** 抽奖活动代码 */
    @Excel(name = "抽奖活动代码")
    private String DRAWCODE;

    /** 概率 */
    @Excel(name = "概率")
    private String PROBABILITY;

    /** 显示序号 */
    @Excel(name = "显示序号")
    private String DISPLAYORDER;

    /** 奖品等级 */
    @Excel(name = "奖品等级", readConverterExp="blank=空奖品,prizeLevel1=一等奖,prizeLevel2=二等奖,prizeLevel3=三等奖,prizeLevel4=四等奖,prizeLevel5=五等奖,prizeLevel6=六等奖,prizeLevel7=七等奖")
    private String PRIZELEVEL;

    /** 提示信息 */
    @Excel(name = "提示信息")
    private String CUE;

    /** 奖品总数量 */
    @Excel(name = "奖品总数量")
    private Long TOTALNUMBER;

    /** 奖品剩余数量 */
    @Excel(name = "奖品剩余数量")
    private Long AVAILABLENUMBER;

    /** 状态 */
    @Excel(name = "状态" ,readConverterExp="1=启用,2=停用")
    private String STATUS;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date CREATETIMESTAMP;

    /** 最后修改时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最后修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date LASTUPDATETIMESTAMP;

    /** 中奖次数限制 */
    @Excel(name = "中奖次数限制",readConverterExp="none=无限制,distinction=当个活动限制,nodistinction=不区分活动限制")
    private String AWARDTYPE;

    /** 中奖次数限制值 */
    @Excel(name = "中奖次数限制值" )
    private Long AWARDTYPEVALUE;

    /** 领取方式 */
    @Excel(name = "领取方式" ,readConverterExp = "0=手动领取,1=自动领取")
    private String AWARDMETHOD;

    /**  */
    private String SENDMSGFLAG;

    /**  */
    private String MSGTEMPLETEID;

    private String PRIZENAME;

    public String getPRIZENAME() {
        return PRIZENAME;
    }

    public void setPRIZENAME(String PRIZENAME) {
        this.PRIZENAME = PRIZENAME;
    }

    public void setDRAWCONFIGID(String DRAWCONFIGID)
    {
        this.DRAWCONFIGID = DRAWCONFIGID;
    }

    public String getDRAWCONFIGID()
    {
        return DRAWCONFIGID;
    }
    public void setPRIZECODE(String PRIZECODE)
    {
        this.PRIZECODE = PRIZECODE;
    }

    public String getPRIZECODE()
    {
        return PRIZECODE;
    }
    public void setDRAWCODE(String DRAWCODE)
    {
        this.DRAWCODE = DRAWCODE;
    }

    public String getDRAWCODE()
    {
        return DRAWCODE;
    }
    public void setPROBABILITY(String PROBABILITY)
    {
        this.PROBABILITY = PROBABILITY;
    }

    public String getPROBABILITY()
    {
        return PROBABILITY;
    }
    public void setDISPLAYORDER(String DISPLAYORDER)
    {
        this.DISPLAYORDER = DISPLAYORDER;
    }

    public String getDISPLAYORDER()
    {
        return DISPLAYORDER;
    }
    public void setPRIZELEVEL(String PRIZELEVEL)
    {
        this.PRIZELEVEL = PRIZELEVEL;
    }

    public String getPRIZELEVEL()
    {
        return PRIZELEVEL;
    }
    public void setCUE(String CUE)
    {
        this.CUE = CUE;
    }

    public String getCUE()
    {
        return CUE;
    }
    public void setTOTALNUMBER(Long TOTALNUMBER)
    {
        this.TOTALNUMBER = TOTALNUMBER;
    }

    public Long getTOTALNUMBER()
    {
        return TOTALNUMBER;
    }
    public void setAVAILABLENUMBER(Long AVAILABLENUMBER)
    {
        this.AVAILABLENUMBER = AVAILABLENUMBER;
    }

    public Long getAVAILABLENUMBER()
    {
        return AVAILABLENUMBER;
    }
    public void setSTATUS(String STATUS)
    {
        this.STATUS = STATUS;
    }

    public String getSTATUS()
    {
        return STATUS;
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
    public void setAWARDTYPE(String AWARDTYPE)
    {
        this.AWARDTYPE = AWARDTYPE;
    }

    public String getAWARDTYPE()
    {
        return AWARDTYPE;
    }
    public void setAWARDTYPEVALUE(Long AWARDTYPEVALUE)
    {
        this.AWARDTYPEVALUE = AWARDTYPEVALUE;
    }

    public Long getAWARDTYPEVALUE()
    {
        return AWARDTYPEVALUE;
    }
    public void setAWARDMETHOD(String AWARDMETHOD)
    {
        this.AWARDMETHOD = AWARDMETHOD;
    }

    public String getAWARDMETHOD()
    {
        return AWARDMETHOD;
    }
    public void setSENDMSGFLAG(String SENDMSGFLAG)
    {
        this.SENDMSGFLAG = SENDMSGFLAG;
    }

    public String getSENDMSGFLAG()
    {
        return SENDMSGFLAG;
    }
    public void setMSGTEMPLETEID(String MSGTEMPLETEID)
    {
        this.MSGTEMPLETEID = MSGTEMPLETEID;
    }

    public String getMSGTEMPLETEID()
    {
        return MSGTEMPLETEID;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("DRAWCONFIGID", getDRAWCONFIGID())
                .append("PRIZECODE", getPRIZECODE())
                .append("DRAWCODE", getDRAWCODE())
                .append("PROBABILITY", getPROBABILITY())
                .append("DISPLAYORDER", getDISPLAYORDER())
                .append("PRIZELEVEL", getPRIZELEVEL())
                .append("CUE", getCUE())
                .append("TOTALNUMBER", getTOTALNUMBER())
                .append("AVAILABLENUMBER", getAVAILABLENUMBER())
                .append("STATUS", getSTATUS())
                .append("CREATETIMESTAMP", getCREATETIMESTAMP())
                .append("LASTUPDATETIMESTAMP", getLASTUPDATETIMESTAMP())
                .append("AWARDTYPE", getAWARDTYPE())
                .append("AWARDTYPEVALUE", getAWARDTYPEVALUE())
                .append("AWARDMETHOD", getAWARDMETHOD())
                .append("SENDMSGFLAG", getSENDMSGFLAG())
                .append("MSGTEMPLETEID", getMSGTEMPLETEID())
                .toString();
    }
}


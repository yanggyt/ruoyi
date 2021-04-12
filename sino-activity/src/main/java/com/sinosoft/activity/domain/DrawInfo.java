package com.sinosoft.activity.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;
import java.util.List;

/**
 * 抽奖活动管理对象 draw_info
 *
 * @author xlh
 * @date 2021-03-25
 */
public class DrawInfo  extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private String DRAWID;

    /** 抽奖活动代码 */
    @Excel(name = "抽奖活动代码")
    private String DRAWCODE;

    /** 抽奖活动类型 */
    @Excel(name = "抽奖活动类型")
    private String DRAWTYPE;

    /** 抽奖活动名称 */
    @Excel(name = "抽奖活动名称")
    private String DRAWNAME;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date STARTTIME;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date ENDTIME;

    /** 消耗对象 */
    @Excel(name = "消耗对象",readConverterExp ="integral=积分,task=抽奖次数")
    private String EXPENO;

    /** 消耗价值 */
    @Excel(name = "消耗价值")
    private Long EXPENOVALUE;

    /** 备注 */
    @Excel(name = "备注")
    private String COMMENTS;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date CREATETIMESTAMP;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date LASTUPDATETIMESTAMP;

    /** 状态 */
    @Excel(name = "状态" ,readConverterExp="1=启用,2=停用")
    private String STATUS;

    /** 是否需要更新缓存 */
    @Excel(name = "是否需要更新缓存")
    private String UPDATEFLAG;

    /** 规则描述 */
    @Excel(name = "规则描述")
    private String RULEDESCRIPTION;

    /**  */
    private String VALIDATETYPE;

    private List<String> drawId;

    public List<String> getDrawId() {
        return drawId;
    }

    public void setDrawId(List<String> drawId) {
        this.drawId = drawId;
    }

    public void setDRAWID(String DRAWID)
    {
        this.DRAWID = DRAWID;
    }

    public String getDRAWID()
    {
        return DRAWID;
    }
    public void setDRAWCODE(String DRAWCODE)
    {
        this.DRAWCODE = DRAWCODE;
    }

    public String getDRAWCODE()
    {
        return DRAWCODE;
    }
    public void setDRAWTYPE(String DRAWTYPE)
    {
        this.DRAWTYPE = DRAWTYPE;
    }

    public String getDRAWTYPE()
    {
        return DRAWTYPE;
    }
    public void setDRAWNAME(String DRAWNAME)
    {
        this.DRAWNAME = DRAWNAME;
    }

    public String getDRAWNAME()
    {
        return DRAWNAME;
    }
    public void setSTARTTIME(Date STARTTIME)
    {
        this.STARTTIME = STARTTIME;
    }

    public Date getSTARTTIME()
    {
        return STARTTIME;
    }
    public void setENDTIME(Date ENDTIME)
    {
        this.ENDTIME = ENDTIME;
    }

    public Date getENDTIME()
    {
        return ENDTIME;
    }
    public void setEXPENO(String EXPENO)
    {
        this.EXPENO = EXPENO;
    }

    public String getEXPENO()
    {
        return EXPENO;
    }
    public void setEXPENOVALUE(Long EXPENOVALUE)
    {
        this.EXPENOVALUE = EXPENOVALUE;
    }

    public Long getEXPENOVALUE()
    {
        return EXPENOVALUE;
    }
    public void setCOMMENTS(String COMMENTS)
    {
        this.COMMENTS = COMMENTS;
    }

    public String getCOMMENTS()
    {
        return COMMENTS;
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
    public void setSTATUS(String STATUS)
    {
        this.STATUS = STATUS;
    }

    public String getSTATUS()
    {
        return STATUS;
    }
    public void setUPDATEFLAG(String UPDATEFLAG)
    {
        this.UPDATEFLAG = UPDATEFLAG;
    }

    public String getUPDATEFLAG()
    {
        return UPDATEFLAG;
    }
    public void setRULEDESCRIPTION(String RULEDESCRIPTION)
    {
        this.RULEDESCRIPTION = RULEDESCRIPTION;
    }

    public String getRULEDESCRIPTION()
    {
        return RULEDESCRIPTION;
    }
    public void setVALIDATETYPE(String VALIDATETYPE)
    {
        this.VALIDATETYPE = VALIDATETYPE;
    }

    public String getVALIDATETYPE()
    {
        return VALIDATETYPE;
    }


}
package com.sinosoft.activity.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 存储奖品的基础信息对象 draw_prize_info
 * 
 * @author dy
 * @date 2021-03-25
 */
public class DrawPrizeInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 业务主键 */
    private int PRIZEID;

    /** 奖品代码 */
    @Excel(name = "奖品代码")
    private String PRIZECODE;

    /** 奖品名称 */
    @Excel(name = "奖品名称")
    private String PRIZENAME;

    /** 奖品类型 */
    @Excel(name = "奖品类型")
    private String PRIZETYPE;

    /** 奖品面额 */
    @Excel(name = "奖品面额")
    private Long PRIZEVALUE;

    /** 备注 */
    @Excel(name = "备注")
    private String COMMENTS;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date CREATETIMESTAMP;

    /** 最后修改时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最后修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date LASTUPDATETIMESTAMP;

    /** 状态 0 未启用，1 启用 */
    @Excel(name = "状态 0 未启用，1 启用")
    private String STATUS;

    /** 积分项目编码 */
    @Excel(name = "积分项目编码")
    private String INTEGRALPROJECTCODE;

    public void setPRIZEID(int PRIZEID)
    {
        this.PRIZEID = PRIZEID;
    }

    public int getPRIZEID()
    {
        return PRIZEID;
    }
    public void setPRIZECODE(String PRIZECODE) 
    {
        this.PRIZECODE = PRIZECODE;
    }

    public String getPRIZECODE() 
    {
        return PRIZECODE;
    }
    public void setPRIZENAME(String PRIZENAME) 
    {
        this.PRIZENAME = PRIZENAME;
    }

    public String getPRIZENAME() 
    {
        return PRIZENAME;
    }
    public void setPRIZETYPE(String PRIZETYPE) 
    {
        this.PRIZETYPE = PRIZETYPE;
    }

    public String getPRIZETYPE() 
    {
        return PRIZETYPE;
    }
    public void setPRIZEVALUE(Long PRIZEVALUE) 
    {
        this.PRIZEVALUE = PRIZEVALUE;
    }

    public Long getPRIZEVALUE() 
    {
        return PRIZEVALUE;
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
    public void setINTEGRALPROJECTCODE(String INTEGRALPROJECTCODE) 
    {
        this.INTEGRALPROJECTCODE = INTEGRALPROJECTCODE;
    }

    public String getINTEGRALPROJECTCODE() 
    {
        return INTEGRALPROJECTCODE;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("PRIZEID", getPRIZEID())
            .append("PRIZECODE", getPRIZECODE())
            .append("PRIZENAME", getPRIZENAME())
            .append("PRIZETYPE", getPRIZETYPE())
            .append("PRIZEVALUE", getPRIZEVALUE())
            .append("COMMENTS", getCOMMENTS())
            .append("CREATETIMESTAMP", getCREATETIMESTAMP())
            .append("LASTUPDATETIMESTAMP", getLASTUPDATETIMESTAMP())
            .append("STATUS", getSTATUS())
            .append("INTEGRALPROJECTCODE", getINTEGRALPROJECTCODE())
            .toString();
    }
}

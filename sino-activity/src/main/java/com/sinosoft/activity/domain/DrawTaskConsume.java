package com.sinosoft.activity.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 抽奖次数消费信息对象 draw_task_consume
 * 
 * @author dy
 * @date 2021-03-26
 */
public class DrawTaskConsume extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 业务主键 */
    private String TASKCONSUMEID;

    /** 活动次数记录主键 */
    @Excel(name = "活动次数记录主键")
    private String TASKNOTIFYID;

    /** 活动代码 */
    @Excel(name = "活动代码")
    private String DRAWCODE;

    /** 任务流水 */
    @Excel(name = "任务流水")
    private String TASKID;

    /** 用户标识 */
    @Excel(name = "用户标识")
    private String USERID;

    /** 赠送次数类型 */
    @Excel(name = "赠送次数类型")
    private String TYPE;

    /** 使用次数 */
    @Excel(name = "使用次数")
    private Long CONSUMENUMBER;

    /** 使用流水 */
    @Excel(name = "使用流水")
    private String TRANSEQNO;

    /** 使用主键 */
    @Excel(name = "使用主键")
    private String TRADEORDERID;

    /** 状态 */
    @Excel(name = "状态")
    private String STATE;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date CREATETIMESTAMP;

    /** 最后修改时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最后修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date LASTUPDATETIMESTAMP;

    public void setTASKCONSUMEID(String TASKCONSUMEID) 
    {
        this.TASKCONSUMEID = TASKCONSUMEID;
    }

    public String getTASKCONSUMEID() 
    {
        return TASKCONSUMEID;
    }
    public void setTASKNOTIFYID(String TASKNOTIFYID) 
    {
        this.TASKNOTIFYID = TASKNOTIFYID;
    }

    public String getTASKNOTIFYID() 
    {
        return TASKNOTIFYID;
    }
    public void setDRAWCODE(String DRAWCODE) 
    {
        this.DRAWCODE = DRAWCODE;
    }

    public String getDRAWCODE() 
    {
        return DRAWCODE;
    }
    public void setTASKID(String TASKID) 
    {
        this.TASKID = TASKID;
    }

    public String getTASKID() 
    {
        return TASKID;
    }
    public void setUSERID(String USERID) 
    {
        this.USERID = USERID;
    }

    public String getUSERID() 
    {
        return USERID;
    }
    public void setTYPE(String TYPE) 
    {
        this.TYPE = TYPE;
    }

    public String getTYPE() 
    {
        return TYPE;
    }
    public void setCONSUMENUMBER(Long CONSUMENUMBER) 
    {
        this.CONSUMENUMBER = CONSUMENUMBER;
    }

    public Long getCONSUMENUMBER() 
    {
        return CONSUMENUMBER;
    }
    public void setTRANSEQNO(String TRANSEQNO) 
    {
        this.TRANSEQNO = TRANSEQNO;
    }

    public String getTRANSEQNO() 
    {
        return TRANSEQNO;
    }
    public void setTRADEORDERID(String TRADEORDERID) 
    {
        this.TRADEORDERID = TRADEORDERID;
    }

    public String getTRADEORDERID() 
    {
        return TRADEORDERID;
    }
    public void setSTATE(String STATE) 
    {
        this.STATE = STATE;
    }

    public String getSTATE() 
    {
        return STATE;
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
            .append("TASKCONSUMEID", getTASKCONSUMEID())
            .append("TASKNOTIFYID", getTASKNOTIFYID())
            .append("DRAWCODE", getDRAWCODE())
            .append("TASKID", getTASKID())
            .append("USERID", getUSERID())
            .append("TYPE", getTYPE())
            .append("CONSUMENUMBER", getCONSUMENUMBER())
            .append("TRANSEQNO", getTRANSEQNO())
            .append("TRADEORDERID", getTRADEORDERID())
            .append("STATE", getSTATE())
            .append("CREATETIMESTAMP", getCREATETIMESTAMP())
            .append("LASTUPDATETIMESTAMP", getLASTUPDATETIMESTAMP())
            .toString();
    }
}

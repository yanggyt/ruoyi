package com.sinosoft.activity.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 活动次数记录信息对象 draw_task_notify
 * 
 * @author dy
 * @date 2021-03-26
 */
public class DrawTaskNotify extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 业务主键 */
    @Excel(name = "业务主键")
    private String TASKNOTIFYID;

    /** 活动代码 */
    @Excel(name = "活动代码")
    private String DRAWCODE;

    /** 任务流水 */
    @Excel(name = "任务流水")
    private String TASKID;

    /** 用户标识 */
    private String USERID;

    /** 赠送次数类型 */
    @Excel(name = "赠送次数类型")
    private String TYPE;

    /** 赠送次数 */
    @Excel(name = "赠送次数")
    private int ADDNUMBER;

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

    /** 可用次数 */
    @Excel(name = "可用次数")
    private int AVAILABLENUMBER;

    /** 账务日期 */
    private String CHECKINGDATE;

    /** 手机号 */
    @Excel(name = "手机号")
    private String PHONE;

    /** 请求类型 */
    private String TASKTYPE;

    /** 身份认证状态 */
    @Excel(name = "身份认证状态")
    private String VSTATE;

    /** 用户等级 */
    @Excel(name = "用户等级")
    private String ULEVEL;

    /** 请求来源 */
    @Excel(name = "请求来源")
    private String SOURCE;

    /** 是否白名单 */
    @Excel(name = "是否白名单")
    private String ISSPECIALFLAG;

    /** 请求标识 */
    private String REQUESTFLAG;

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
    public void setADDNUMBER(int ADDNUMBER)
    {
        this.ADDNUMBER = ADDNUMBER;
    }

    public int getADDNUMBER()
    {
        return ADDNUMBER;
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
    public void setAVAILABLENUMBER(int AVAILABLENUMBER)
    {
        this.AVAILABLENUMBER = AVAILABLENUMBER;
    }

    public int getAVAILABLENUMBER()
    {
        return AVAILABLENUMBER;
    }
    public void setCHECKINGDATE(String CHECKINGDATE) 
    {
        this.CHECKINGDATE = CHECKINGDATE;
    }

    public String getCHECKINGDATE() 
    {
        return CHECKINGDATE;
    }
    public void setPHONE(String PHONE) 
    {
        this.PHONE = PHONE;
    }

    public String getPHONE() 
    {
        return PHONE;
    }
    public void setTASKTYPE(String TASKTYPE) 
    {
        this.TASKTYPE = TASKTYPE;
    }

    public String getTASKTYPE() 
    {
        return TASKTYPE;
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
    public void setSOURCE(String SOURCE) 
    {
        this.SOURCE = SOURCE;
    }

    public String getSOURCE() 
    {
        return SOURCE;
    }
    public void setISSPECIALFLAG(String ISSPECIALFLAG) 
    {
        this.ISSPECIALFLAG = ISSPECIALFLAG;
    }

    public String getISSPECIALFLAG() 
    {
        return ISSPECIALFLAG;
    }
    public void setREQUESTFLAG(String REQUESTFLAG) 
    {
        this.REQUESTFLAG = REQUESTFLAG;
    }

    public String getREQUESTFLAG() 
    {
        return REQUESTFLAG;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("TASKNOTIFYID", getTASKNOTIFYID())
            .append("DRAWCODE", getDRAWCODE())
            .append("TASKID", getTASKID())
            .append("USERID", getUSERID())
            .append("TYPE", getTYPE())
            .append("ADDNUMBER", getADDNUMBER())
            .append("STATE", getSTATE())
            .append("CREATETIMESTAMP", getCREATETIMESTAMP())
            .append("LASTUPDATETIMESTAMP", getLASTUPDATETIMESTAMP())
            .append("AVAILABLENUMBER", getAVAILABLENUMBER())
            .append("CHECKINGDATE", getCHECKINGDATE())
            .append("PHONE", getPHONE())
            .append("TASKTYPE", getTASKTYPE())
            .append("VSTATE", getVSTATE())
            .append("ULEVEL", getULEVEL())
            .append("SOURCE", getSOURCE())
            .append("ISSPECIALFLAG", getISSPECIALFLAG())
            .append("REQUESTFLAG", getREQUESTFLAG())
            .toString();
    }
}

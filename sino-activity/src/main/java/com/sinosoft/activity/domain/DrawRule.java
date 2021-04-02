package com.sinosoft.activity.domain;
import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;


/**
 * 存储抽奖特殊规则对象 draw_rule
 *
 * @author ruoyi
 * @date 2021-03-25
 */
public class DrawRule extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 业务主键 */
    private String DRAWRULEID;

    /** 抽奖活动代码 */
    private String DRAWCODE;

    /** 首次抽奖配置标志 */
    private String FIRSTFLAG;

    /** 首次抽奖必中奖品编码 */
    private String FIRSTAWARDPRIZE;

    /** 日抽奖次数限制标志 */
    private String DAILYFLAG;

    /** 日抽奖限制开始时间 */
    private String DAILYSTARTTIME;

    /** 日抽奖限制结束时间 */
    private String DAILYENDTIME;

    /** 日抽奖限制次数 */
    private Long DAILYNUMBER;

    /** N次抽奖必中标志 */
    private String WILLDRAWFLAG;

    /** N次抽奖必中奖品 */
    private String WILLDRAWAWARDPRIZE;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date CREATETIMESTAMP;

    /** 最后修改时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date LASTUPDATETIMESTAMP;

    /** N次抽奖必中基数 */
    private Long WILLDRAWAWARDNUMBER;

    public String getDRAWCODE() {
        return DRAWCODE;
    }

    public void setDRAWCODE(String DRAWCODE) {
        this.DRAWCODE = DRAWCODE;
    }

    public Date getCREATETIMESTAMP() {
        return CREATETIMESTAMP;
    }

    public void setCREATETIMESTAMP(Date CREATETIMESTAMP) {
        this.CREATETIMESTAMP = CREATETIMESTAMP;
    }

    public Date getLASTUPDATETIMESTAMP() {
        return LASTUPDATETIMESTAMP;
    }

    public void setLASTUPDATETIMESTAMP(Date LASTUPDATETIMESTAMP) {
        this.LASTUPDATETIMESTAMP = LASTUPDATETIMESTAMP;
    }

    public void setDRAWRULEID(String DRAWRULEID)
    {
        this.DRAWRULEID = DRAWRULEID;
    }

    public String getDAILYSTARTTIME() {
        return DAILYSTARTTIME;
    }

    public void setDAILYSTARTTIME(String DAILYSTARTTIME) {
        this.DAILYSTARTTIME = DAILYSTARTTIME;
    }

    public String getDAILYENDTIME() {
        return DAILYENDTIME;
    }

    public void setDAILYENDTIME(String DAILYENDTIME) {
        this.DAILYENDTIME = DAILYENDTIME;
    }

    public String getDRAWRULEID()
    {
        return DRAWRULEID;
    }


    public void setFIRSTFLAG(String FIRSTFLAG)
    {
        this.FIRSTFLAG = FIRSTFLAG;
    }

    public String getFIRSTFLAG()
    {
        return FIRSTFLAG;
    }
    public void setFIRSTAWARDPRIZE(String FIRSTAWARDPRIZE)
    {
        this.FIRSTAWARDPRIZE = FIRSTAWARDPRIZE;
    }

    public String getFIRSTAWARDPRIZE()
    {
        return FIRSTAWARDPRIZE;
    }
    public void setDAILYFLAG(String DAILYFLAG)
    {
        this.DAILYFLAG = DAILYFLAG;
    }

    public String getDAILYFLAG()
    {
        return DAILYFLAG;
    }


    public void setDAILYNUMBER(Long DAILYNUMBER)
    {
        this.DAILYNUMBER = DAILYNUMBER;
    }

    public Long getDAILYNUMBER()
    {
        return DAILYNUMBER;
    }
    public void setWILLDRAWFLAG(String WILLDRAWFLAG)
    {
        this.WILLDRAWFLAG = WILLDRAWFLAG;
    }

    public String getWILLDRAWFLAG()
    {
        return WILLDRAWFLAG;
    }
    public void setWILLDRAWAWARDPRIZE(String WILLDRAWAWARDPRIZE)
    {
        this.WILLDRAWAWARDPRIZE = WILLDRAWAWARDPRIZE;
    }

    public String getWILLDRAWAWARDPRIZE()
    {
        return WILLDRAWAWARDPRIZE;
    }


    public void setWILLDRAWAWARDNUMBER(Long WILLDRAWAWARDNUMBER)
    {
        this.WILLDRAWAWARDNUMBER = WILLDRAWAWARDNUMBER;
    }

    public Long getWILLDRAWAWARDNUMBER()
    {
        return WILLDRAWAWARDNUMBER;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("DRAWRULEID", getDRAWRULEID())
                .append("FIRSTFLAG", getFIRSTFLAG())
                .append("FIRSTAWARDPRIZE", getFIRSTAWARDPRIZE())
                .append("DAILYFLAG", getDAILYFLAG())
                .append("DAILYSTARTTIME", getDAILYSTARTTIME())
                .append("DAILYENDTIME", getDAILYENDTIME())
                .append("DAILYNUMBER", getDAILYNUMBER())
                .append("WILLDRAWFLAG", getWILLDRAWFLAG())
                .append("WILLDRAWAWARDPRIZE", getWILLDRAWAWARDPRIZE())
                .append("WILLDRAWAWARDNUMBER", getWILLDRAWAWARDNUMBER())
                .toString();
    }
}
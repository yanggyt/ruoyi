package com.ruoyi.bps.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 快递订阅推送信息对象 exp_subs_push_resp
 * 
 * @author box
 * @date 2021-05-13
 */
public class ExpSubsPushResp extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** SID */
    private Long sid;

    /** 监控状态 */
    @Excel(name = "监控状态")
    private String status;

    /** 状态 */
    @Excel(name = "状态")
    private String billStatus;

    /** 监控状态消息 */
    @Excel(name = "监控状态消息")
    private String message;

    /** 快递公司编码是否出错 */
    @Excel(name = "快递公司编码是否出错")
    private String autoCheck;

    /** 原始快递公司编码 */
    @Excel(name = "原始快递公司编码")
    private String comOld;

    /** 修正快递公司编码 */
    @Excel(name = "修正快递公司编码")
    private String comNew;

    /** 当前快递消息 */
    @Excel(name = "当前快递消息")
    private String lastResultMessage;

    /** 当前快递单状态 */
    @Excel(name = "当前快递单状态")
    private String lastResultState;

    /** 通讯状态 */
    @Excel(name = "通讯状态")
    private String lastResulStatus;

    /** 快递单明细状态 */
    @Excel(name = "快递单明细状态")
    private String lastResultCondition;

    /** 是否签收 */
    @Excel(name = "是否签收")
    private String lastResultIsCheck;

    /** 快递公司编码 */
    @Excel(name = "快递公司编码")
    private String lastResultCom;

    /** 快递单号 */
    @Excel(name = "快递单号")
    private String lastResultNu;

    /** 快递流转信息 */
    @Excel(name = "快递流转信息")
    private String lastResultData;

    /** 目的国快递消息 */
    @Excel(name = "目的国快递消息")
    private String destResultMessage;

    /** 目的国快递单状态 */
    @Excel(name = "目的国快递单状态")
    private String destResultState;

    /** 目的国通讯状态 */
    @Excel(name = "目的国通讯状态")
    private String destResultStatus;

    /** 目的国快递单明细状态 */
    @Excel(name = "目的国快递单明细状态")
    private String destResultCondition;

    /** 目的国是否签收 */
    @Excel(name = "目的国是否签收")
    private String destResultIsCheck;

    /** 目的国快递公司编码 */
    @Excel(name = "目的国快递公司编码")
    private String destResultCom;

    /** 目的国快递单号 */
    @Excel(name = "目的国快递单号")
    private String destResultNu;

    /** 目的国快递流转信息 */
    @Excel(name = "目的国快递流转信息")
    private String destResultData;

    public void setSid(Long sid) 
    {
        this.sid = sid;
    }

    public Long getSid() 
    {
        return sid;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setBillStatus(String billStatus) 
    {
        this.billStatus = billStatus;
    }

    public String getBillStatus() 
    {
        return billStatus;
    }
    public void setMessage(String message) 
    {
        this.message = message;
    }

    public String getMessage() 
    {
        return message;
    }
    public void setAutoCheck(String autoCheck) 
    {
        this.autoCheck = autoCheck;
    }

    public String getAutoCheck() 
    {
        return autoCheck;
    }
    public void setComOld(String comOld) 
    {
        this.comOld = comOld;
    }

    public String getComOld() 
    {
        return comOld;
    }
    public void setComNew(String comNew) 
    {
        this.comNew = comNew;
    }

    public String getComNew() 
    {
        return comNew;
    }
    public void setLastResultMessage(String lastResultMessage) 
    {
        this.lastResultMessage = lastResultMessage;
    }

    public String getLastResultMessage() 
    {
        return lastResultMessage;
    }
    public void setLastResultState(String lastResultState) 
    {
        this.lastResultState = lastResultState;
    }

    public String getLastResultState() 
    {
        return lastResultState;
    }
    public void setLastResulStatus(String lastResulStatus) 
    {
        this.lastResulStatus = lastResulStatus;
    }

    public String getLastResulStatus() 
    {
        return lastResulStatus;
    }
    public void setLastResultCondition(String lastResultCondition) 
    {
        this.lastResultCondition = lastResultCondition;
    }

    public String getLastResultCondition() 
    {
        return lastResultCondition;
    }
    public void setLastResultIsCheck(String lastResultIsCheck) 
    {
        this.lastResultIsCheck = lastResultIsCheck;
    }

    public String getLastResultIsCheck() 
    {
        return lastResultIsCheck;
    }
    public void setLastResultCom(String lastResultCom) 
    {
        this.lastResultCom = lastResultCom;
    }

    public String getLastResultCom() 
    {
        return lastResultCom;
    }
    public void setLastResultNu(String lastResultNu) 
    {
        this.lastResultNu = lastResultNu;
    }

    public String getLastResultNu() 
    {
        return lastResultNu;
    }
    public void setLastResultData(String lastResultData) 
    {
        this.lastResultData = lastResultData;
    }

    public String getLastResultData() 
    {
        return lastResultData;
    }
    public void setDestResultMessage(String destResultMessage) 
    {
        this.destResultMessage = destResultMessage;
    }

    public String getDestResultMessage() 
    {
        return destResultMessage;
    }
    public void setDestResultState(String destResultState) 
    {
        this.destResultState = destResultState;
    }

    public String getDestResultState() 
    {
        return destResultState;
    }
    public void setDestResultStatus(String destResultStatus) 
    {
        this.destResultStatus = destResultStatus;
    }

    public String getDestResultStatus() 
    {
        return destResultStatus;
    }
    public void setDestResultCondition(String destResultCondition) 
    {
        this.destResultCondition = destResultCondition;
    }

    public String getDestResultCondition() 
    {
        return destResultCondition;
    }
    public void setDestResultIsCheck(String destResultIsCheck) 
    {
        this.destResultIsCheck = destResultIsCheck;
    }

    public String getDestResultIsCheck() 
    {
        return destResultIsCheck;
    }
    public void setDestResultCom(String destResultCom) 
    {
        this.destResultCom = destResultCom;
    }

    public String getDestResultCom() 
    {
        return destResultCom;
    }
    public void setDestResultNu(String destResultNu) 
    {
        this.destResultNu = destResultNu;
    }

    public String getDestResultNu() 
    {
        return destResultNu;
    }
    public void setDestResultData(String destResultData) 
    {
        this.destResultData = destResultData;
    }

    public String getDestResultData() 
    {
        return destResultData;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("sid", getSid())
            .append("status", getStatus())
            .append("billStatus", getBillStatus())
            .append("message", getMessage())
            .append("autoCheck", getAutoCheck())
            .append("comOld", getComOld())
            .append("comNew", getComNew())
            .append("lastResultMessage", getLastResultMessage())
            .append("lastResultState", getLastResultState())
            .append("lastResulStatus", getLastResulStatus())
            .append("lastResultCondition", getLastResultCondition())
            .append("lastResultIsCheck", getLastResultIsCheck())
            .append("lastResultCom", getLastResultCom())
            .append("lastResultNu", getLastResultNu())
            .append("lastResultData", getLastResultData())
            .append("destResultMessage", getDestResultMessage())
            .append("destResultState", getDestResultState())
            .append("destResultStatus", getDestResultStatus())
            .append("destResultCondition", getDestResultCondition())
            .append("destResultIsCheck", getDestResultIsCheck())
            .append("destResultCom", getDestResultCom())
            .append("destResultNu", getDestResultNu())
            .append("destResultData", getDestResultData())
            .toString();
    }
}

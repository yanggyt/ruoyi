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

    /** 最后更新时间 */
    @Excel(name = "最后更新时间")
    private String lastResponseTime;

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(String billStatus) {
        this.billStatus = billStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAutoCheck() {
        return autoCheck;
    }

    public void setAutoCheck(String autoCheck) {
        this.autoCheck = autoCheck;
    }

    public String getComOld() {
        return comOld;
    }

    public void setComOld(String comOld) {
        this.comOld = comOld;
    }

    public String getComNew() {
        return comNew;
    }

    public void setComNew(String comNew) {
        this.comNew = comNew;
    }

    public String getLastResultMessage() {
        return lastResultMessage;
    }

    public void setLastResultMessage(String lastResultMessage) {
        this.lastResultMessage = lastResultMessage;
    }

    public String getLastResultState() {
        return lastResultState;
    }

    public void setLastResultState(String lastResultState) {
        this.lastResultState = lastResultState;
    }

    public String getLastResulStatus() {
        return lastResulStatus;
    }

    public void setLastResulStatus(String lastResulStatus) {
        this.lastResulStatus = lastResulStatus;
    }

    public String getLastResultCondition() {
        return lastResultCondition;
    }

    public void setLastResultCondition(String lastResultCondition) {
        this.lastResultCondition = lastResultCondition;
    }

    public String getLastResultIsCheck() {
        return lastResultIsCheck;
    }

    public void setLastResultIsCheck(String lastResultIsCheck) {
        this.lastResultIsCheck = lastResultIsCheck;
    }

    public String getLastResultCom() {
        return lastResultCom;
    }

    public void setLastResultCom(String lastResultCom) {
        this.lastResultCom = lastResultCom;
    }

    public String getLastResultNu() {
        return lastResultNu;
    }

    public void setLastResultNu(String lastResultNu) {
        this.lastResultNu = lastResultNu;
    }

    public String getLastResultData() {
        return lastResultData;
    }

    public void setLastResultData(String lastResultData) {
        this.lastResultData = lastResultData;
    }

    public String getDestResultMessage() {
        return destResultMessage;
    }

    public void setDestResultMessage(String destResultMessage) {
        this.destResultMessage = destResultMessage;
    }

    public String getDestResultState() {
        return destResultState;
    }

    public void setDestResultState(String destResultState) {
        this.destResultState = destResultState;
    }

    public String getDestResultStatus() {
        return destResultStatus;
    }

    public void setDestResultStatus(String destResultStatus) {
        this.destResultStatus = destResultStatus;
    }

    public String getDestResultCondition() {
        return destResultCondition;
    }

    public void setDestResultCondition(String destResultCondition) {
        this.destResultCondition = destResultCondition;
    }

    public String getDestResultIsCheck() {
        return destResultIsCheck;
    }

    public void setDestResultIsCheck(String destResultIsCheck) {
        this.destResultIsCheck = destResultIsCheck;
    }

    public String getDestResultCom() {
        return destResultCom;
    }

    public void setDestResultCom(String destResultCom) {
        this.destResultCom = destResultCom;
    }

    public String getDestResultNu() {
        return destResultNu;
    }

    public void setDestResultNu(String destResultNu) {
        this.destResultNu = destResultNu;
    }

    public String getDestResultData() {
        return destResultData;
    }

    public void setDestResultData(String destResultData) {
        this.destResultData = destResultData;
    }

    public String getLastResponseTime() {
        return lastResponseTime;
    }

    public void setLastResponseTime(String lastResponseTime) {
        this.lastResponseTime = lastResponseTime;
    }

    @Override
    public String toString() {
        return "ExpSubsPushResp{" +
                "sid=" + sid +
                ", status='" + status + '\'' +
                ", billStatus='" + billStatus + '\'' +
                ", message='" + message + '\'' +
                ", autoCheck='" + autoCheck + '\'' +
                ", comOld='" + comOld + '\'' +
                ", comNew='" + comNew + '\'' +
                ", lastResultMessage='" + lastResultMessage + '\'' +
                ", lastResultState='" + lastResultState + '\'' +
                ", lastResulStatus='" + lastResulStatus + '\'' +
                ", lastResultCondition='" + lastResultCondition + '\'' +
                ", lastResultIsCheck='" + lastResultIsCheck + '\'' +
                ", lastResultCom='" + lastResultCom + '\'' +
                ", lastResultNu='" + lastResultNu + '\'' +
                ", lastResultData='" + lastResultData + '\'' +
                ", destResultMessage='" + destResultMessage + '\'' +
                ", destResultState='" + destResultState + '\'' +
                ", destResultStatus='" + destResultStatus + '\'' +
                ", destResultCondition='" + destResultCondition + '\'' +
                ", destResultIsCheck='" + destResultIsCheck + '\'' +
                ", destResultCom='" + destResultCom + '\'' +
                ", destResultNu='" + destResultNu + '\'' +
                ", destResultData='" + destResultData + '\'' +
                ", lastResponseTime='" + lastResponseTime + '\'' +
                '}';
    }
}

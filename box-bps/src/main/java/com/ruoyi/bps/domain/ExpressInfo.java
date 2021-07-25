package com.ruoyi.bps.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 快递信息对象 expressInfo
 * 
 * @author box
 * @date 2021-05-06
 */
public class ExpressInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 消息 */
    private String sid;

    /** 消息 */
    @Excel(name = "消息",type= Excel.Type.EXPORT)
    private String message;


    /** 出货单号 */
    @Excel(name = "出货单号")
    private String deliveryNum;

    /** 快递单号 */
    @Excel(name = "快递单号")
    private String nu;

    /** 签收状态 */
    @Excel(name = "签收状态",type= Excel.Type.EXPORT,readConverterExp = "0=未签收,1=已签收")
    private String ischeck;

    /** 快递公司 */
    @Excel(name = "快递公司",type= Excel.Type.EXPORT,dictType = "express_company")
    private String com;

    /** 通信状态 */
    //@Excel(name = "通信状态",type= Excel.Type.EXPORT)
    private String status;

    /** 运单详情 */
    @Excel(name = "运单详情",type= Excel.Type.EXPORT)
    private String data;

    /** 当前状态 */
    @Excel(name = "当前状态",type= Excel.Type.EXPORT,dictType = "express_stats")
    private String state;

    /** 状态标志 */
    //@Excel(name = "状态标志",type= Excel.Type.EXPORT)
    private String condition;

    /** 路由信息 */
    //@Excel(name = "路由信息",type= Excel.Type.EXPORT)
    private String routeInfo;

    /** 返回码 */
    //@Excel(name = "返回码",type= Excel.Type.EXPORT)
    private String returnCode;

    /** 返回结果 */
    //@Excel(name = "返回结果",type= Excel.Type.EXPORT)
    private String result;

    /** 电话号码 */
    //@Excel(name = "电话号码",type= Excel.Type.EXPORT)
    private String phone;

    /** 揽收时间*/
    @Excel(name = "揽收时间",type= Excel.Type.EXPORT)
    private String collectTime;

    /** 签收时间*/
    @Excel(name = "签收时间",type= Excel.Type.EXPORT)
    private String singedTime;

    /** 最后更新时间*/
    @Excel(name = "最后更新时间",type= Excel.Type.EXPORT)
    private String lastUpdateTime;

    /** 查询时间*/
    @Excel(name = "查询时间",type= Excel.Type.EXPORT)
    private String queryTime;

    /** 查询人*/
    @Excel(name = "查询人",type= Excel.Type.EXPORT)
    private String queryUserName;

    /** 查询ID*/
    private String queryId;

    /** 查询类型*/
    private String queryType;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDeliveryNum() {
        return deliveryNum;
    }

    public void setDeliveryNum(String deliveryNum) {
        this.deliveryNum = deliveryNum;
    }

    public String getNu() {
        return nu;
    }

    public void setNu(String nu) {
        this.nu = nu;
    }

    public String getIscheck() {
        return ischeck;
    }

    public void setIscheck(String ischeck) {
        this.ischeck = ischeck;
    }

    public String getCom() {
        return com;
    }

    public void setCom(String com) {
        this.com = com;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getRouteInfo() {
        return routeInfo;
    }

    public void setRouteInfo(String routeInfo) {
        this.routeInfo = routeInfo;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(String collectTime) {
        this.collectTime = collectTime;
    }

    public String getSingedTime() {
        return singedTime;
    }

    public void setSingedTime(String singedTime) {
        this.singedTime = singedTime;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getQueryTime() {
        return queryTime;
    }

    public void setQueryTime(String queryTime) {
        this.queryTime = queryTime;
    }

    public String getQueryUserName() {
        return queryUserName;
    }

    public void setQueryUserName(String queryUserName) {
        this.queryUserName = queryUserName;
    }

    public String getQueryId() {
        return queryId;
    }

    public void setQueryId(String queryId) {
        this.queryId = queryId;
    }

    public String getQueryType() {
        return queryType;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }

    @Override
    public String toString() {
        return "ExpressInfo{" +
                "sid='" + sid + '\'' +
                ", message='" + message + '\'' +
                ", deliveryNum='" + deliveryNum + '\'' +
                ", nu='" + nu + '\'' +
                ", ischeck='" + ischeck + '\'' +
                ", com='" + com + '\'' +
                ", status='" + status + '\'' +
                ", data='" + data + '\'' +
                ", state='" + state + '\'' +
                ", condition='" + condition + '\'' +
                ", routeInfo='" + routeInfo + '\'' +
                ", returnCode='" + returnCode + '\'' +
                ", result='" + result + '\'' +
                ", phone='" + phone + '\'' +
                ", collectTime='" + collectTime + '\'' +
                ", singedTime='" + singedTime + '\'' +
                ", lastUpdateTime='" + lastUpdateTime + '\'' +
                ", queryTime='" + queryTime + '\'' +
                ", queryUserName='" + queryUserName + '\'' +
                ", queryId='" + queryId + '\'' +
                ", queryType='" + queryType + '\'' +
                '}';
    }
}

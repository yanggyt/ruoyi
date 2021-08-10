package com.ruoyi.bps.domain;

public class ExpTopgpLog {
    Long Sid;
    Long requestId;
    String requestType;
    String expressNumber;
    String deliveryNumber;
    String requestStr;
    String requestTime;
    String responseCode;
    String responseStr;

    public Long getSid() {
        return Sid;
    }

    public void setSid(Long sid) {
        Sid = sid;
    }

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getExpressNumber() {
        return expressNumber;
    }

    public void setExpressNumber(String expressNumber) {
        this.expressNumber = expressNumber;
    }

    public String getDeliveryNumber() {
        return deliveryNumber;
    }

    public void setDeliveryNumber(String deliveryNumber) {
        this.deliveryNumber = deliveryNumber;
    }

    public String getRequestStr() {
        return requestStr;
    }

    public void setRequestStr(String requestStr) {
        this.requestStr = requestStr;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseStr() {
        return responseStr;
    }

    public void setResponseStr(String responseStr) {
        this.responseStr = responseStr;
    }

    @Override
    public String toString() {
        return "ExpSubscribeTopgpLog{" +
                "Sid=" + Sid +
                ", requestId='" + requestId + '\'' +
                ", requestType='" + requestType + '\'' +
                ", expressNumber='" + expressNumber + '\'' +
                ", deliveryNumber='" + deliveryNumber + '\'' +
                ", requestStr='" + requestStr + '\'' +
                ", requestTime='" + requestTime + '\'' +
                ", responseCode='" + responseCode + '\'' +
                ", responseStr='" + responseStr + '\'' +
                '}';
    }
}

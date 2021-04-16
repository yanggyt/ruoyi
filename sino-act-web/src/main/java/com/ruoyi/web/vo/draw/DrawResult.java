package com.ruoyi.web.vo.draw;

import com.ruoyi.web.vo.Result;

public class DrawResult extends Result {
    private String prizeCode;
    private String prizeName;
    private String prizeLevel;
    private String prizeType;
    private String displayOrder;
    private String cue;
    private String available;
    private String extId;
    private String gatewayFlow;
    private String gateWayTime;
    private String drawCode;
    private String source;
    private String userId;
    private String result;

    public String getGateWayTime() {
        return gateWayTime;
    }

    public void setGateWayTime(String gateWayTime) {
        this.gateWayTime = gateWayTime;
    }

    public String getDrawCode() {
        return drawCode;
    }

    public void setDrawCode(String drawCode) {
        this.drawCode = drawCode;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPrizeCode() {
        return prizeCode;
    }

    public void setPrizeCode(String prizeCode) {
        this.prizeCode = prizeCode;
    }

    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName;
    }

    public String getPrizeLevel() {
        return prizeLevel;
    }

    public void setPrizeLevel(String prizeLevel) {
        this.prizeLevel = prizeLevel;
    }

    public String getPrizeType() {
        return prizeType;
    }

    public void setPrizeType(String prizeType) {
        this.prizeType = prizeType;
    }

    public String getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(String displayOrder) {
        this.displayOrder = displayOrder;
    }

    public String getCue() {
        return cue;
    }

    public void setCue(String cue) {
        this.cue = cue;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getExtId() {
        return extId;
    }

    public void setExtId(String extId) {
        this.extId = extId;
    }

    public String getGatewayFlow() {
        return gatewayFlow;
    }

    public void setGatewayFlow(String gatewayFlow) {
        this.gatewayFlow = gatewayFlow;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}

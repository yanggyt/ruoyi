package com.ruoyi.web.vo.draw;

public class Prize {
    private String prizeCode;
    private String prizeType;
    private String prizeName;
    private String status;
    private String drawTime;
    private String userName;
    private String mobile;
    /**
     * 外部奖品标识
     */
    private String extId;
    /**
     * 抽奖记录流水标识
     */
    private String gatewayFlow;
    private String prizeImg;

    public String getPrizeCode() {
        return prizeCode;
    }

    public void setPrizeCode(String prizeCode) {
        this.prizeCode = prizeCode;
    }

    public String getPrizeType() {
        return prizeType;
    }

    public void setPrizeType(String prizeType) {
        this.prizeType = prizeType;
    }

    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDrawTime() {
        return drawTime;
    }

    public void setDrawTime(String drawTime) {
        this.drawTime = drawTime;
    }

    public String getExtId() {
        return extId;
    }

    public void setExtId(String extId) {
        this.extId = extId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGatewayFlow() {
        return gatewayFlow;
    }

    public void setGatewayFlow(String gatewayFlow) {
        this.gatewayFlow = gatewayFlow;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPrizeImg() {
        return prizeImg;
    }

    public void setPrizeImg(String prizeImg) {
        this.prizeImg = prizeImg;
    }
}

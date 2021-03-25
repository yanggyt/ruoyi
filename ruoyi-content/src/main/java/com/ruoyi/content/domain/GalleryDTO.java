package com.ruoyi.content.domain;

import java.util.List;

public class GalleryDTO {

    private Integer picId;
    private String imgUrl;
    private String picAdId;
    private String companyId;
    private String picState;
    private List<String> codeName;
    private String createDate;
    private String createTime;

    public Integer getPicId() {
        return picId;
    }

    public void setPicId(Integer picId) {
        this.picId = picId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getPicAdId() {
        return picAdId;
    }

    public void setPicAdId(String picAdId) {
        this.picAdId = picAdId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getPicState() {
        return picState;
    }

    public void setPicState(String picState) {
        this.picState = picState;
    }

    public List<String> getCodeName() {
        return codeName;
    }

    public void setCodeName(List<String> codeName) {
        this.codeName = codeName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

}

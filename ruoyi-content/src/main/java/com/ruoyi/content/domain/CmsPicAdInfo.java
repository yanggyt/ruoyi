package com.ruoyi.content.domain;

import com.ruoyi.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 图片广告对象 cms_pic_ad_info
 *
 * @author liushenlu
 * @date 2021-03-25
 */
public class CmsPicAdInfo {

    private static final long serialVersionUID = 1L;

    /**
     * 广告编号
     */
    private Long picAdId;

    /**
     * 广告类型
     */
    @Excel(name = "广告类型")
    private String picAdType;

    /**
     * 产品标题
     */
    @Excel(name = "产品标题")
    private String picAdTitle;

    /**
     * 广告特有名称
     */
    @Excel(name = "广告特有名称")
    private String picAdName;

    /**
     * 广告链接
     */
    @Excel(name = "广告链接")
    private String picAdUrl;

    /**
     * 广告状态，0：可展示，1：不可展示
     */
    @Excel(name = "广告状态，0：可展示，1：不可展示")
    private String picAdState;

    /**
     * 公司id
     */
    @Excel(name = "公司id")
    private String companyId;

    /**
     *
     */
    @Excel(name = "")
    private String createDate;

    /**
     *
     */
    @Excel(name = "")
    private String createTime;

    /**
     *
     */
    @Excel(name = "")
    private String createUser;

    /**
     *
     */
    @Excel(name = "")
    private String updateUser;

    /**
     *
     */
    @Excel(name = "")
    private String updateDate;

    /**
     *
     */
    @Excel(name = "")
    private String updateTime;

    public void setPicAdId(Long picAdId) {
        this.picAdId = picAdId;
    }

    public Long getPicAdId() {
        return picAdId;
    }

    public void setPicAdType(String picAdType) {
        this.picAdType = picAdType;
    }

    public String getPicAdType() {
        return picAdType;
    }

    public void setPicAdTitle(String picAdTitle) {
        this.picAdTitle = picAdTitle;
    }

    public String getPicAdTitle() {
        return picAdTitle;
    }

    public void setPicAdName(String picAdName) {
        this.picAdName = picAdName;
    }

    public String getPicAdName() {
        return picAdName;
    }

    public void setPicAdUrl(String picAdUrl) {
        this.picAdUrl = picAdUrl;
    }

    public String getPicAdUrl() {
        return picAdUrl;
    }

    public void setPicAdState(String picAdState) {
        this.picAdState = picAdState;
    }

    public String getPicAdState() {
        return picAdState;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("picAdId", getPicAdId())
                .append("picAdType", getPicAdType())
                .append("picAdTitle", getPicAdTitle())
                .append("picAdName", getPicAdName())
                .append("picAdUrl", getPicAdUrl())
                .append("picAdState", getPicAdState())
                .append("companyId", getCompanyId())
                .append("createDate", getCreateDate())
                .append("createTime", getCreateTime())
                .append("createUser", getCreateUser())
                .append("updateUser", getUpdateUser())
                .append("updateDate", getUpdateDate())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}

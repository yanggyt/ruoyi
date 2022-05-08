package com.wuzhen.system.domain;

import com.wuzhen.common.annotation.Excel;
import com.wuzhen.common.annotation.Excel.ColumnType;
import com.wuzhen.common.core.domain.BaseEntity;

/**
 * 广告信息 Advert_info
 *
 * @author zhengzheng
 */
public class AdvertInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 广告ID
     */
    @Excel(name = "广告ID", cellType = ColumnType.NUMERIC)
    private Long id;


    /**
     * 广告标题
     */
    @Excel(name = "广告标题")
    private String bannerTitle;


    /**
     * 跳转类型
     */
    @Excel(name = "跳转类型")
    private String bannerType;



    private String bannerPicUrl;


    /**
     * 广告跳转地址
     */
    @Excel(name = "广告跳转地址")
    private String address;


    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBannerTitle() {
        return bannerTitle;
    }

    public void setBannerTitle(String bannerTitle) {
        this.bannerTitle = bannerTitle;
    }

    public String getBannerType() {
        return bannerType;
    }

    public void setBannerType(String bannerType) {
        this.bannerType = bannerType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getBannerPicUrl() {
        return bannerPicUrl;
    }

    public void setBannerPicUrl(String bannerPicUrl) {
        this.bannerPicUrl = bannerPicUrl;
    }

    @Override
    public String toString() {
        return "AdvertInfo{" +
                "id=" + id +
                ", bannerTitle='" + bannerTitle + '\'' +
                ", bannerType='" + bannerType + '\'' +
                ", address='" + address + '\'' +
                ", delFlag='" + delFlag + '\'' +
                '}';
    }
}

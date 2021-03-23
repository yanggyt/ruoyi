package com.ruoyi.content.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 文章广告对象 cms_article_ad_info
 * 
 * @author ruoyi
 * @date 2021-03-23
 */
public class CmsArticleAdInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long adId;

    /** 公司id */
    @Excel(name = "公司id")
    private String companyId;

    /** 广告类型 */
    @Excel(name = "广告类型")
    private String adType;

    /** 广告类型名 */
    @Excel(name = "广告类型名")
    private String adTypeName;

    /** 广告跳转地址 */
    @Excel(name = "广告跳转地址")
    private String adLinkUrl;

    /** 广告标题 */
    @Excel(name = "广告标题")
    private String adTitle;

    /** 广告名称 */
    @Excel(name = "广告名称")
    private String adName;

    /** 广告简介 */
    @Excel(name = "广告简介")
    private String adSummary;

    /** 广告背景颜色 */
    @Excel(name = "广告背景颜色")
    private String adColorType;

    /** 广告图片地址 */
    @Excel(name = "广告图片地址")
    private String adImageUrl;

    /** 广告状态  0：展示  1：不可用 */
    @Excel(name = "广告状态  0：展示  1：不可用")
    private String adState;

    /**  */
    @Excel(name = "")
    private String createDate;

    /** 广告创建者 */
    @Excel(name = "广告创建者")
    private String createUser;

    /**  */
    @Excel(name = "")
    private String updateUser;

    /**  */
    @Excel(name = "")
    private String updateDate;

    public void setAdId(Long adId) 
    {
        this.adId = adId;
    }

    public Long getAdId() 
    {
        return adId;
    }
    public void setCompanyId(String companyId) 
    {
        this.companyId = companyId;
    }

    public String getCompanyId() 
    {
        return companyId;
    }
    public void setAdType(String adType) 
    {
        this.adType = adType;
    }

    public String getAdType() 
    {
        return adType;
    }
    public void setAdTypeName(String adTypeName) 
    {
        this.adTypeName = adTypeName;
    }

    public String getAdTypeName() 
    {
        return adTypeName;
    }
    public void setAdLinkUrl(String adLinkUrl) 
    {
        this.adLinkUrl = adLinkUrl;
    }

    public String getAdLinkUrl() 
    {
        return adLinkUrl;
    }
    public void setAdTitle(String adTitle) 
    {
        this.adTitle = adTitle;
    }

    public String getAdTitle() 
    {
        return adTitle;
    }
    public void setAdName(String adName) 
    {
        this.adName = adName;
    }

    public String getAdName() 
    {
        return adName;
    }
    public void setAdSummary(String adSummary) 
    {
        this.adSummary = adSummary;
    }

    public String getAdSummary() 
    {
        return adSummary;
    }
    public void setAdColorType(String adColorType) 
    {
        this.adColorType = adColorType;
    }

    public String getAdColorType() 
    {
        return adColorType;
    }
    public void setAdImageUrl(String adImageUrl) 
    {
        this.adImageUrl = adImageUrl;
    }

    public String getAdImageUrl() 
    {
        return adImageUrl;
    }
    public void setAdState(String adState) 
    {
        this.adState = adState;
    }

    public String getAdState() 
    {
        return adState;
    }
    public void setCreateDate(String createDate) 
    {
        this.createDate = createDate;
    }

    public String getCreateDate() 
    {
        return createDate;
    }
    public void setCreateUser(String createUser) 
    {
        this.createUser = createUser;
    }

    public String getCreateUser() 
    {
        return createUser;
    }
    public void setUpdateUser(String updateUser) 
    {
        this.updateUser = updateUser;
    }

    public String getUpdateUser() 
    {
        return updateUser;
    }
    public void setUpdateDate(String updateDate) 
    {
        this.updateDate = updateDate;
    }

    public String getUpdateDate() 
    {
        return updateDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("adId", getAdId())
            .append("companyId", getCompanyId())
            .append("adType", getAdType())
            .append("adTypeName", getAdTypeName())
            .append("adLinkUrl", getAdLinkUrl())
            .append("adTitle", getAdTitle())
            .append("adName", getAdName())
            .append("adSummary", getAdSummary())
            .append("adColorType", getAdColorType())
            .append("adImageUrl", getAdImageUrl())
            .append("adState", getAdState())
            .append("createDate", getCreateDate())
            .append("createTime", getCreateTime())
            .append("createUser", getCreateUser())
            .append("updateUser", getUpdateUser())
            .append("updateDate", getUpdateDate())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}

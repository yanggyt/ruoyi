package com.sinosoft.activity.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 活动配置对象 act_config
 * 
 * @author dy
 * @date 2021-04-08
 */
public class ActConfig extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Integer id;

    /** 页面风格 */
    @Excel(name = "页面风格")
    private Integer pageStyle;

    /** 活动类型 */
    @Excel(name = "活动类型")
    private Integer actType;

    /** 活动编码 */
    @Excel(name = "活动编码")
    private String actCode;

    /** 活动名称 */
    @Excel(name = "活动名称")
    private String actName;

    /** 分享标题 */
    @Excel(name = "分享标题")
    private String shareTitle;

    /** 分享描述 */
    @Excel(name = "分享描述")
    private String shareDesc;

    /** 分享链接 */
    @Excel(name = "分享链接")
    private String shareLink;

    /** 分享图标 */
    @Excel(name = "分享图标")
    private String shareImg;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setPageStyle(Integer pageStyle) 
    {
        this.pageStyle = pageStyle;
    }

    public Integer getPageStyle() 
    {
        return pageStyle;
    }
    public void setActType(Integer actType) 
    {
        this.actType = actType;
    }

    public Integer getActType() 
    {
        return actType;
    }
    public void setActCode(String actCode) 
    {
        this.actCode = actCode;
    }

    public String getActCode() 
    {
        return actCode;
    }
    public void setActName(String actName) 
    {
        this.actName = actName;
    }

    public String getActName() 
    {
        return actName;
    }
    public void setShareTitle(String shareTitle) 
    {
        this.shareTitle = shareTitle;
    }

    public String getShareTitle() 
    {
        return shareTitle;
    }
    public void setShareDesc(String shareDesc) 
    {
        this.shareDesc = shareDesc;
    }

    public String getShareDesc() 
    {
        return shareDesc;
    }
    public void setShareLink(String shareLink) 
    {
        this.shareLink = shareLink;
    }

    public String getShareLink() 
    {
        return shareLink;
    }
    public void setShareImg(String shareImg) 
    {
        this.shareImg = shareImg;
    }

    public String getShareImg() 
    {
        return shareImg;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("pageStyle", getPageStyle())
            .append("actType", getActType())
            .append("actCode", getActCode())
            .append("actName", getActName())
            .append("shareTitle", getShareTitle())
            .append("shareDesc", getShareDesc())
            .append("shareLink", getShareLink())
            .append("shareImg", getShareImg())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}

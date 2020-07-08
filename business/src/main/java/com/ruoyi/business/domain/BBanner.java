package com.ruoyi.business.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 轮播图对象 b_banner
 * 
 * @author anjie
 * @date 2020-06-21
 */
public class BBanner extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 描述 */
    @Excel(name = "描述")
    private String bannerDescribe;

    /** 文件路径 */
    @Excel(name = "文件路径")
    private String bannerFile;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setBannerDescribe(String bannerDescribe) 
    {
        this.bannerDescribe = bannerDescribe;
    }

    public String getBannerDescribe() 
    {
        return bannerDescribe;
    }
    public void setBannerFile(String bannerFile) 
    {
        this.bannerFile = bannerFile;
    }

    public String getBannerFile() 
    {
        return bannerFile;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("bannerDescribe", getBannerDescribe())
            .append("bannerFile", getBannerFile())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}

package com.ruoyi.business.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 产品分类对象 biz_product_type
 * 
 * @author ruoyi
 * @date 2020-09-05
 */
public class BizProductType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 产品分类ID */
    private Long id;

    /** 产品分类编码 */
    @Excel(name = "产品分类编码")
    private String productTypeCode;

    /** 产品分类名称 */
    @Excel(name = "产品分类名称")
    private String productTypeName;

    /** 附件地址 */
    private String imageUrl;

    /** 排序 */
    @Excel(name = "排序")
    private Long sort;

    /** 是否禁用：0-否，1-是 */
    @Excel(name = "是否禁用：0-否，1-是")
    private Integer isEnable;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProductTypeCode(String productTypeCode)
    {
        this.productTypeCode = productTypeCode;
    }

    public String getProductTypeCode() 
    {
        return productTypeCode;
    }
    public void setProductTypeName(String productTypeName) 
    {
        this.productTypeName = productTypeName;
    }

    public String getProductTypeName() 
    {
        return productTypeName;
    }
    public void setImageUrl(String imageUrl) 
    {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() 
    {
        return imageUrl;
    }
    public void setSort(Long sort) 
    {
        this.sort = sort;
    }

    public Long getSort() 
    {
        return sort;
    }
    public void setIsEnable(Integer isEnable) 
    {
        this.isEnable = isEnable;
    }

    public Integer getIsEnable() 
    {
        return isEnable;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("productTypeCode", getProductTypeCode())
            .append("productTypeName", getProductTypeName())
            .append("imageUrl", getImageUrl())
            .append("sort", getSort())
            .append("isEnable", getIsEnable())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}

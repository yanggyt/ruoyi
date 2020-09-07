package com.ruoyi.business.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 产品图片对象 biz_product_image
 * 
 * @author ruoyi
 * @date 2020-09-06
 */
public class BizProductImage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 产品图片ID */
    private Long productImageId;

    /** 产品ID */
    private Long productId;

    /** 附件类型：0-主图，1-详情图，2-轮播图 */
    private Integer imageType;

    /** 附件地址 */
    private String imageUrl;


    public void setProductImageId(Long productImageId)
    {
        this.productImageId = productImageId;
    }

    public Long getProductImageId() 
    {
        return productImageId;
    }
    public void setProductId(Long productId) 
    {
        this.productId = productId;
    }

    public Long getProductId() 
    {
        return productId;
    }
    public void setImageType(Integer imageType) 
    {
        this.imageType = imageType;
    }

    public Integer getImageType() {
        return imageType;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl()
    {
        return imageUrl;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("productImageId", getProductImageId())
            .append("productId", getProductId())
            .append("imageUrl", getImageUrl())
            .toString();
    }
}

package com.ruoyi.business.domain;

import java.util.List;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 产品对象 biz_product
 * 
 * @author ruoyi
 * @date 2020-09-06
 */
public class BizProduct extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 产品ID */
    private Long productId;

    /** 产品编码 */
    @Excel(name = "产品编码")
    private String productCode;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String productName;

    /** 产品分类ID */
    @Excel(name = "产品分类ID")
    private Long productTypeId;

    /** 产品类型 */
    @Excel(name = "产品类型")
    private Integer productClass;

    /** 产品单价 */
    @Excel(name = "产品单价")
    private Long amount;

    /** 排序 */
    @Excel(name = "排序")
    private Long sort;

    /** 是否上架 */
    @Excel(name = "是否上架")
    private Integer onlineStatus;

    /** 上架时间 */
    @Excel(name = "上架时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date onlineTime;

    /** 下架时间 */
    private Date offlineTime;

    /** 产品图片信息 */
    private String mainImage;
    private List<String> detailImages;
    private List<String> loopImages;

    public void setProductId(Long productId) 
    {
        this.productId = productId;
    }

    public Long getProductId() 
    {
        return productId;
    }
    public void setProductCode(String productCode) 
    {
        this.productCode = productCode;
    }

    public String getProductCode() 
    {
        return productCode;
    }
    public void setProductName(String productName) 
    {
        this.productName = productName;
    }

    public String getProductName() 
    {
        return productName;
    }
    public void setProductTypeId(Long productTypeId) 
    {
        this.productTypeId = productTypeId;
    }

    public Long getProductTypeId() 
    {
        return productTypeId;
    }
    public void setProductClass(Integer productClass) 
    {
        this.productClass = productClass;
    }

    public Integer getProductClass() 
    {
        return productClass;
    }
    public void setAmount(Long amount) 
    {
        this.amount = amount;
    }

    public Long getAmount() 
    {
        return amount;
    }
    public void setSort(Long sort) 
    {
        this.sort = sort;
    }

    public Long getSort() 
    {
        return sort;
    }
    public void setOnlineStatus(Integer onlineStatus) 
    {
        this.onlineStatus = onlineStatus;
    }

    public Integer getOnlineStatus() 
    {
        return onlineStatus;
    }
    public void setOnlineTime(Date onlineTime) 
    {
        this.onlineTime = onlineTime;
    }

    public Date getOnlineTime() 
    {
        return onlineTime;
    }
    public void setOfflineTime(Date offlineTime) 
    {
        this.offlineTime = offlineTime;
    }

    public Date getOfflineTime() 
    {
        return offlineTime;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public List<String> getDetailImages() {
        return detailImages;
    }

    public void setDetailImages(List<String> detailImages) {
        this.detailImages = detailImages;
    }

    public List<String> getLoopImages() {
        return loopImages;
    }

    public void setLoopImages(List<String> loopImages) {
        this.loopImages = loopImages;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("productId", getProductId())
            .append("productCode", getProductCode())
            .append("productName", getProductName())
            .append("productTypeId", getProductTypeId())
            .append("productClass", getProductClass())
            .append("amount", getAmount())
            .append("sort", getSort())
            .append("onlineStatus", getOnlineStatus())
            .append("onlineTime", getOnlineTime())
            .append("offlineTime", getOfflineTime())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}

package com.ruoyi.province.platform.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 商品价格带对象 platf_item_price_band
 * 
 * @author dalin
 * @date 2020-12-24
 */
public class ItemPriceBand extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long priceBandId;

    /** 名称 */
    @Excel(name = "名称")
    private String priceBandName;

    /** 单据号 */
    @Excel(name = "单据号")
    private String docNum;

    /** 上限 */
    @Excel(name = "上限")
    private BigDecimal priceBandUpper;

    /** 下限 */
    @Excel(name = "下限")
    private BigDecimal priceBandLower;

    /** 资料状态 */
    @Excel(name = "资料状态")
    private String status;

    /** 删除标志 */
    private String delFlag;

    /** 制单人 */
    @Excel(name = "制单人")
    private String createByuserName;

    /** 修改人 */
    @Excel(name = "修改人")
    private String updateByuserName;

    public Long getPriceBandId() {
        return priceBandId;
    }

    public void setPriceBandId(Long priceBandId) {
        this.priceBandId = priceBandId;
    }

    public void setPriceBandName(String priceBandName)
    {
        this.priceBandName = priceBandName;
    }

    public String getPriceBandName() 
    {
        return priceBandName;
    }
    public void setDocNum(String docNum) 
    {
        this.docNum = docNum;
    }

    public String getDocNum() 
    {
        return docNum;
    }
    public void setPriceBandUpper(BigDecimal priceBandUpper) 
    {
        this.priceBandUpper = priceBandUpper;
    }

    public BigDecimal getPriceBandUpper() 
    {
        return priceBandUpper;
    }
    public void setPriceBandLower(BigDecimal priceBandLower) 
    {
        this.priceBandLower = priceBandLower;
    }

    public BigDecimal getPriceBandLower() 
    {
        return priceBandLower;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }
    public void setCreateByuserName(String createByuserName) 
    {
        this.createByuserName = createByuserName;
    }

    public String getCreateByuserName() 
    {
        return createByuserName;
    }
    public void setUpdateByuserName(String updateByuserName) 
    {
        this.updateByuserName = updateByuserName;
    }

    public String getUpdateByuserName() 
    {
        return updateByuserName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("priceBandName", getPriceBandName())
            .append("docNum", getDocNum())
            .append("priceBandUpper", getPriceBandUpper())
            .append("priceBandLower", getPriceBandLower())
            .append("status", getStatus())
            .append("remark", getRemark())
            .append("delFlag", getDelFlag())
            .append("createByuserName", getCreateByuserName())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateByuserName", getUpdateByuserName())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}

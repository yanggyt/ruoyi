package com.ruoyi.busi.domain;

import java.math.BigDecimal;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 订单对象 busi_order
 * 
 * @author WangCL
 * @date 2021-12-21
 */
public class BusiOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID主键 */
    private String id;

    /** 客户公司ID */
    private String companyId;

    /** 客户公司名称 */
    @Excel(name = "客户公司")
    private String companyName;

    /** 订单名称 */
    @Excel(name = "订单名称")
    private String orderName;

    /** 款号标识码 */
    @Excel(name = "款号标识码")
    private String identificationCode;

    /** 订单价格 */
    @Excel(name = "订单价格")
    private double price;

    /** 类型 */
    @Excel(name = "类型")
    private String classify;

    /** 转换比例(米/件) */
    @Excel(name = "转换比例(米/件)")
    private BigDecimal ratio;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 产品需求信息 */
    private List<BusiProductRequire> busiProductRequireList;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setCompanyId(String companyId) 
    {
        this.companyId = companyId;
    }

    public String getCompanyId() 
    {
        return companyId;
    }
    public void setOrderName(String orderName) 
    {
        this.orderName = orderName;
    }

    public String getOrderName() 
    {
        return orderName;
    }
    public void setIdentificationCode(String identificationCode) 
    {
        this.identificationCode = identificationCode;
    }

    public String getIdentificationCode() 
    {
        return identificationCode;
    }

    public void setClassify(String classify) 
    {
        this.classify = classify;
    }

    public String getClassify() 
    {
        return classify;
    }
    public void setRatio(BigDecimal ratio) 
    {
        this.ratio = ratio;
    }

    public BigDecimal getRatio() 
    {
        return ratio;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<BusiProductRequire> getBusiProductRequireList()
    {
        return busiProductRequireList;
    }

    public void setBusiProductRequireList(List<BusiProductRequire> busiProductRequireList)
    {
        this.busiProductRequireList = busiProductRequireList;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("companyId", getCompanyId())
            .append("companyName", getCompanyName())
            .append("orderName", getOrderName())
            .append("identificationCode", getIdentificationCode())
            .append("price", getPrice())
            .append("classify", getClassify())
            .append("ratio", getRatio())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("busiProductRequireList", getBusiProductRequireList())
            .toString();
    }
}

package com.ruoyi.business.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 订单对象 biz_order
 * 
 * @author ruoyi
 * @date 2020-09-09
 */
public class BizOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;
    //订单状态(0-待支付，1-已支付，2-已取消, 3-待收货, 4-已完成)
    public static final int STATUS_INITIAL = 0;
    public static final int STATUS_PAYED = 1;
    public static final int STATUS_CANCELED = 2;
    public static final int STATUS_DELIVERY = 3;
    public static final int STATUS_COMPLETED = 4;

    /** 订单ID */
    private Long id;

    /** 订单编码 */
    @Excel(name = "订单编码")
    private String orderSn;

    /** 会员ID */
    private Long memberId;

    /** 手机号码 */
    @Excel(name = "手机号码")
    private String mobile;

    /** 用户姓名 */
    @Excel(name = "用户姓名")
    private String memberName;

    /** 订单金额 */
    @Excel(name = "订单金额")
    private BigDecimal orderAmount;

    /** 订单状态：0-待支付，1-已支付，2-已取消, 3-待收货, 4-已完成 */
    @Excel(name = "0-待支付，1-已支付，2-已取消, 3-待收货, 4-已完成")
    private Integer orderStatus;

    /** 订单备注 */
    @Excel(name = "订单备注")
    private String remark;

    /** 收货人详细地址 */
    @Excel(name = "收货人详细地址")
    private String addressDetail;

    /** 收货人地址ID */
    private Long addressId;

    /** 商品信息 */
    @Excel(name = "商品名称")
    private String productName;

    @Excel(name = "商品单价")
    private String productAmount;

    @Excel(name = "商品数量")
    private String productCount;

    /** 收货人地址信息 */
    @Excel(name = "收货人姓名")
    private String addressName;

    @Excel(name = "收货人手机")
    private String addressMobile;

    @Excel(name = "收货人省份")
    private String addressProvince;

    @Excel(name = "收货人城市")
    private String addressCity;

    @Excel(name = "收货人区域")
    private String addressArea;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setOrderSn(String orderSn) 
    {
        this.orderSn = orderSn;
    }

    public String getOrderSn() 
    {
        return orderSn;
    }
    public void setMemberId(Long memberId) 
    {
        this.memberId = memberId;
    }

    public Long getMemberId() 
    {
        return memberId;
    }
    public void setMobile(String mobile) 
    {
        this.mobile = mobile;
    }

    public String getMobile() 
    {
        return mobile;
    }
    public void setMemberName(String memberName) 
    {
        this.memberName = memberName;
    }

    public String getMemberName() 
    {
        return memberName;
    }
    public void setOrderAmount(BigDecimal orderAmount) 
    {
        this.orderAmount = orderAmount;
    }

    public BigDecimal getOrderAmount() 
    {
        return orderAmount;
    }
    public void setOrderStatus(Integer orderStatus) 
    {
        this.orderStatus = orderStatus;
    }

    public Integer getOrderStatus() 
    {
        return orderStatus;
    }
    public void setAddressId(Long addressId) 
    {
        this.addressId = addressId;
    }

    public Long getAddressId() 
    {
        return addressId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(String productAmount) {
        this.productAmount = productAmount;
    }

    public String getProductCount() {
        return productCount;
    }

    public void setProductCount(String productCount) {
        this.productCount = productCount;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public String getAddressMobile() {
        return addressMobile;
    }

    public void setAddressMobile(String addressMobile) {
        this.addressMobile = addressMobile;
    }

    public String getAddressProvince() {
        return addressProvince;
    }

    public void setAddressProvince(String addressProvince) {
        this.addressProvince = addressProvince;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public String getAddressArea() {
        return addressArea;
    }

    public void setAddressArea(String addressArea) {
        this.addressArea = addressArea;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("orderSn", getOrderSn())
            .append("memberId", getMemberId())
            .append("mobile", getMobile())
            .append("memberName", getMemberName())
            .append("orderAmount", getOrderAmount())
            .append("orderStatus", getOrderStatus())
            .append("addressId", getAddressId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}

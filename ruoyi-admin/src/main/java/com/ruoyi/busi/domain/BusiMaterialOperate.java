package com.ruoyi.busi.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 物料操作流水对象 busi_material_operate
 *
 * @author WangCL
 * @date 2021-12-24
 */
public class BusiMaterialOperate extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * ID主键
     */
    private Long id;

    /**
     * 物料库存ID
     */
    private String materialStockId;

    /**
     * 操作数量
     */
    @Excel(name = "操作数量")
    private double amount;

    /**
     * 操作类型
     */
    @Excel(name = "操作类型")
    private String oprateType;

    /**
     * 颜色和类型
     */
    private String colorAndType;

    /**
     * 订单ID
     */
    private String orderId;

    /**
     * 订单名称
     */
    @Excel(name = "订单")
    private String orderName;

    /**
     * 类型
     */
    private String classify;

    /**
     * 颜色
     */
    private String color;

    /**
     * 单位
     */
    private String unit;

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getColorAndType() {
        return colorAndType;
    }

    public void setColorAndType(String colorAndType) {
        this.colorAndType = colorAndType;
        this.color = colorAndType.split(";")[0];
        this.classify = colorAndType.split(";")[1];
        this.unit = colorAndType.split(";")[2];

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setMaterialStockId(String materialStockId) {
        this.materialStockId = materialStockId;
    }

    public String getMaterialStockId() {
        return materialStockId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setOprateType(String oprateType) {
        this.oprateType = oprateType;
    }

    public String getOprateType() {
        return oprateType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("materialStockId", getMaterialStockId())
                .append("amount", getAmount())
                .append("oprateType", getOprateType())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("remark", getRemark())
                .toString();
    }
}

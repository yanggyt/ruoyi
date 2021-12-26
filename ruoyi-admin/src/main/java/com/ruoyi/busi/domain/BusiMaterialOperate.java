package com.ruoyi.busi.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Optional;

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
     * 订单
     */
    @Excel(name = "订单")
    private String materialStockId;

    /**
     * 操作数量
     */
    @Excel(name = "操作数量")
    private Long amount;

    /**
     * 操作类型
     */
    @Excel(name = "操作类型")
    private String oprateType;

    /**
     * 颜色和类型
     */
    private String colorAndType;

    private String orderId;

    public String getColor() {
        if (colorAndType != null) {
            return colorAndType.split(";")[0];
        } else {
            return "";
        }


    }

    public String getClassify() {
        if (colorAndType != null) {
            return colorAndType.split(";")[1];
        } else {
            return "";
        }
    }

    public String getUnit() {
        if (colorAndType != null) {
            return colorAndType.split(";")[2];
        } else {
            return "";
        }
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

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getAmount() {
        return amount;
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

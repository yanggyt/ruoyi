package com.ruoyi.bend.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.Table;

/**
 * 收单详情对象 scrcu_online_order_details
 * 
 * @author bend
 * @date 2020-09-01
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "收单列表")
@Table(name = "scrcu_online_order_details")
public class ScrcuOnlineOrderDetails extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 商户号 */
    @ApiModelProperty(notes = "商户号")
    @Excel(name = "商户号")
    private String merId;

    /** 父商户号 */
    @ApiModelProperty(notes = "父商户号")
    @Excel(name = "父商户号")
    private String parentMerId;

    /** 机构编码 */
    @ApiModelProperty(notes = "机构编码")
    @Excel(name = "机构编码")
    private String orgCode;

    /** 主订单号 */
    @ApiModelProperty(notes = "主订单号")
    @Excel(name = "主订单号")
    private String orderNumber;

    /** 子订单号 */
    @ApiModelProperty(notes = "子订单号")
    @Excel(name = "子订单号")
    private String subOrderNumber;

    /** 交易金额 */
    @ApiModelProperty(notes = "交易金额")
    @Excel(name = "交易金额")
    private String subOrderAmt;

    /** 支付金额 */
    @ApiModelProperty(notes = "支付金额")
    @Excel(name = "支付金额")
    private String subPayAmt;

    /** 物流码 */
    @ApiModelProperty(notes = "物流码")
    private String logisCode;

    /** 机构红包 */
    @ApiModelProperty(notes = "机构红包")
    private String redPacketOrgNo;

    /** 结算标记（0不自动结算 1自动结算） */
    @ApiModelProperty(notes = "结算标记（0不自动结算 1自动结算）")
    @Excel(name = "结算标记", readConverterExp = "0=不自动结算,1=自动结算")
    private String autoSettleFlag;

    /** 商品信息 */
    @ApiModelProperty(notes = "商品信息")
    @Excel(name = "商品信息")
    private String goodsInfo;

    /** 删除标记（0否 1是） */
    @ApiModelProperty(notes = "删除标记（0否 1是）")
    private Integer deleted;

}

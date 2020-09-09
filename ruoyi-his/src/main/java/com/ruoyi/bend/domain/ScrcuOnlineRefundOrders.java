package com.ruoyi.bend.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.Table;

/**
 * 退款订单对象 scrcu_online_refund_orders
 * 
 * @author bend
 * @date 2020-09-01
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "退款订单")
@Table(name = "scrcu_online_refund_orders")
public class ScrcuOnlineRefundOrders extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 商户代码 */
    @ApiModelProperty(notes = "商户代码")
    @Excel(name = "商户代码")
    private String merId;

    /** 退货商户号 */
    @ApiModelProperty(notes = "退货商户号")
    @Excel(name = "退货商户号")
    private String merNo;

    /** 退款订单号 */
    @ApiModelProperty(notes = "退款订单号")
    @Excel(name = "退款订单号")
    private String orderNumber;

    /** 发送时间 */
    @ApiModelProperty(notes = "发送时间")
    @Excel(name = "发送时间")
    private String orderSendTime;

    /** 后台通知地址 */
    @ApiModelProperty(notes = "后台通知地址")
    private String backEndUrl;

    /** 原支付主订单号 */
    @ApiModelProperty(notes = "原支付主订单号")
    @Excel(name = "原支付主订单号")
    private String oriOrderNumber;

    /** 原支付子订单号 */
    @ApiModelProperty(notes = "原支付子订单号")
    @Excel(name = "原支付子订单号")
    private String oriSubOrderNumber;

    /** 退款金额 */
    @ApiModelProperty(notes = "退款金额")
    @Excel(name = "退款金额")
    private String orderAmt;

    /** 接入渠道（01PC 02手机wap） */
    @ApiModelProperty(notes = "接入渠道（01PC 02手机wap）")
    @Excel(name = "接入渠道", readConverterExp = "0=1PC,0=2手机wap")
    private String channel;

    /** 订单状态（01交易中 02交易成功 03交易失败） */
    @ApiModelProperty(notes = "订单状态（01交易中 02交易成功 03交易失败）")
    @Excel(name = "订单状态", readConverterExp = "0=1交易中,0=2交易成功,0=3交易失败")
    private String orderState;

    /** 删除标记（0否 1是） */
    @ApiModelProperty(notes = "删除标记（0否 1是）")
    private Integer deleted;

}

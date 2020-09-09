package com.ruoyi.bend.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.Table;

/**
 * 扫码退款对象 scrcu_offline_refund_orders
 * 
 * @author bend
 * @date 2020-09-01
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "扫码退款")
@Table(name = "scrcu_offline_refund_orders")
public class ScrcuOfflineRefundOrders extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 商户编号 */
    @ApiModelProperty(notes = "商户编号")
    @Excel(name = "商户编号")
    private String mchtNo;

    /** 退款订单 */
    @ApiModelProperty(notes = "退款订单")
    @Excel(name = "退款订单")
    private String reqSsn;

    /** 请求时间 */
    @ApiModelProperty(notes = "请求时间")
    @Excel(name = "请求时间")
    private String reqTime;

    /** 原订单号 */
    @ApiModelProperty(notes = "原订单号")
    @Excel(name = "原订单号")
    private String origReqSsn;

    /** 原请求时间 */
    @ApiModelProperty(notes = "原请求时间")
    @Excel(name = "原请求时间")
    private String origReqTime;

    /** 原响应流水 */
    @ApiModelProperty(notes = "原响应流水")
    @Excel(name = "原响应流水")
    private String origRespSsn;

    /** 原响应时间 */
    @ApiModelProperty(notes = "原响应时间")
    @Excel(name = "原响应时间")
    private String origRespTime;

    /** 退款金额 */
    @ApiModelProperty(notes = "退款金额")
    @Excel(name = "退款金额")
    private String orderAmt;

    /** 退款描述 */
    @ApiModelProperty(notes = "退款描述")
    @Excel(name = "退款描述")
    private String orderDesc;

    /** 退款响应流水号 */
    @ApiModelProperty(notes = "退款响应流水号")
    @Excel(name = "退款响应流水号")
    private String respSsn;

    /** 退款响应时间 */
    @ApiModelProperty(notes = "退款响应时间")
    @Excel(name = "退款响应时间")
    private String respTime;

    /** 订单状态（00订单成功 01已撤销 02已发生退款 03已关闭 09订单失败 10订单已受理 11订单处理中 12用户输密中 13订单处理超时）] */
    @ApiModelProperty(notes = "订单状态（00订单成功 01已撤销 02已发生退款 03已关闭 09订单失败 10订单已受理 11订单处理中 12用户输密中 13订单处理超时）]")
    @Excel(name = "订单状态", readConverterExp = "0=0订单成功,0=1已撤销,0=2已发生退款,0=3已关闭,0=9订单失败,1=0订单已受理,1=1订单处理中,1=2用户输密中,1=3订单处理超时")
    private String orderState;

    /** 删除标记（0否 1是） */
    @ApiModelProperty(notes = "删除标记（0否 1是）")
    private Integer deleted;

}

package com.ruoyi.bend.domain;

import java.util.Date;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.Table;

/**
 * 线下订单对象 scrcu_offline_orders
 * 
 * @author bend
 * @date 2020-09-01
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "线下订单")
@Table(name = "scrcu_offline_orders")
public class ScrcuOfflineOrders extends BaseEntity
{
    private static final long serialVersionUID = 1L;


    /** 用户ID */
    @ApiModelProperty(notes = "用户ID")
    private Long memberId;

    /** 订单号 */
    @ApiModelProperty(notes = "订单号")
    @Excel(name = "订单号")
    private String reqSsn;

    /** 请求时间 */
    @ApiModelProperty(notes = "请求时间")
    @Excel(name = "请求时间")
    private String reqTime;

    /** 商户编号 */
    @ApiModelProperty(notes = "商户编号")
    @Excel(name = "商户编号")
    private String mchtNo;

    /** 行政区划 */
    @ApiModelProperty(notes = "行政区划")
    private String txnAreaInfo;

    /** 交易结果通知地址 */
    @ApiModelProperty(notes = "交易结果通知地址")
    private String notifyUrl;

    /** 订单金额 */
    @ApiModelProperty(notes = "订单金额")
    @Excel(name = "订单金额")
    private String orderAmt;

    /** 有效时间,单位为秒；超过该时间订单自动关闭； */
    @ApiModelProperty(notes = "有效时间,单位为秒；超过该时间订单自动关闭；")
    private String validTime;

    /** 订单描述 */
    @ApiModelProperty(notes = "订单描述")
    @Excel(name = "订单描述")
    private String orderDesc;

    /** 商户二维码 */
    @ApiModelProperty(notes = "商户二维码")
    private String mchToken;

    /** 用户付款码 */
    @ApiModelProperty(notes = "用户付款码")
    private String userToken;

    /** 扫码类别（1用户主扫 2用户被扫） */
    @ApiModelProperty(notes = "扫码类别（1用户主扫 2用户被扫）")
    @Excel(name = "扫码类别", readConverterExp = "1=用户主扫,2=用户被扫")
    private String sacnType;

    /** 交易类型（01支付 02退款） */
    @ApiModelProperty(notes = "交易类型（01支付 02退款）")
    @Excel(name = "交易类型", readConverterExp = "0=1支付,0=2退款")
    private String txnType;

    /** 系统流水号 */
    @ApiModelProperty(notes = "系统流水号")
    @Excel(name = "系统流水号")
    private String payNo;

    /** 机构编码 */
    @ApiModelProperty(notes = "机构编码")
    @Excel(name = "机构编码")
    private String orgCode;

    /** HIS交易号 */
    @ApiModelProperty(notes = "HIS交易号")
    @Excel(name = "HIS交易号")
    private String hisTradeNo;

    /** 响应流水号 */
    @ApiModelProperty(notes = "响应流水号")
    @Excel(name = "响应流水号")
    private String respSsn;

    /** 响应时间 */
    @ApiModelProperty(notes = "响应时间")
    @Excel(name = "响应时间")
    private String respTime;

    /** 订单状态（00订单成功 01已撤销 02已发生退款 03已关闭 09订单失败 10订单已受理 11订单处理中 12用户输密中 13订单处理超时）] */
    @ApiModelProperty(notes = "订单状态（00订单成功 01已撤销 02已发生退款 03已关闭 09订单失败 10订单已受理 11订单处理中 12用户输密中 13订单处理超时）]")
    @Excel(name = "订单状态", readConverterExp = "0=0订单成功,0=1已撤销,0=2已发生退款,0=3已关闭,0=9订单失败,1=0订单已受理,1=1订单处理中,1=2用户输密中,1=3订单处理超时")
    private String orderState;

    /** 订单种类（0免费支付 1收单支付 2扫码支付 3微信支付） */
    @ApiModelProperty(notes = "订单种类（0免费支付 1收单支付 2扫码支付 3微信支付）")
    @Excel(name = "订单种类", readConverterExp = "0=免费支付,1=收单支付,2=扫码支付,3=微信支付")
    private Integer orderSpecies;

    /** 费用来源（0微信挂号 1微信门诊缴费 2微信住院预交 3分诊挂号 4分诊门诊缴费 5线下扫码支付 6诊间支付） */
    @ApiModelProperty(notes = "费用来源（0微信挂号 1微信门诊缴费 2微信住院预交 3分诊挂号 4分诊门诊缴费 5线下扫码支付 6诊间支付）")
    @Excel(name = "费用来源", readConverterExp = "0=微信挂号,1=微信门诊缴费,2=微信住院预交,3=分诊挂号,4=分诊门诊缴费,5=线下扫码支付,6=诊间支付")
    private Integer expenseType;

    /** 删除标记（0否 1是） */
    @ApiModelProperty(notes = "删除标记（0否 1是）")
    private Integer deleted;

}

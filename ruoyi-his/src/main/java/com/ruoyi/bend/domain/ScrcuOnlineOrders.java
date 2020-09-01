package com.ruoyi.bend.domain;

import java.util.List;
import java.util.Date;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.Table;

/**
 * 收单列表对象 scrcu_online_orders
 * 
 * @author bend
 * @date 2020-09-01
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "收单列表")
@Table(name = "scrcu_online_orders")
public class ScrcuOnlineOrders extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户ID */
    @ApiModelProperty(notes = "用户ID")
    @Excel(name = "用户ID")
    private Long memberId;

    /** 商户代码 */
    @ApiModelProperty(notes = "商户代码")
    @Excel(name = "商户代码")
    private String merId;

    /** 订单类型(01实物消费订单,固定值) */
    @ApiModelProperty(notes = "订单类型(01实物消费订单,固定值)")
    private String orderType;

    /** 订单号 */
    @ApiModelProperty(notes = "订单号")
    @Excel(name = "订单号")
    private String orderNumber;

    /** 订单发送时间,格式: yyyyMMddHHmmss */
    @ApiModelProperty(notes = "订单发送时间,格式: yyyyMMddHHmmss")
    @Excel(name = "订单发送时间,格式: yyyyMMddHHmmss")
    private String orderSendTime;

    /** 订单总金额,单位为分 */
    @ApiModelProperty(notes = "订单总金额,单位为分")
    @Excel(name = "订单总金额,单位为分")
    private String orderAmt;

    /** 支付总金额,单位为分(包含物流,不包含红包) */
    @ApiModelProperty(notes = "支付总金额,单位为分(包含物流,不包含红包)")
    @Excel(name = "支付总金额,单位为分(包含物流,不包含红包)")
    private String payAmt;

    /** 商品名称 */
    @ApiModelProperty(notes = "商品名称")
    @Excel(name = "商品名称")
    private String goodsSubject;

    /** 交易币种（01人民币） */
    @ApiModelProperty(notes = "交易币种（01人民币）")
    @Excel(name = "交易币种", readConverterExp = "0=1人民币")
    private String orderCurrency;

    /** 接入渠道（01PC 02手机） */
    @ApiModelProperty(notes = "接入渠道（01PC 02手机）")
    @Excel(name = "接入渠道", readConverterExp = "0=1PC,0=2手机")
    private String channel;

    /** 接入方式（01H5商城 02APP商城 03微信公众号商城 04小程序商城 05其它(当channel为02时必填)） */
    @ApiModelProperty(notes = "接入方式（01H5商城 02APP商城 03微信公众号商城 04小程序商城 05其它(当channel为02时必填)）")
    @Excel(name = "接入方式", readConverterExp = "0=1H5商城,0=2APP商城,0=3微信公众号商城,0=4小程序商城,0=5其它(当channel为02时必填)")
    private String mobileWay;

    /** 手机接入类型（01安卓 02IOS系统(当mobileWay为02时必填)） */
    @ApiModelProperty(notes = "手机接入类型（01安卓 02IOS系统(当mobileWay为02时必填)）")
    @Excel(name = "手机接入类型", readConverterExp = "0=1安卓,0=2IOS系统(当mobileWay为02时必填)")
    private String mobileType;

    /** 前端通知地址 */
    @ApiModelProperty(notes = "前端通知地址")
    private String frontEndUrl;

    /** 后台通知地址 */
    @ApiModelProperty(notes = "后台通知地址")
    private String backEndUrl;

    /** 收单主订单号 */
    @ApiModelProperty(notes = "收单主订单号")
    @Excel(name = "收单主订单号")
    private String orderSeqId;

    /** 支付类型（00蜀信e支付 01蜀信卡快捷支付 02银联支付 03微信支付 04支付宝支付 05授信支付  10积分支付） */
    @ApiModelProperty(notes = "支付类型（00蜀信e支付 01蜀信卡快捷支付 02银联支付 03微信支付 04支付宝支付 05授信支付  10积分支付）")
    @Excel(name = "支付类型", readConverterExp = "0=0蜀信e支付,0=1蜀信卡快捷支付,0=2银联支付,0=3微信支付,0=4支付宝支付,0=5授信支付,1=0积分支付")
    private String payType;

    /** 交易类型（01消费 02退货 03撤销 04查询） */
    @ApiModelProperty(notes = "交易类型（01消费 02退货 03撤销 04查询）")
    @Excel(name = "交易类型", readConverterExp = "0=1消费,0=2退货,0=3撤销,0=4查询")
    private String transType;

    /** 交易状态（02成功） */
    @ApiModelProperty(notes = "交易状态（02成功）")
    @Excel(name = "交易状态", readConverterExp = "0=2成功")
    private String payStatus;

    /** 订单种类（1收单支付 2扫码支付） */
    @ApiModelProperty(notes = "订单种类（1收单支付 2扫码支付）")
    @Excel(name = "订单种类", readConverterExp = "1=收单支付,2=扫码支付")
    private Integer orderSpecies;

    /** 订单状态（01交易中 02交易成功 03交易失败） */
    @ApiModelProperty(notes = "订单状态（01交易中 02交易成功 03交易失败）")
    @Excel(name = "订单状态", readConverterExp = "0=1交易中,0=2交易成功,0=3交易失败")
    private String orderState;

    /** 支付完成时间,格式: yyyyMMddHHmmss */
    @ApiModelProperty(notes = "支付完成时间,格式: yyyyMMddHHmmss")
    @Excel(name = "支付完成时间,格式: yyyyMMddHHmmss", width = 30, dateFormat = "yyyy-MM-dd")
    private Date orderTranTime;

    /** 订单创建时间,格式: yyyyMMddHHmmss */
    @ApiModelProperty(notes = "订单创建时间,格式: yyyyMMddHHmmss")
    @Excel(name = "订单创建时间,格式: yyyyMMddHHmmss", width = 30, dateFormat = "yyyy-MM-dd")
    private Date orderCreateTime;

    /** 费用来源（0微信挂号 1微信门诊缴费 2微信住院预交 3分诊挂号 4分诊门诊缴费 5线下扫码支付 6诊间支付） */
    @ApiModelProperty(notes = "费用来源（0微信挂号 1微信门诊缴费 2微信住院预交 3分诊挂号 4分诊门诊缴费 5线下扫码支付 6诊间支付）")
    @Excel(name = "费用来源", readConverterExp = "0=微信挂号,1=微信门诊缴费,2=微信住院预交,3=分诊挂号,4=分诊门诊缴费,5=线下扫码支付,6=诊间支付")
    private Integer expenseType;

    /** 支付方式类别（1微信支付 2支付宝 3农信支付） */
    @ApiModelProperty(notes = "支付方式类别（1微信支付 2支付宝 3农信支付）")
    @Excel(name = "支付方式类别", readConverterExp = "1=微信支付,2=支付宝,3=农信支付")
    private Integer payWayType;

    /** 流水号 */
    @ApiModelProperty(notes = "流水号")
    @Excel(name = "流水号")
    private String payNo;

    /** 删除标记（0否 1是） */
    @ApiModelProperty(notes = "删除标记（0否 1是）")
    private Integer deleted;

    /** 收单详情信息 */
    @ApiModelProperty(notes = "收单详情信息")
    private List<ScrcuOnlineOrderDetails> scrcuOnlineOrderDetailsList;

}

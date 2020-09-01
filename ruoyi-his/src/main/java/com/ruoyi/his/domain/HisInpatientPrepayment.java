package com.ruoyi.his.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.math.BigDecimal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.Table;

/**
 * 住院预交对象 his_inpatient_prepayment
 * 
 * @author bend
 * @date 2020-07-14
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "住院预交")
@Table(name = "his_inpatient_prepayment")
public class HisInpatientPrepayment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户ID */
    @ApiModelProperty(notes = "用户ID")
    private Long memberId;

    /** 手机号 */
    @ApiModelProperty(notes = "手机号")
    @Excel(name = "手机号")
    private String mobilePhone;

    /** 就诊人ID */
    @ApiModelProperty(notes = "就诊人ID")
    private Long patientId;

    /** 就诊人 */
    @ApiModelProperty(notes = "就诊人")
    @Excel(name = "就诊人")
    private String patientName;

    /** 身份证 */
    @ApiModelProperty(notes = "身份证")
    @Excel(name = "身份证")
    private String idCardNo;

    /** 住院ID */
    @ApiModelProperty(notes = "住院ID")
    @Excel(name = "住院ID")
    private String businessId;

    /** 订单编号 */
    @ApiModelProperty(notes = "订单编号")
    @Excel(name = "订单编号")
    private String orderCode;

    /** 预缴费ID */
    @ApiModelProperty(notes = "预缴费ID")
    @Excel(name = "预缴费ID")
    private String prepayId;

    /** 流水号 */
    @ApiModelProperty(notes = "流水号")
    @Excel(name = "流水号")
    private String paySerialNo;

    /** 缴费方式列表 */
    @ApiModelProperty(notes = "缴费方式列表")
    @Excel(name = "缴费方式列表")
    private String paymentListStr;

    /** 收费人员ID */
    @ApiModelProperty(notes = "收费人员ID")
    @Excel(name = "收费人员ID")
    private String userId;

    /** 收费人员 */
    @ApiModelProperty(notes = "收费人员")
    @Excel(name = "收费人员")
    private String userName;

    /** 厂商标识 */
    @ApiModelProperty(notes = "厂商标识")
    @Excel(name = "厂商标识")
    private String vendorId;

    /** 基层流水号 */
    @ApiModelProperty(notes = "基层流水号")
    @Excel(name = "基层流水号")
    private String hisSerialNo;

    /** 预交金额 */
    @ApiModelProperty(notes = "预交金额")
    @Excel(name = "预交金额")
    private BigDecimal totalFee;

    /** 实际金额 */
    @ApiModelProperty(notes = "实际金额")
    @Excel(name = "实际金额")
    private BigDecimal totalCost;

    /** 机构ID */
    @ApiModelProperty(notes = "机构ID")
    private String orgCode;

    /** 机构名称 */
    @ApiModelProperty(notes = "机构名称")
    @Excel(name = "机构名称")
    private String orgName;

    /** 状态（0未交费 1已支付） */
    @ApiModelProperty(notes = "状态（0未交费 1已支付）")
    @Excel(name = "状态", readConverterExp = "0=未交费,1=已支付")
    private Integer prepayStatus;

    /** 删除标记（0否 1是） */
    @ApiModelProperty(notes = "删除标记（0否 1是）")
    private Integer deleted;

}

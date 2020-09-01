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
 * 门诊预交对象 his_outpatient_payment
 * 
 * @author bend
 * @date 2020-07-14
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "门诊预交")
@Table(name = "his_outpatient_payment")
public class HisOutpatientPayment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 订单编号 */
    @ApiModelProperty(notes = "订单编号")
    @Excel(name = "订单编号")
    private String orderCode;

    /** 挂号ID */
    @ApiModelProperty(notes = "挂号ID")
    @Excel(name = "挂号ID")
    private String registeredId;

    /** 处方IDS */
    @ApiModelProperty(notes = "处方IDS")
    @Excel(name = "处方IDS")
    private String recipeIds;

    /** 收费人员ID */
    @ApiModelProperty(notes = "收费人员ID")
    @Excel(name = "收费人员ID")
    private String userId;

    /** 总金额 */
    @ApiModelProperty(notes = "总金额")
    @Excel(name = "总金额")
    private BigDecimal totalFee;

    /** 缴费流水号 */
    @ApiModelProperty(notes = "缴费流水号")
    @Excel(name = "缴费流水号")
    private String paySerialNo;

    /** 缴费方式列表 */
    @ApiModelProperty(notes = "缴费方式列表")
    @Excel(name = "缴费方式列表")
    private String paymentListStr;

    /** 厂商标识 */
    @ApiModelProperty(notes = "厂商标识")
    @Excel(name = "厂商标识")
    private String vendorId;

    /** 机构ID */
    @ApiModelProperty(notes = "机构ID")
    private String orgCode;

    /** 机构名称 */
    @ApiModelProperty(notes = "机构名称")
    @Excel(name = "机构名称")
    private String orgName;

    /** 删除标记（0否 1是） */
    @ApiModelProperty(notes = "删除标记（0否 1是）")
    private Integer deleted;

}

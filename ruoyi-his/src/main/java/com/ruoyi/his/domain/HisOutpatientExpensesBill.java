package com.ruoyi.his.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.math.BigDecimal;
import java.util.List;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 待缴费单对象 his_outpatient_expenses_bill
 * 
 * @author bend
 * @date 2020-07-09
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "待缴费单")
@Table(name = "his_outpatient_expenses_bill")
public class HisOutpatientExpensesBill extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户ID */
    @ApiModelProperty(notes = "用户ID")
    private Long memberId;

    /** 患者ID */
    @ApiModelProperty(notes = "患者ID")
    private Long patientId;


    /** 病人姓名 */
    @ApiModelProperty(notes = "病人姓名")
    @Excel(name = "病人姓名")
    private String patientName;

    /** 身份证号 */
    @ApiModelProperty(notes = "身份证号")
    @Excel(name = "身份证号")
    private String idCardNo;

    /** 门诊ID */
    @ApiModelProperty(notes = "门诊ID")
    @Excel(name = "门诊ID")
    private String businessId;

    /** 挂号ID */
    @ApiModelProperty(notes = "挂号ID")
    private String registeredId;

    /** 处方ID */
    @ApiModelProperty(notes = "处方ID")
    private String recipeId;

    /** 处方号 */
    @ApiModelProperty(notes = "处方号")
    @Excel(name = "处方号")
    private String recipeCode;

    /** 处方费用 */
    @ApiModelProperty(notes = "处方费用")
    @Excel(name = "处方费用")
    private BigDecimal recipeFee;

    /** 处方医生 */
    @ApiModelProperty(notes = "处方医生")
    @Excel(name = "处方医生")
    private String billDoctor;

    /** 开单科室 */
    @ApiModelProperty(notes = "开单科室")
    @Excel(name = "开单科室")
    private String billDept;

    /** 开单时间 */
    @ApiModelProperty(notes = "开单时间")
    @Excel(name = "开单时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date billTime;

    /** 费用名称 */
    @ApiModelProperty(notes = "费用名称")
    @Excel(name = "费用名称")
    private String feeName;

    /** 执行科室 */
    @ApiModelProperty(notes = "执行科室")
    private String operateDept;

    /** 处方状态（0待缴费 1已缴费） */
    @ApiModelProperty(notes = "处方状态（0待缴费 1已缴费）")
    @Excel(name = "处方状态", readConverterExp = "0=待缴费,1=已缴费")
    private Integer recipeStatus;

    /** 收费记录ID */
    @ApiModelProperty(notes = "收费记录ID")
    @Excel(name = "收费记录ID")
    private String chargeRecordId;

    /** 订单编号 */
    @ApiModelProperty(notes = "订单编号")
    @Excel(name = "订单编号")
    private String orderCode;

    /** 机构ID */
    @ApiModelProperty(notes = "机构ID")
    private String orgCode;

    /** 机构名称 */
    @ApiModelProperty(notes = "机构名称")
    @Excel(name = "机构名称")
    private String orgName;

    /** 缴费时间 */
    @ApiModelProperty(notes = "缴费时间")
    @Excel(name = "缴费时间")
    private String payTime;

    /** 删除标记（0否 1是） */
    @ApiModelProperty(notes = "删除标记（0否 1是）")
    private Integer deleted;

    /** 清单详情信息 */
    @ApiModelProperty(notes = "清单详情信息")
    @Transient
    private List<HisOutpatientExpensesBillDetail> hisOutpatientExpensesBillDetailList;

}

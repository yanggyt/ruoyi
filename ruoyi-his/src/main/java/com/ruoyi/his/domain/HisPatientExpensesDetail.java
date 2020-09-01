package com.ruoyi.his.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;
/**
 * 费用详情对象 his_patient_expenses_detail
 * 
 * @author bend
 * @date 2020-07-09
 */
@Data
@ApiModel(value = "费用记录")
@Table(name = "his_patient_expenses")
public class HisPatientExpensesDetail
{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(notes = "主键ID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    /** 费用ID（分别对应门诊费和住院费） */
    @ApiModelProperty(notes = "费用ID（分别对应门诊费和住院费）")
    @Excel(name = "费用ID")
    private Long expensesId;

    /** 费用明细ID */
    @ApiModelProperty(notes = "费用明细ID")
    @Excel(name = "费用明细ID")
    private String costDetailId;

    /** 项目名称 */
    @ApiModelProperty(notes = "项目名称")
    @Excel(name = "项目名称")
    private String costItemName;

    /** 项目编码 */
    @ApiModelProperty(notes = "项目编码")
    private String costItemCode;

    /** 项目类别名称 */
    @ApiModelProperty(notes = "项目类别名称")
    @Excel(name = "项目类别名称")
    private String costItemCategoryName;

    /** 项目类别编码 */
    @ApiModelProperty(notes = "项目类别编码")
    private String costItemCategoryCode;

    /** 单位 */
    @ApiModelProperty(notes = "单位")
    @Excel(name = "单位")
    private String unit;

    /** 剂型 */
    @ApiModelProperty(notes = "剂型")
    @Excel(name = "剂型")
    private String formulation;

    /** 规格 */
    @ApiModelProperty(notes = "规格")
    @Excel(name = "规格")
    private String specification;

    /** 单价 */
    @ApiModelProperty(notes = "单价")
    @Excel(name = "单价")
    private BigDecimal unitPrice;

    /** 数量 */
    @ApiModelProperty(notes = "数量")
    @Excel(name = "数量")
    private Long quantity;

    /** 金额 */
    @ApiModelProperty(notes = "金额")
    @Excel(name = "金额")
    private BigDecimal amount;

    /** 药品用量 */
    @ApiModelProperty(notes = "药品用量")
    @Excel(name = "药品用量")
    private String drugDosage;

    /** 药品用法 */
    @ApiModelProperty(notes = "药品用法")
    @Excel(name = "药品用法")
    private String drugUsage;

    /** 用药频次 */
    @ApiModelProperty(notes = "用药频次")
    @Excel(name = "用药频次")
    private String frequency;

    /** 用药天数 */
    @ApiModelProperty(notes = "用药天数")
    @Excel(name = "用药天数")
    private Long medicateDays;

    /** 医院计价单位 */
    @ApiModelProperty(notes = "医院计价单位")
    private String hospitalPricingUnit;

    /** 是否进口药品 */
    @ApiModelProperty(notes = "是否进口药品")
    private String isImportedDrugs;

    /** 药品产地 */
    @ApiModelProperty(notes = "药品产地")
    private String drugProducingArea;

    /** 处方号 */
    @ApiModelProperty(notes = "处方号")
    @Excel(name = "处方号")
    private String recipeCode;

    /** 费用单据类型 */
    @ApiModelProperty(notes = "费用单据类型")
    @Excel(name = "费用单据类型")
    private String costDocumentType;

    /** 开单科室名称 */
    @ApiModelProperty(notes = "开单科室名称")
    @Excel(name = "开单科室名称")
    private String billDeptName;

    /** 开单科室编码 */
    @ApiModelProperty(notes = "开单科室编码")
    private String billDeptId;

    /** 开单医生姓名 */
    @ApiModelProperty(notes = "开单医生姓名")
    @Excel(name = "开单医生姓名")
    private String billDoctorName;

    /** 开单医生编码 */
    @ApiModelProperty(notes = "开单医生编码")
    private String billDoctorId;

    /** 开单时间 */
    @ApiModelProperty(notes = "开单时间")
    @Excel(name = "开单时间")
    private String billTime;

    /** 执行科室名称 */
    @ApiModelProperty(notes = "执行科室名称")
    @Excel(name = "执行科室名称")
    private String operateDeptName;

    /** 执行科室编码 */
    @ApiModelProperty(notes = "执行科室编码")
    private String operateDeptId;

    /** 执行医生姓名 */
    @ApiModelProperty(notes = "执行医生姓名")
    @Excel(name = "执行医生姓名")
    private String operateDoctorName;

    /** 执行医生编码 */
    @ApiModelProperty(notes = "执行医生编码")
    private String operateDoctorId;

    /** 执行时间 */
    @ApiModelProperty(notes = "执行时间")
    @Excel(name = "执行时间")
    private String operateTime;

    /** 处方医师 */
    @ApiModelProperty(notes = "处方医师")
    @Excel(name = "处方医师")
    private String prescribe;

    /** 经办人 */
    @ApiModelProperty(notes = "经办人")
    @Excel(name = "经办人")
    private String operator;

    /** 执业医师证号 */
    @ApiModelProperty(notes = "执业医师证号")
    private String doctorNumber;

    /** 费用冲销ID */
    @ApiModelProperty(notes = "费用冲销ID")
    private String costWriteOffId;

    /** 是否收费（0否 1是） */
    @ApiModelProperty(notes = "是否收费（0否 1是）")
    @Excel(name = "是否收费", readConverterExp = "0=否,1=是")
    private Integer isChargeFee;

    /** 费用时间 */
    @ApiModelProperty(notes = "费用时间")
    @Excel(name = "费用时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date chargeTime;

    /** 机构编码 */
    @ApiModelProperty(notes = "机构编码")
    private String orgCode;

    /** 机构名称 */
    @ApiModelProperty(notes = "机构名称")
    @Excel(name = "机构名称")
    private String orgName;

    /** 包装规格 */
    @ApiModelProperty(notes = "包装规格")
    private String packageSpec;

    /** 与单次用量同单位规格 */
    @ApiModelProperty(notes = "与单次用量同单位规格")
    private String dosageSpec;

    /** 每次用量 */
    @ApiModelProperty(notes = "每次用量")
    private String everyTimeDosage;

    /** 目录CODE */
    @ApiModelProperty(notes = "目录CODE")
    private String dirCode;

    /** 创建时间 */
    @ApiModelProperty(notes = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 删除标记（0否 1是） */
    @ApiModelProperty(notes = "删除标记（0否 1是）")
    private Integer deleted;

}

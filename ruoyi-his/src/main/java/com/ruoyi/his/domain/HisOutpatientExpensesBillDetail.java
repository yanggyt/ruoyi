package com.ruoyi.his.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 清单详情对象 his_outpatient_expenses_bill_detail
 *
 * @author bend
 * @date 2020-07-09
 */
@Data
@ApiModel(value = "待缴费单详情")
@Table(name = "his_outpatient_expenses_bill")
public class HisOutpatientExpensesBillDetail
{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(notes = "主键ID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    /** 清单ID */
    @ApiModelProperty(notes = "清单ID")
    @Excel(name = "清单ID")
    private Long billId;

    /** 清单详情ID */
    @ApiModelProperty(notes = "清单详情ID")
    @Excel(name = "清单详情ID")
    private String billDetailId;

    /** 处方ID */
    @ApiModelProperty(notes = "处方ID")
    @Excel(name = "处方ID")
    private String recipeId;

    /** 处方号 */
    @ApiModelProperty(notes = "处方号")
    @Excel(name = "处方号")
    private String recipeCode;

    /** 金额 */
    @ApiModelProperty(notes = "金额")
    @Excel(name = "金额")
    private BigDecimal fee;

    /** 费用名称 */
    @ApiModelProperty(notes = "费用名称")
    @Excel(name = "费用名称")
    private String feeName;

    /** 处方医生 */
    @ApiModelProperty(notes = "处方医生")
    @Excel(name = "处方医生")
    private String billDoctor;

    /** 开单科室 */
    @ApiModelProperty(notes = "开单科室")
    @Excel(name = "开单科室")
    private String billDept;

    /** 执行科室 */
    @ApiModelProperty(notes = "执行科室")
    @Excel(name = "执行科室")
    private String operateDept;

    /** 开单时间 */
    @ApiModelProperty(notes = "开单时间")
    @Excel(name = "开单时间")
    private String billTime;

    /** 数量 */
    @ApiModelProperty(notes = "数量")
    @Excel(name = "数量")
    private Long amount;

    /** 单位 */
    @ApiModelProperty(notes = "单位")
    @Excel(name = "单位")
    private String unit;

    /** 规格 */
    @ApiModelProperty(notes = "规格")
    @Excel(name = "规格")
    private String specification;

    /** 单价 */
    @ApiModelProperty(notes = "单价")
    @Excel(name = "单价")
    private BigDecimal unitPrice;

    /** 费用项目名称 */
    @ApiModelProperty(notes = "费用项目名称")
    @Excel(name = "费用项目名称")
    private String costItemName;

    /** 创建时间 */
    @ApiModelProperty(notes = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 删除标记（0否 1是） */
    @ApiModelProperty(notes = "删除标记（0否 1是）")
    private Integer deleted;

}

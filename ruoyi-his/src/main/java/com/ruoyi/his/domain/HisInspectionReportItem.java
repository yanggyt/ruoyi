package com.ruoyi.his.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.Table;
/**
 * 检查检验明细对象 his_inspection_report_item
 * 
 * @author bend
 * @date 2020-07-10
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "检查检验明细")
@Table(name = "his_inspection_report_item")
public class HisInspectionReportItem extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 报告ID */
    @ApiModelProperty(notes = "报告ID")
    @Excel(name = "报告ID")
    private Long reportId;

    /** 检查项ID */
    @ApiModelProperty(notes = "检查项ID")
    @Excel(name = "检查项ID")
    private String itemId;

    /** 执行记录ID */
    @ApiModelProperty(notes = "执行记录ID")
    @Excel(name = "执行记录ID")
    private String execRecordId;

    /** 项目名称 */
    @ApiModelProperty(notes = "项目名称")
    @Excel(name = "项目名称")
    private String itemName;

    /** 项目内容 */
    @ApiModelProperty(notes = "项目内容")
    @Excel(name = "项目内容")
    private String itemValue;

    /** 排序 */
    @ApiModelProperty(notes = "排序")
    @Excel(name = "排序")
    private String sortNo;

    /** 单位 */
    @ApiModelProperty(notes = "单位")
    @Excel(name = "单位")
    private String unit;

    /** 参考值 */
    @ApiModelProperty(notes = "参考值")
    @Excel(name = "参考值")
    private String referenceValue;

    /** 定性结果 */
    @ApiModelProperty(notes = "定性结果")
    @Excel(name = "定性结果")
    private String qualityResult;

    /** 档案标志 */
    @ApiModelProperty(notes = "档案标志")
    @Excel(name = "档案标志")
    private String archivedFlag;

    /** 金额 */
    @ApiModelProperty(notes = "金额")
    @Excel(name = "金额")
    private String fee;

    /** 删除标记（0否 1是） */
    @ApiModelProperty(notes = "删除标记（0否 1是）")
    private Integer deleted;

}

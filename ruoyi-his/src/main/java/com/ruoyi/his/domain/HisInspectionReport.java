package com.ruoyi.his.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.math.BigDecimal;
import java.util.List;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.Table;

/**
 * 检查检验对象 his_inspection_report
 * 
 * @author bend
 * @date 2020-07-10
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "检查检验报告单")
@Table(name = "his_inspection_report")
public class HisInspectionReport extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 申请单ID */
    @ApiModelProperty(notes = "申请单ID")
    @Excel(name = "申请单ID")
    private String applyId;

    /** 身份证号 */
    @ApiModelProperty(notes = "身份证号")
    @Excel(name = "身份证号")
    private String idCardNo;

    /** 病人姓名 */
    @ApiModelProperty(notes = "病人姓名")
    @Excel(name = "病人姓名")
    private String patientName;

    /** 性别 */
    @ApiModelProperty(notes = "性别")
    @Excel(name = "性别")
    private String patientSex;

    /** 年龄 */
    @ApiModelProperty(notes = "年龄")
    @Excel(name = "年龄")
    private String age;

    /** 金额 */
    @ApiModelProperty(notes = "金额")
    @Excel(name = "金额")
    private BigDecimal fee;

    /** 单据号 */
    @ApiModelProperty(notes = "单据号")
    @Excel(name = "单据号")
    private String billCode;

    /** 单据类型（8检查 9检验） */
    @ApiModelProperty(notes = "单据类型（8检查 9检验）")
    @Excel(name = "单据类型", readConverterExp = "8=检查,9=检验")
    private Integer billType;

    /** 单据状态 */
    @ApiModelProperty(notes = "单据状态")
    private Integer billStatus;

    /** 开单时间 */
    @ApiModelProperty(notes = "开单时间")
    @Excel(name = "开单时间")
    private String billTime;

    /** 数据来源 */
    @ApiModelProperty(notes = "数据来源")
    private Integer dataSource;

    /** 主诉 */
    @ApiModelProperty(notes = "主诉")
    @Excel(name = "主诉")
    private String subjective;

    /** 诊断 */
    @ApiModelProperty(notes = "诊断")
    @Excel(name = "诊断")
    private String assessment;

    /** 包名称 */
    @ApiModelProperty(notes = "包名称")
    @Excel(name = "包名称")
    private String packName;

    /** 标本类型（1全血 2血清 3粪便 4白带 5穿刺液） */
    @ApiModelProperty(notes = "标本类型（1全血 2血清 3粪便 4白带 5穿刺液）")
    @Excel(name = "标本类型", readConverterExp = "1=全血,2=血清,3=粪便,4=白带,5=穿刺液")
    private Integer specimenType;

    /** 标本号 */
    @ApiModelProperty(notes = "标本号")
    private String specimen;

    /** 图片源 */
    @ApiModelProperty(notes = "图片源")
    private String imageSrc;

    /** 结果 */
    @ApiModelProperty(notes = "结果")
    @Excel(name = "结果")
    private String dataResult;

    /** 异常类型（0否 1是 2无） */
    @ApiModelProperty(notes = "异常类型（0否 1是 2无）")
    @Excel(name = "异常类型", readConverterExp = "0=否,1=是,2=无")
    private Integer infectious;

    /** 结论 */
    @ApiModelProperty(notes = "结论")
    @Excel(name = "结论")
    private String conclusion;

    /** 模板ID */
    @ApiModelProperty(notes = "模板ID")
    private String templateId;

    /** 科室ID */
    @ApiModelProperty(notes = "科室ID")
    private String deptId;

    /** 科室名称 */
    @ApiModelProperty(notes = "科室名称")
    @Excel(name = "科室名称")
    private String deptName;

    /** 开单医生 */
    @ApiModelProperty(notes = "开单医生")
    @Excel(name = "开单医生")
    private String userName;

    /** 机构ID */
    @ApiModelProperty(notes = "机构ID")
    private String orgCode;

    /** 执行人 */
    @ApiModelProperty(notes = "执行人")
    @Excel(name = "执行人")
    private String actorName;

    /** 操作员 */
    @ApiModelProperty(notes = "操作员")
    @Excel(name = "操作员")
    private String operators;

    /** 报告时间 */
    @ApiModelProperty(notes = "报告时间")
    @Excel(name = "报告时间")
    private String execTime;

    /** 删除标记（0否 1是） */
    @ApiModelProperty(notes = "删除标记（0否 1是）")
    private Integer deleted;

    /** 检查检验明细信息 */
    @ApiModelProperty(notes = "检查检验明细信息")
    private List<HisInspectionReportItem> hisInspectionReportItemList;

}

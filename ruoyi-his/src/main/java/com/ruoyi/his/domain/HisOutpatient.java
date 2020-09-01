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
 * 门诊病人对象 his_outpatient
 * 
 * @author bend
 * @date 2020-07-08
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "门诊病人")
@Table(name = "his_outpatient")
public class HisOutpatient extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 就诊人 */
    @ApiModelProperty(notes = "就诊人")
    private Long patientId;

    /** 姓名 */
    @ApiModelProperty(notes = "姓名")
    @Excel(name = "姓名")
    private String patientName;

    /** 身份证 */
    @ApiModelProperty(notes = "身份证")
    @Excel(name = "身份证")
    private String idCardNo;

    /** 性别 */
    @ApiModelProperty(notes = "性别")
    @Excel(name = "性别")
    private String patientSex;

    /** 医院名称 */
    @ApiModelProperty(notes = "医院名称")
    @Excel(name = "医院名称")
    private String orgName;

    /** 医院ID */
    @ApiModelProperty(notes = "医院ID")
    private String orgCode;

    /** 门诊ID */
    @ApiModelProperty(notes = "门诊ID")
    @Excel(name = "门诊ID")
    private String businessId;

    /** 挂号ID */
    @ApiModelProperty(notes = "挂号ID")
    private String registeredId;

    /** 挂号费 */
    @ApiModelProperty(notes = "挂号费")
    private BigDecimal registeredFee;

    /** 门诊号 */
    @ApiModelProperty(notes = "门诊号")
    @Excel(name = "门诊号")
    private String outpatientNumber;

    /** 就诊时间 */
    @ApiModelProperty(notes = "就诊时间")
    @Excel(name = "就诊时间")
    private String visitDate;

    /** 科室ID */
    @ApiModelProperty(notes = "科室ID")
    private String deptId;

    /** 科室名称 */
    @ApiModelProperty(notes = "科室名称")
    @Excel(name = "科室名称")
    private String deptName;

    /** 医生ID */
    @ApiModelProperty(notes = "医生ID")
    private String doctorId;

    /** 医生名称 */
    @ApiModelProperty(notes = "医生名称")
    @Excel(name = "医生名称")
    private String doctorName;

    /** 诊断编码 */
    @ApiModelProperty(notes = "诊断编码")
    private String diagnoseDiseaseCode;

    /** 诊断名称 */
    @ApiModelProperty(notes = "诊断名称")
    @Excel(name = "诊断名称")
    private String diagnoseDiseaseName;

    /** 病情描述 */
    @ApiModelProperty(notes = "病情描述")
    @Excel(name = "病情描述")
    private String diseaseDesc;

    /** 经办人 */
    @ApiModelProperty(notes = "经办人")
    private String operator;

    /** 总费用 */
    @ApiModelProperty(notes = "总费用")
    @Excel(name = "总费用")
    private BigDecimal totalCost;

    /** 接诊状态（-1已取消 0未接诊 1接诊中 2已结诊） */
    @ApiModelProperty(notes = "接诊状态（-1已取消 0未接诊 1接诊中 2已结诊）")
    @Excel(name = "接诊状态", readConverterExp = "-1=已取消,0=未接诊,1=接诊中,2=已结诊")
    private Integer receptionStatus;

    /** 删除标记（0否 1是） */
    @ApiModelProperty(notes = "删除标记（0否 1是）")
    private Integer deleted;

}

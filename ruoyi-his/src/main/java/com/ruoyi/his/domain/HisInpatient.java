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
 * 住院病人对象 his_inpatient
 * 
 * @author bend
 * @date 2020-07-08
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "住院病人")
@Table(name = "his_inpatient")
public class HisInpatient extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 就诊人 */
    @ApiModelProperty(notes = "就诊人")
    private Long patientId;

    /** 姓名 */
    @ApiModelProperty(notes = "姓名")
    @Excel(name = "姓名")
    private String patientName;

    /** 身份证号 */
    @ApiModelProperty(notes = "身份证号")
    @Excel(name = "身份证号")
    private String idCardNo;

    /** 性别 */
    @ApiModelProperty(notes = "性别")
    @Excel(name = "性别")
    private String patientSex;

    /** 出生日期 */
    @ApiModelProperty(notes = "出生日期")
    @Excel(name = "出生日期")
    private String birthday;

    /** 联系人 */
    @ApiModelProperty(notes = "联系人")
    private String contactName;

    /** 联系电话 */
    @ApiModelProperty(notes = "联系电话")
    private String mobilePhone;

    /** 家庭地址 */
    @ApiModelProperty(notes = "家庭地址")
    private String familyAddress;

    /** 入院日期 */
    @ApiModelProperty(notes = "入院日期")
    @Excel(name = "入院日期")
    private String admissionDate;

    /** 出院日期 */
    @ApiModelProperty(notes = "出院日期")
    @Excel(name = "出院日期")
    private String leaveDate;

    /** 住院号 */
    @ApiModelProperty(notes = "住院号")
    @Excel(name = "住院号")
    private String hospitalizedNo;

    /** 住院ID */
    @ApiModelProperty(notes = "住院ID")
    @Excel(name = "住院ID")
    private String businessId;

    /** 医院ID */
    @ApiModelProperty(notes = "医院ID")
    private String orgCode;

    /** 医院名称 */
    @ApiModelProperty(notes = "医院名称")
    @Excel(name = "医院名称")
    private String orgName;

    /** 入院科室 */
    @ApiModelProperty(notes = "入院科室")
    private String deptId;

    /** 入院科室名称 */
    @ApiModelProperty(notes = "入院科室名称")
    @Excel(name = "入院科室名称")
    private String deptName;

    /** 入院诊断医生ID */
    @ApiModelProperty(notes = "入院诊断医生ID")
    private String doctorId;

    /** 入院诊断医生 */
    @ApiModelProperty(notes = "入院诊断医生")
    @Excel(name = "入院诊断医生")
    private String doctorName;

    /** 入院床位编码 */
    @ApiModelProperty(notes = "入院床位编码")
    private String bedId;

    /** 入院床位 */
    @ApiModelProperty(notes = "入院床位")
    private String bedName;

    /** 入院主诊断 */
    @ApiModelProperty(notes = "入院主诊断")
    @Excel(name = "入院主诊断")
    private String mainDiagnose;

    /** 入院主诊断ICD10 */
    @ApiModelProperty(notes = "入院主诊断ICD10")
    @Excel(name = "入院主诊断ICD10")
    private String mainDiagnoseIcd10;

    /** 入院次诊断 */
    @ApiModelProperty(notes = "入院次诊断")
    @Excel(name = "入院次诊断")
    private String secondaryDiagnose;

    /** 入院次诊断ICD10 */
    @ApiModelProperty(notes = "入院次诊断ICD10")
    private String secondaryDiagnoseIcd10;

    /** 入院病区编码 */
    @ApiModelProperty(notes = "入院病区编码")
    private String wardId;

    /** 入院病区 */
    @ApiModelProperty(notes = "入院病区")
    private String wardName;

    /** 入院经办人 */
    @ApiModelProperty(notes = "入院经办人")
    private String operator;

    /** 入院经办时间 */
    @ApiModelProperty(notes = "入院经办时间")
    private String operateTime;

    /** 住院总费用 */
    @ApiModelProperty(notes = "住院总费用")
    @Excel(name = "住院总费用")
    private BigDecimal totalFee;

    /** 出院科室编码 */
    @ApiModelProperty(notes = "出院科室编码")
    private String leaveDeptId;

    /** 出院科室名称 */
    @ApiModelProperty(notes = "出院科室名称")
    @Excel(name = "出院科室名称")
    private String leaveDeptName;

    /** 出院病区编码 */
    @ApiModelProperty(notes = "出院病区编码")
    private String leaveWardId;

    /** 出院病区 */
    @ApiModelProperty(notes = "出院病区")
    private String leaveWardName;

    /** 出院床位编码 */
    @ApiModelProperty(notes = "出院床位编码")
    private String leaveBedId;

    /** 出院床位 */
    @ApiModelProperty(notes = "出院床位")
    private String leaveBedName;

    /** 出院主诊断 */
    @ApiModelProperty(notes = "出院主诊断")
    @Excel(name = "出院主诊断")
    private String leaveMainDiagnose;

    /** 出院主诊断ICD10 */
    @ApiModelProperty(notes = "出院主诊断ICD10")
    private String leaveMainDiagnoseIcd10;

    /** 出院次诊断 */
    @ApiModelProperty(notes = "出院次诊断")
    @Excel(name = "出院次诊断")
    private String leaveSecondaryDiagnose;

    /** 出院次诊断ICD10 */
    @ApiModelProperty(notes = "出院次诊断ICD10")
    private String leaveSecondaryDiagnoseIcd10;

    /** 在院状态（0在院无床 1在院有床 2出院未结算 3出院已结算 -1撤销入院） */
    @ApiModelProperty(notes = "在院状态（0在院无床 1在院有床 2出院未结算 3出院已结算 -1撤销入院）")
    @Excel(name = "在院状态", readConverterExp = "0=在院无床,1=在院有床,2=出院未结算,3=出院已结算,-=1撤销入院")
    private Integer inpatientStatus;

    /** 住院总预交费 */
    @ApiModelProperty(notes = "住院总预交费")
    @Excel(name = "住院总预交费")
    private BigDecimal prepaidTotalFee;

    /** 执业医师证号 */
    @ApiModelProperty(notes = "执业医师证号")
    private String doctorNumber;

    /** 删除标记（0否 1是） */
    @ApiModelProperty(notes = "删除标记（0否 1是）")
    private Integer deleted;

}

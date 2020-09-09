package com.ruoyi.his.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.List;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.Table;

/**
 * 费用记录对象 his_patient_expenses
 * 
 * @author bend
 * @date 2020-07-09
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "费用记录")
@Table(name = "his_patient_expenses")
public class HisPatientExpenses extends BaseEntity
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

    /** 订单号 */
    @ApiModelProperty(notes = "订单号")
    private String orderCode;

    /** 机构ID */
    @ApiModelProperty(notes = "机构ID")
    private String orgCode;

    /** 医院名称 */
    @ApiModelProperty(notes = "医院名称")
    @Excel(name = "医院名称")
    private String orgName;

    /** 开单科室 */
    @ApiModelProperty(notes = "开单科室")
    @Excel(name = "开单科室")
    private String billDept;

    /** 开单医生 */
    @ApiModelProperty(notes = "开单医生")
    @Excel(name = "开单医生")
    private String billDoctor;

    /** 业务ID[分别对应门诊和住院] */
    @ApiModelProperty(notes = "业务ID[分别对应门诊和住院]")
    @Excel(name = "业务ID")
    private String businessId;

    /** 业务编号[分别对应门诊号和住院号] */
    @ApiModelProperty(notes = "业务编号[分别对应门诊号和住院号]")
    @Excel(name = "业务编号")
    private String expenseBusinessNo;

    /** 业务类型（1门诊 2住院） */
    @ApiModelProperty(notes = "业务类型（1门诊 2住院）")
    @Excel(name = "业务类型", readConverterExp = "1=门诊,2=住院")
    private Integer expenseType;

    /** 收费记录ID */
    @ApiModelProperty(notes = "收费记录ID")
    @Excel(name = "收费记录ID")
    private String chargeRecordId;

    /** 删除标记（0否 1是） */
    @ApiModelProperty(notes = "删除标记（0否 1是）")
    private Integer deleted;

    /** 费用详情信息 */
    @ApiModelProperty(notes = "费用详情信息")
    private List<HisPatientExpensesDetail> hisPatientExpensesDetailList;

}

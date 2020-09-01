package com.ruoyi.his.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.Table;

/**
 * 申请单对象 his_inspection_apply
 * 
 * @author bend
 * @date 2020-07-10
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "申请单")
@Table(name = "his_inspection_apply")
public class HisInspectionApply extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 机构编码 */
    @ApiModelProperty(notes = "机构编码")
    private String orgCode;

    /** 姓名 */
    @ApiModelProperty(notes = "姓名")
    @Excel(name = "姓名")
    private String patientName;

    /** 身份证号 */
    @ApiModelProperty(notes = "身份证号")
    @Excel(name = "身份证号")
    private String idCardNo;

    /** 机构名称 */
    @ApiModelProperty(notes = "机构名称")
    @Excel(name = "机构名称")
    private String orgName;

    /** 申请单ID */
    @ApiModelProperty(notes = "申请单ID")
    @Excel(name = "申请单ID")
    private String applyId;

    /** 项目名称 */
    @ApiModelProperty(notes = "项目名称")
    @Excel(name = "项目名称")
    private String applyItemName;

    /** 开单时间 */
    @ApiModelProperty(notes = "开单时间")
    @Excel(name = "开单时间")
    private String billTime;

    /** 开单人 */
    @ApiModelProperty(notes = "开单人")
    @Excel(name = "开单人")
    private String operator;

    /** 操作人员 */
    @ApiModelProperty(notes = "操作人员")
    @Excel(name = "操作人员")
    private String userId;

    /** 业务ID */
    @ApiModelProperty(notes = "业务ID")
    @Excel(name = "业务ID")
    private String businessId;

    /** 删除标记（0否 1是） */
    @ApiModelProperty(notes = "删除标记（0否 1是）")
    private Integer deleted;

}

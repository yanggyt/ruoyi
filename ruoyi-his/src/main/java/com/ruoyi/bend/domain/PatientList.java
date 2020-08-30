package com.ruoyi.bend.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.Table;

/**
 * 就诊人列表对象 bend_patient_list
 * 
 * @author bend
 * @date 2020-08-30
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "就诊人列表")
@Table(name = "bend_patient_list")
public class PatientList extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户ID */
    @ApiModelProperty(notes = "用户ID")
    private Long memberId;

    /** 真实姓名 */
    @ApiModelProperty(notes = "真实姓名")
    @Excel(name = "真实姓名")
    private String realName;

    /** 身份证号 */
    @ApiModelProperty(notes = "身份证号")
    @Excel(name = "身份证号")
    private String idCardNo;

    /** 手机号 */
    @ApiModelProperty(notes = "手机号")
    @Excel(name = "手机号")
    private String mobilePhone;

    /** 是否默认（0否 1是） */
    @ApiModelProperty(notes = "是否默认（0否 1是）")
    @Excel(name = "是否默认", readConverterExp = "0=否,1=是")
    private Integer defaultPatient;

    /** 就诊人关系（0未指定 1本人 2配偶 3父亲 4母亲 5儿子 6女儿 7亲友 8朋友 9其他） */
    @ApiModelProperty(notes = "就诊人关系（0未指定 1本人 2配偶 3父亲 4母亲 5儿子 6女儿 7亲友 8朋友 9其他）")
    @Excel(name = "就诊人关系", readConverterExp = "0=未指定,1=本人,2=配偶,3=父亲,4=母亲,5=儿子,6=女儿,7=亲友,8=朋友,9=其他")
    private Integer relationShip;

    /** 家庭关系（0未指定 1配偶 2父子 3母子 4儿子 5女儿） */
    @ApiModelProperty(notes = "家庭关系（0未指定 1配偶 2父子 3母子 4儿子 5女儿）")
    @Excel(name = "家庭关系", readConverterExp = "0=未指定,1=配偶,2=父子,3=母子,4=儿子,5=女儿")
    private Integer familyRelationship;

    /** 性别（0未知 1男 2女） */
    @ApiModelProperty(notes = "性别（0未知 1男 2女）")
    @Excel(name = "性别", readConverterExp = "0=未知,1=男,2=女")
    private Integer sex;

    /** 绑定状态（0绑定中 1已解绑） */
    @ApiModelProperty(notes = "绑定状态（0绑定中 1已解绑）")
    @Excel(name = "绑定状态", readConverterExp = "0=绑定中,1=已解绑")
    private Integer bindStatus;

    /** 删除标记（0否 1是） */
    @ApiModelProperty(notes = "删除标记（0否 1是）")
    private Integer deleted;

}

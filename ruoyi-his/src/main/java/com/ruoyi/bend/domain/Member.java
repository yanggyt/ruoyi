package com.ruoyi.bend.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.Table;

/**
 * 会员列表对象 bend_member
 * 
 * @author bend
 * @date 2020-08-30
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "会员列表")
@Table(name = "bend_member")
public class Member extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 手机号 */
    @ApiModelProperty(notes = "手机号")
    @Excel(name = "手机号")
    private String mobilePhone;

    /** 密码 */
    @ApiModelProperty(notes = "密码")
    private String password;

    /** 真实姓名 */
    @ApiModelProperty(notes = "真实姓名")
    @Excel(name = "真实姓名")
    private String realName;

    /** 身份证号 */
    @ApiModelProperty(notes = "身份证号")
    @Excel(name = "身份证号")
    private String idCardNo;

    /** 用户状态（0正常 1冻结） */
    @ApiModelProperty(notes = "用户状态（0正常 1冻结）")
    @Excel(name = "用户状态", readConverterExp = "0=正常,1=冻结")
    private Integer memberStatus;

    /** 会员类型（0普通 1医生 2管理员） */
    @ApiModelProperty(notes = "会员类型（0普通 1医生 2管理员）")
    @Excel(name = "会员类型", readConverterExp = "0=普通,1=医生,2=管理员")
    private Integer memberType;

    /** 删除标记（0否 1是） */
    @ApiModelProperty(notes = "删除标记（0否 1是）")
    private Integer deleted;

}

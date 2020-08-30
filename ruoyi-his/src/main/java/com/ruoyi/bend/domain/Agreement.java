package com.ruoyi.bend.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.Table;

/**
 * 协议管理对象 bend_agreement
 * 
 * @author bend
 * @date 2020-08-30
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "协议管理")
@Table(name = "bend_agreement")
public class Agreement extends BaseEntity
{
    private static final long serialVersionUID = 1L;


    /** 协议标题 */
    @ApiModelProperty(notes = "协议标题")
    @Excel(name = "协议标题")
    private String agreementTitle;

    /** 协议类型 */
    @ApiModelProperty(notes = "协议类型")
    @Excel(name = "协议类型")
    private Integer agreementType;

    /** 状态（0正常 1关闭） */
    @ApiModelProperty(notes = "状态（0正常 1关闭）")
    @Excel(name = "状态", readConverterExp = "0=正常,1=关闭")
    private Integer status;

    /** 协议内容 */
    @ApiModelProperty(notes = "协议内容")
    private String agreementContent;

    /** 删除标记（0否 1是） */
    @ApiModelProperty(notes = "删除标记（0否 1是）")
    private Integer deleted;

}

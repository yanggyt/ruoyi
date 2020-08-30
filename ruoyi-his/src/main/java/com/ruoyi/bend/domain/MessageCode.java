package com.ruoyi.bend.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.Table;

/**
 * 短信管理对象 bend_message_code
 * 
 * @author bend
 * @date 2020-08-30
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "短信管理")
@Table(name = "bend_message_code")
public class MessageCode extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 手机号 */
    @ApiModelProperty(notes = "手机号")
    @Excel(name = "手机号")
    private String mobilePhone;

    /** 短信签名 */
    @ApiModelProperty(notes = "短信签名")
    @Excel(name = "短信签名")
    private String signName;

    /** 短信类型 */
    @ApiModelProperty(notes = "短信类型")
    private Integer smsType;

    /** 验证码 */
    @ApiModelProperty(notes = "验证码")
    @Excel(name = "验证码")
    private String smsCode;

    /** 短信内容 */
    @ApiModelProperty(notes = "短信内容")
    @Excel(name = "短信内容")
    private String messageText;

    /** 模板编号 */
    @ApiModelProperty(notes = "模板编号")
    @Excel(name = "模板编号")
    private String templateCode;

    /** 模板参数 */
    @ApiModelProperty(notes = "模板参数")
    @Excel(name = "模板参数")
    private String templateParam;

    /** 状态（0未使用 1已使用） */
    @ApiModelProperty(notes = "状态（0未使用 1已使用）")
    @Excel(name = "状态", readConverterExp = "0=未使用,1=已使用")
    private Integer smsStatus;

    /** 删除标记（0否 1是） */
    @ApiModelProperty(notes = "删除标记（0否 1是）")
    private Integer deleted;

}

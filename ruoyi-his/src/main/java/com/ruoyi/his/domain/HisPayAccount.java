package com.ruoyi.his.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.Table;

/**
 * 支付账户对象 his_pay_account
 * 
 * @author bend
 * @date 2020-07-14
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "支付账户")
@Table(name = "his_pay_account")
public class HisPayAccount extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 账户ID */
    @ApiModelProperty(notes = "账户ID")
    @Excel(name = "账户ID")
    private String accId;

    /** 拼音简写 */
    @ApiModelProperty(notes = "拼音简写")
    @Excel(name = "拼音简写")
    private String accPy;

    /** 支付方式名称 */
    @ApiModelProperty(notes = "支付方式名称")
    @Excel(name = "支付方式名称")
    private String accName;

    /** 支付类型 */
    @ApiModelProperty(notes = "支付类型")
    @Excel(name = "支付类型")
    private String payment;

    /** 拼音简写 */
    @ApiModelProperty(notes = "拼音简写")
    @Excel(name = "拼音简写")
    private String payPy;

    /** 支付方式ID */
    @ApiModelProperty(notes = "支付方式ID")
    @Excel(name = "支付方式ID")
    private String paymentId;

    /** 默认显示（0否 1是） */
    @ApiModelProperty(notes = "默认显示（0否 1是）")
    @Excel(name = "默认显示", readConverterExp = "0=否,1=是")
    private Integer isDefaultShow;

    /** 排序号 */
    @ApiModelProperty(notes = "排序号")
    @Excel(name = "排序号")
    private Long sortNo;

    /** 删除标记（0否 1是） */
    @ApiModelProperty(notes = "删除标记（0否 1是）")
    private Integer deleted;

}

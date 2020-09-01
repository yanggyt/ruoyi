package com.ruoyi.his.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.Table;
/**
 * 特约商户对象 his_merchant_wechat
 * 
 * @author bend
 * @date 2020-07-27
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "微信服务商")
@Table(name = "his_wechat_provider")
public class HisMerchantWechat extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 服务商ID */
    @ApiModelProperty(notes = "服务商ID")
    @Excel(name = "服务商ID")
    private Long providerId;

    /** 特约商户 */
    @ApiModelProperty(notes = "特约商户")
    @Excel(name = "特约商户")
    private String subAppId;

    /** 机构ID */
    @ApiModelProperty(notes = "机构ID")
    @Excel(name = "机构ID")
    private String orgCode;

    /** 机构名称 */
    @ApiModelProperty(notes = "机构名称")
    @Excel(name = "机构名称")
    private String orgName;

    /** 删除标记（0否 1是） */
    @ApiModelProperty(notes = "删除标记（0否 1是）")
    private Integer deleted;

}

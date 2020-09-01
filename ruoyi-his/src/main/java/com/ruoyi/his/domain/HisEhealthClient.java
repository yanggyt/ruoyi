package com.ruoyi.his.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.Table;

/**
 * 健康卡对象 his_ehealth_client
 * 
 * @author bend
 * @date 2020-07-27
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "健康卡")
@Table(name = "his_ehealth_client")
public class HisEhealthClient extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** APPID */
    @ApiModelProperty(notes = "APPID")
    @Excel(name = "APPID")
    private String appId;

    /** APP密钥 */
    @ApiModelProperty(notes = "APP密钥")
    @Excel(name = "APP密钥")
    private String appSecret;

    /** 机构代码 */
    @ApiModelProperty(notes = "机构代码")
    @Excel(name = "机构代码")
    private String orgCode;

    /** 机构名称 */
    @ApiModelProperty(notes = "机构名称")
    @Excel(name = "机构名称")
    private String orgName;

    /** 接口地址 */
    @ApiModelProperty(notes = "接口地址")
    @Excel(name = "接口地址")
    private String openApi;

    /** 版本号 */
    @ApiModelProperty(notes = "版本号")
    @Excel(name = "版本号")
    private String version;

    /** 删除标记（0否 1是） */
    @ApiModelProperty(notes = "删除标记（0否 1是）")
    private Integer deleted;

}

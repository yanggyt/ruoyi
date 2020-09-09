package com.ruoyi.his.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.Table;

/**
 * 农信商户对象 his_merchant_scrcu
 * 
 * @author bend
 * @date 2020-07-27
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "农信商户")
@Table(name = "his_merchant_scrcu")
public class HisMerchantScrcu extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主商户号 */
    @ApiModelProperty(notes = "主商户号")
    @Excel(name = "主商户号")
    private String mchNo;

    /** 主商户名 */
    @ApiModelProperty(notes = "主商户名")
    @Excel(name = "主商户名")
    private String mchName;

    /** 子商户号 */
    @ApiModelProperty(notes = "子商户号")
    @Excel(name = "子商户号")
    private String subMchNo;

    /** 子商户名 */
    @ApiModelProperty(notes = "子商户名")
    @Excel(name = "子商户名")
    private String subMchName;

    /** 机构ID */
    @ApiModelProperty(notes = "机构ID")
    @Excel(name = "机构ID")
    private String orgCode;

    /** 机构名称 */
    @ApiModelProperty(notes = "机构名称")
    @Excel(name = "机构名称")
    private String orgName;

    /** 客户端ID */
    @ApiModelProperty(notes = "客户端ID")
    @Excel(name = "客户端ID")
    private String clientId;

    /** 客户端密钥 */
    @ApiModelProperty(notes = "客户端密钥")
    @Excel(name = "客户端密钥")
    private String secret;

    /** 开放平台 */
    @ApiModelProperty(notes = "开放平台")
    @Excel(name = "开放平台")
    private String openClientId;

    /** 接口地址 */
    @ApiModelProperty(notes = "接口地址")
    @Excel(name = "接口地址")
    private String openApi;

    /** 公钥路径 */
    @ApiModelProperty(notes = "公钥路径")
    @Excel(name = "公钥路径")
    private String publicKeyPath;

    /** 私钥路径 */
    @ApiModelProperty(notes = "私钥路径")
    @Excel(name = "私钥路径")
    private String privateKeyPath;

    /** 私钥密码 */
    @ApiModelProperty(notes = "私钥密码")
    @Excel(name = "私钥密码")
    private String privateKeyPassword;

    /** 区划代码 */
    @ApiModelProperty(notes = "区划代码")
    @Excel(name = "区划代码")
    private String txnAreaInfo;

    /** 版本号 */
    @ApiModelProperty(notes = "版本号")
    @Excel(name = "版本号")
    private String version;

    /** 删除标记（0否 1是） */
    @ApiModelProperty(notes = "删除标记（0否 1是）")
    private Integer deleted;

}

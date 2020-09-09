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
 * 微信服务商对象 his_wechat_provider
 * 
 * @author bend
 * @date 2020-07-27
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "微信服务商")
@Table(name = "his_wechat_provider")
public class HisWechatProvider extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 客户端ID */
    @ApiModelProperty(notes = "客户端ID")
    @Excel(name = "客户端ID")
    private String clientId;

    /** 客户端密钥 */
    @ApiModelProperty(notes = "客户端密钥")
    @Excel(name = "客户端密钥")
    private String clientSecret;

    /** AppID */
    @ApiModelProperty(notes = "AppID")
    @Excel(name = "AppID")
    private String appId;

    /** AppSecret */
    @ApiModelProperty(notes = "AppSecret")
    @Excel(name = "AppSecret")
    private String appSecret;

    /** 服务商AppId */
    @ApiModelProperty(notes = "服务商AppId")
    @Excel(name = "服务商AppId")
    private String mchAppId;

    /** 服务商账号 */
    @ApiModelProperty(notes = "服务商账号")
    @Excel(name = "服务商账号")
    private String mchId;

    /** 服务商密钥 */
    @ApiModelProperty(notes = "服务商密钥")
    @Excel(name = "服务商密钥")
    private String mchKey;

    /** 证书路径 */
    @ApiModelProperty(notes = "证书路径")
    @Excel(name = "证书路径")
    private String keyPath;

    /**
     * 前端地址(公众号）
     */
    @ApiModelProperty(value = "前端地址(公众号）")
    @Excel(name = "前端地址")
    private String frontUrl;

    /** 后台接口 */
    @ApiModelProperty(notes = "后台接口")
    @Excel(name = "后台接口")
    private String openApi;

    /** 删除标记（0否 1是） */
    @ApiModelProperty(notes = "删除标记（0否 1是）")
    private Integer deleted;

    /** 特约商户信息 */
    @ApiModelProperty(notes = "特约商户信息")
    private List<HisMerchantWechat> hisMerchantWechatList;

}

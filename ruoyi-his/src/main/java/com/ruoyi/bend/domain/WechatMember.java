package com.ruoyi.bend.domain;

import java.util.Date;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.Table;

/**
 * 微信用户对象 bend_wechat_member
 * 
 * @author bend
 * @date 2020-08-30
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "微信用户")
@Table(name = "bend_wechat_member")
public class WechatMember extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户ID */
    @ApiModelProperty(notes = "用户ID")
    private Long memberId;

    /** 手机号 */
    @ApiModelProperty(notes = "手机号")
    @Excel(name = "手机号")
    private String mobilePhone;

    /** AppId */
    @ApiModelProperty(notes = "AppId")
    @Excel(name = "AppId")
    private String appId;

    /** OpenId */
    @ApiModelProperty(notes = "OpenId")
    @Excel(name = "OpenId")
    private String openId;

    /** 微信昵称 */
    @ApiModelProperty(notes = "微信昵称")
    @Excel(name = "微信昵称")
    private String nickname;

    /** 真实姓名 */
    @ApiModelProperty(notes = "真实姓名")
    @Excel(name = "真实姓名")
    private String realName;

    /** 性别（0未知 1男 2女） */
    @ApiModelProperty(notes = "性别（0未知 1男 2女）")
    private Integer sex;

    /** 性别描述 */
    @ApiModelProperty(notes = "性别描述")
    @Excel(name = "性别描述")
    private String sexDesc;

    /** 关注状态（0未关注 1关注中 2已取消） */
    @ApiModelProperty(notes = "关注状态（0未关注 1关注中 2已取消）")
    @Excel(name = "关注状态", readConverterExp = "0=未关注,1=关注中,2=已取消")
    private Integer followStatus;

    /** 订阅标识（0未订阅 1订阅中 2已取消） */
    @ApiModelProperty(notes = "订阅标识（0未订阅 1订阅中 2已取消）")
    @Excel(name = "订阅标识", readConverterExp = "0=未订阅,1=订阅中,2=已取消")
    private Integer subscribe;

    /** 所在城市 */
    @ApiModelProperty(notes = "所在城市")
    @Excel(name = "所在城市")
    private String city;

    /** 所在省份 */
    @ApiModelProperty(notes = "所在省份")
    @Excel(name = "所在省份")
    private String province;

    /** 所在国家 */
    @ApiModelProperty(notes = "所在国家")
    private String country;

    /** 用户语言 */
    @ApiModelProperty(notes = "用户语言")
    private String language;

    /** 用户头像 */
    @ApiModelProperty(notes = "用户头像")
    @Excel(name = "用户头像")
    private String headImgUrl;

    /** 关注时间 */
    @ApiModelProperty(notes = "关注时间")
    @Excel(name = "关注时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date subscribeTime;

    /** UnionID */
    @ApiModelProperty(notes = "UnionID")
    private String unionid;

    /** 分组ID */
    @ApiModelProperty(notes = "分组ID")
    private String groupId;

    /** 标签列表 */
    @ApiModelProperty(notes = "标签列表")
    private String tagIds;

    /** 关注渠道 */
    @ApiModelProperty(notes = "关注渠道")
    private String subscribeScene;

    /** 扫码场景 */
    @ApiModelProperty(notes = "扫码场景")
    private String qrScene;

    /** 场景描述 */
    @ApiModelProperty(notes = "场景描述")
    private String qrSceneStr;

    /** 删除标记（0否 1是） */
    @ApiModelProperty(notes = "删除标记（0否 1是）")
    private Integer deleted;

}

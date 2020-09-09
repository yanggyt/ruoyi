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
 * 内容管理对象 bend_article
 * 
 * @author bend
 * @date 2020-08-30
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "内容管理")
@Table(name = "bend_article")
public class Article extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 文章标题 */
    @ApiModelProperty(notes = "文章标题")
    @Excel(name = "文章标题")
    private String title;

    /** 副标题 */
    @ApiModelProperty(notes = "副标题")
    @Excel(name = "副标题")
    private String subTitle;

    /** 文章封面 */
    @ApiModelProperty(notes = "文章封面")
    @Excel(name = "文章封面")
    private String articleCover;

    /** 浏览量 */
    @ApiModelProperty(notes = "浏览量")
    @Excel(name = "浏览量")
    private Long pvCount;

    /** 评论数 */
    @ApiModelProperty(notes = "评论数")
    @Excel(name = "评论数")
    private Long commentCount;

    /** 转载量 */
    @ApiModelProperty(notes = "转载量")
    @Excel(name = "转载量")
    private Long quoteCount;

    /** 微信公众号 */
    @ApiModelProperty(notes = "微信公众号")
    @Excel(name = "微信公众号")
    private String wechatAccount;

    /** 署名名称(医生名称或医院名称) */
    @ApiModelProperty(notes = "署名名称(医生名称或医院名称)")
    @Excel(name = "署名名称(医生名称或医院名称)")
    private String signatureName;

    /** 文章类型（0公众号 1平台文章 2医院文章 3医生文章） */
    @ApiModelProperty(notes = "文章类型（0公众号 1平台文章 2医院文章 3医生文章）")
    @Excel(name = "文章类型", readConverterExp = "0=公众号,1=平台文章,2=医院文章,3=医生文章")
    private Integer articleType;

    /** 文章类别（0内容文章 1活动文章） */
    @ApiModelProperty(notes = "文章类别（0内容文章 1活动文章）")
    @Excel(name = "文章类别", readConverterExp = "0=内容文章,1=活动文章")
    private Integer articleClassify;

    /** 文章分类（0健康资讯 1惠民政策） */
    @ApiModelProperty(notes = "文章分类（0健康资讯 1惠民政策）")
    @Excel(name = "文章分类", readConverterExp = "0=健康资讯,1=惠民政策")
    private Integer articleCategory;

    /** 文章状态（0草稿 1正常 2删除） */
    @ApiModelProperty(notes = "文章状态（0草稿 1正常 2删除）")
    @Excel(name = "文章状态", readConverterExp = "0=草稿,1=正常,2=删除")
    private Integer articleStatus;

    /** 审核状态（0正常/审核通过 1审核中 2审核驳回） */
    @ApiModelProperty(notes = "审核状态（0正常/审核通过 1审核中 2审核驳回）")
    @Excel(name = "审核状态", readConverterExp = "0=正常/审核通过,1=审核中,2=审核驳回")
    private Integer approveStatus;

    /** 排序号 */
    @ApiModelProperty(notes = "排序号")
    @Excel(name = "排序号")
    private Long sortNo;

    /** 审核时间 */
    @ApiModelProperty(notes = "审核时间")
    private Date auditTime;

    /** 发布时间 */
    @ApiModelProperty(notes = "发布时间")
    @Excel(name = "发布时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date releaseTime;

    /** 文章内容 */
    @ApiModelProperty(notes = "文章内容")
    private String content;

    /** 删除标记（0否 1是） */
    @ApiModelProperty(notes = "删除标记（0否 1是）")
    private Integer deleted;

}

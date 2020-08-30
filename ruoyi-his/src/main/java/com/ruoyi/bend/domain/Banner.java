package com.ruoyi.bend.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.Table;

/**
 * 首页管理对象 bend_banner
 * 
 * @author bend
 * @date 2020-08-30
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "首页管理")
@Table(name = "bend_banner")
public class Banner extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 广告标题 */
    @ApiModelProperty(notes = "广告标题")
    @Excel(name = "广告标题")
    private String adTitle;

    /** 图片地址 */
    @ApiModelProperty(notes = "图片地址")
    @Excel(name = "图片地址")
    private String adUrl;

    /** 活动详情 */
    @ApiModelProperty(notes = "活动详情")
    @Excel(name = "活动详情")
    private String adDetailUrl;

    /** 广告类型（0广告 1活动） */
    @ApiModelProperty(notes = "广告类型（0广告 1活动）")
    @Excel(name = "广告类型", readConverterExp = "0=广告,1=活动")
    private Integer adType;

    /** 删除标记（0否 1是） */
    @ApiModelProperty(notes = "删除标记（0否 1是）")
    private Integer deleted;

}

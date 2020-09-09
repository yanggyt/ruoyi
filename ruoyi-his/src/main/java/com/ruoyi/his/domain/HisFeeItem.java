package com.ruoyi.his.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.Table;

/**
 * 费用类型对象 his_fee_item
 * 
 * @author bend
 * @date 2020-07-14
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "费用类型")
@Table(name = "his_fee_item")
public class HisFeeItem extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 类型ID */
    @ApiModelProperty(notes = "类型ID")
    @Excel(name = "类型ID")
    private String feeTypeId;

    /** 类型名称 */
    @ApiModelProperty(notes = "类型名称")
    @Excel(name = "类型名称")
    private String feeTypeName;

    /** 默认显示（0否 1是） */
    @ApiModelProperty(notes = "默认显示（0否 1是）")
    @Excel(name = "默认显示", readConverterExp = "0=否,1=是")
    private Integer isShow;

    /** 删除标记（0否 1是） */
    @ApiModelProperty(notes = "删除标记（0否 1是）")
    private Integer deleted;

}

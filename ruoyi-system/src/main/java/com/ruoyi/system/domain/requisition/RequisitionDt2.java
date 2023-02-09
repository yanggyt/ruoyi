package com.ruoyi.system.domain.requisition;

import lombok.Data;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 请购单供应商明显dt2对象 sys_requisition_dt2
 *
 * @author SKaiL
 * @date 2022-09-26
 */
@Data
public class RequisitionDt2 extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    /** 主表id */
    @Excel(name = "主表id")
    private Long requisitionId;

    /** 供应商是否选中 */
    @Excel(name = "供应商是否选中")
    private Integer suppliers;

    /** 供应商名称 */
    @Excel(name = "供应商名称")
    private String suppliersName;

    /** 供应商编号 */
    @Excel(name = "供应商编号")
    private String suppliersCode;

    /** 税率 */
    @Excel(name = "税率")
    private Integer taxRate;

    /** 币别 */
    @Excel(name = "币别")
    private Integer coin;

    /** 总金额 */
    @Excel(name = "总金额")
    private String totalAmount;

    /** 未税单价 */
    @Excel(name = "未税单价")
    private String untaxedUnitPrice;

}

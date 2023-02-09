package com.ruoyi.system.domain.requisition;

import lombok.Data;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 请购单产品明细dt1对象 sys_requisition_dt1
 * 
 * @author SKaiL
 * @date 2022-09-26
 */
@Data
public class RequisitionDt1 extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    /** 主表id */
    @Excel(name = "主表id")
    private Long requisitionId;

    /** 项目编号 */
    @Excel(name = "项目编号")
    private String projectCode;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String projectName;

    /** 规格说明 */
    @Excel(name = "规格说明")
    private String specifications;

    /** 购买数量 */
    @Excel(name = "购买数量")
    private Long quantity;

    /** 单价 */
    @Excel(name = "单价")
    private String unitPrice;

    /** 金额 */
    @Excel(name = "金额")
    private String amount;


}

package com.ruoyi.system.domain.paymentRequest;

import lombok.Data;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 请款单dt1对象 sys_payment_request_dt1
 * 
 * @author SKaiL
 * @date 2022-09-21
 */
@Data
public class PaymentRequestDt1 extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    /** 关联id */
    @Excel(name = "关联id")
    private Long requestId;

    /** 项目编号 */
    @Excel(name = "项目编号")
    private String projectCode;

    /** 摘 要 说 明 */
    @Excel(name = "摘 要 说 明")
    private String content;

    /** 单位 */
    @Excel(name = "单位")
    private String unit;

    /** 数量 */
    @Excel(name = "数量")
    private String quantity;

    /** 单价 */
    @Excel(name = "单价")
    private String unitPrice;

    /** 总金额 */
    @Excel(name = "总金额")
    private String totalAmount;

    /** 预付比例 */
    @Excel(name = "预付比例")
    private String ratio;

    /** 币别 */
    @Excel(name = "币别")
    private Integer currency;

    /** 删除标志（0代表删除 1代表存在） */
    private String delFlag;

}

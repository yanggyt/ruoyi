package com.ruoyi.system.domain.paymentRequest;

import lombok.Data;

/**
 * 请款单审核结果
 *
 * @author Kailun_Shi
 * @version 1.0
 * @date 2021/11/25 11:37
 */
@Data
public class PaymentAuditResults {

    /** 请款单id */
    private Long paymentRequestId;

    /** 审核结果 1.同意 2.否决 3.退回重送 4.保留 5.加签*/
    private Integer result;

    /** 备注 */
    private String remark;

    /** 加签类型 1.前加签 2.后加签*/
    private Integer endorsementType;

    /** 后加签位置 */
    private Integer afterStatus;

    /** 加签人员员工编号 */
    private String endorsement;

    /** 请款单ids(用于批量审核) */
    private String ids;
}

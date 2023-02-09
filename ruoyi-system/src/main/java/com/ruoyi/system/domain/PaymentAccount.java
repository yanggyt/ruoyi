package com.ruoyi.system.domain;

import lombok.Data;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 付款账号信息对象 sys_payment_account
 * 
 * @author SKaiL
 * @date 2022-09-26
 */
@Data
public class PaymentAccount extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 父级ID */
    @Excel(name = "父级ID")
    private Long parentId;

    /** 收款人 */
    @Excel(name = "收款人")
    private String payee;

    /** 收款银行 */
    @Excel(name = "收款银行")
    private String bankName;

    /** 收款账号 */
    @Excel(name = "收款账号")
    private String accountNumber;

    /** 收款人地址 */
    @Excel(name = "收款人地址")
    private String address;

    /** 开户行代码 */
    @Excel(name = "开户行代码")
    private String swiftCode;

    /** 开户行地址 */
    @Excel(name = "开户行地址")
    private String bankAddress;

    /** 删除标志（0代表删除 1代表存在） */
    private Long delFlag;

}
